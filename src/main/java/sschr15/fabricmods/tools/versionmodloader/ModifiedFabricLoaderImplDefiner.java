package sschr15.fabricmods.tools.versionmodloader;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ModifiedFabricLoaderImplDefiner {
    public static void defineModifiedFabricLoaderImpl() {
        try {
            String className;
            ClassNode classNode = new ClassNode();
            ClassReader reader;
            try {
                reader = new ClassReader("net.fabricmc.loader.impl.FabricLoaderImpl");
                className = "net.fabricmc.loader.impl.FabricLoaderImpl";
            } catch (Exception e) {
                reader = new ClassReader("net.fabricmc.loader.FabricLoader");
                className = "net.fabricmc.loader.FabricLoader";
            }

            boolean usesImpl = className.contains(".impl.");
            String discoveryPackage = usesImpl ? "net/fabricmc/loader/impl/discovery/" : "net/fabricmc/loader/discovery/";

            reader.accept(classNode, 0);
            MethodNode setup = classNode.methods.stream()
                    .filter(m -> m.name.equals("setup"))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Could not find setup method"));

            String directoryModCandidateFinderClassName = discoveryPackage.replace('.', '/') + "DirectoryModCandidateFinder";

            TypeInsnNode directoryModCandidateFinder = (TypeInsnNode) Arrays.stream(setup.instructions.toArray())
                    .filter(i -> i.getOpcode() == Opcodes.NEW && ((TypeInsnNode) i).desc.equals(directoryModCandidateFinderClassName))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Could not find directory mod candidate finder"));

            AbstractInsnNode insertBefore = directoryModCandidateFinder // new
                    .getPrevious(); // aload

            String modDiscovererClassName = discoveryPackage + (usesImpl ? "ModDiscoverer" : "ModResolver");
            String modCandidateFinderClassName = discoveryPackage + "ModCandidateFinder";

            // add our own directory mod candidate finder
            String gameProviderClassName = "net/fabricmc/loader/" + (usesImpl ? "impl/" : "") + "game/GameProvider";
            List<AbstractInsnNode> instructions = Arrays.asList(
                    new VarInsnNode(Opcodes.ALOAD, usesImpl ? 2 : 1),
                    new TypeInsnNode(Opcodes.NEW, directoryModCandidateFinderClassName),
                    new InsnNode(Opcodes.DUP),
                    new VarInsnNode(Opcodes.ALOAD, 0),
                    new FieldInsnNode(Opcodes.GETFIELD, classNode.name, "gameDir", "Ljava/nio/file/Path;"),
                    new LdcInsnNode("mods"),
                    new MethodInsnNode(Opcodes.INVOKEINTERFACE, "java/nio/file/Path", "resolve", "(Ljava/lang/String;)Ljava/nio/file/Path;", true),
                    new VarInsnNode(Opcodes.ALOAD, 0),
                    new FieldInsnNode(Opcodes.GETFIELD, classNode.name, "provider", "L" + gameProviderClassName + ";"),
                    new MethodInsnNode(Opcodes.INVOKEINTERFACE, gameProviderClassName, "getRawGameVersion", "()Ljava/lang/String;", true),
                    new MethodInsnNode(Opcodes.INVOKEINTERFACE, "java/nio/file/Path", "resolve", "(Ljava/lang/String;)Ljava/nio/file/Path;", true),
                    // fun hack to support old versions of floader
                    new VarInsnNode(usesImpl ? Opcodes.ILOAD : Opcodes.ALOAD,  usesImpl ? 1 : 0),
                    usesImpl ? new InsnNode(Opcodes.NOP) : new MethodInsnNode(Opcodes.INVOKEVIRTUAL, classNode.name, "isDevelopmentEnvironment", "()Z", false),
                    new MethodInsnNode(Opcodes.INVOKESPECIAL, directoryModCandidateFinderClassName, "<init>", "(Ljava/nio/file/Path;Z)V", false),
                    new MethodInsnNode(Opcodes.INVOKEVIRTUAL, modDiscovererClassName, "addCandidateFinder", "(L" + modCandidateFinderClassName + ";)V", false)
            );
            for (AbstractInsnNode instruction : instructions) {
                setup.instructions.insertBefore(insertBefore, instruction);
            }

            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            classNode.accept(writer);
            byte[] bytes = writer.toByteArray();
            Files.write(Paths.get("./FabricLoader.class"), bytes);
            Util.defineClass(className, bytes, Thread.currentThread().getContextClassLoader(), ModifiedFabricLoaderImplDefiner.class.getProtectionDomain());
        } catch (Exception e) {
            throw Util.rethrow(e);
        }
    }
}
