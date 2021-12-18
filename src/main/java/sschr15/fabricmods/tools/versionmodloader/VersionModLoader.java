package sschr15.fabricmods.tools.versionmodloader;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.LanguageAdapter;
import net.fabricmc.loader.api.LanguageAdapterException;
import net.fabricmc.loader.api.ModContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static sschr15.fabricmods.tools.versionmodloader.Util.rethrow;

@SuppressWarnings("unused")
public class VersionModLoader implements LanguageAdapter {
    private static List<Path> versionSpecificMods;
    private static final Logger LOGGER = LogManager.getLogger("VersionModLoader");

    private static void init() throws Throwable {
        // this is the real reason we implement LanguageAdapter (really early init)
        LOGGER.info("Initializing VersionModLoader really early");

        if (System.getProperty("versionmodloader") == null) {
            // we must relaunch with -Dversionmodloader and using MainVML to force fabric to work the way we want
            LOGGER.info("Relaunching with -Dversionmodloader");

            String vmArgs = ManagementFactory.getRuntimeMXBean().getInputArguments()
                    .stream().filter(s -> !s.contains("-agentlib") && !s.contains("-javaagent"))
                    .collect(Collectors.joining(" "));
            String command = System.getProperty("sun.java.command").split(" ")[0];
            String java = System.getProperty("java.home") + "/bin/java";

            if (System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win")) {
                java = java.replace('/', '\\') + ".exe";
            }

            String cp = System.getProperty("java.class.path");
            if (!FabricLoader.getInstance().isDevelopmentEnvironment()) {
                // we need to add our jarfile to the classpath
                ModContainer container = FabricLoader.getInstance().getModContainer("version-mod-loader")
                        .orElseThrow(() -> new RuntimeException("Could not find jar file for version-mod-loader"));
                Path jar;
                try {
                    jar = container.getRootPath();
                } catch (RuntimeException e) {
                    // very old fabric crashes when we try to access root path too early
                    Class<?> modContainer = Class.forName("net.fabricmc.loader.ModContainer");
                    Method setupRootPath = modContainer.getDeclaredMethod("setupRootPath");
                    Field root = modContainer.getDeclaredField("root");
                    root.setAccessible(true);
                    setupRootPath.setAccessible(true);

                    setupRootPath.invoke(container);
                    jar = container.getRootPath();
                    root.set(container, null); // avoid floader crashing trying to setup root twice
                }

                FileSystem fs = jar.getFileSystem();
                cp += File.pathSeparator + fs.toString().replace('\\', '/');
            }

            if (command.equals("org.multimc.EntryPoint")) {
                // replace mmc's entrypoint with fabric's
                try {
                    Class.forName("net.fabricmc.loader.launch.knot.KnotClient");
                    command = "net.fabricmc.loader.launch.knot.KnotClient";
                } catch (ClassNotFoundException e) {
                    command = "net.fabricmc.loader.impl.launch.knot.KnotClient";
                }
            }
            String[] fabricReportedArgs = FabricLoader.getInstance().getLaunchArguments(false);

            // fix for funky mclauncher stuff
            if (vmArgs.contains("-Dos.name=")) {
                String beforeOsName = vmArgs.substring(0, vmArgs.indexOf("-Dos.name="));
                String afterXss = vmArgs.substring(vmArgs.indexOf("-Xss"));
                afterXss = afterXss.substring(afterXss.indexOf(' ') + 1); // skip the value and the space
                vmArgs = beforeOsName + afterXss;

                // fix weird space thing which convinces java it's done parsing vm args
                if (vmArgs.contains("-DFabricMcEmu= ")) {
                    vmArgs = vmArgs.replace("-DFabricMcEmu= ", "-DFabricMcEmu=");
                }
            }

            // fix for extra spaces
            vmArgs = vmArgs.replaceAll("\\s+", " ");

            List<String> entireCommand = new ArrayList<>();
            entireCommand.add(java);
            entireCommand.addAll(Arrays.asList(vmArgs.split(" ")));
            entireCommand.add("-Dversionmodloader"); // if it exists, we're fine
            entireCommand.add("-cp");
            entireCommand.add(cp);
            entireCommand.add(MainVML.class.getName()); // init with our own Main
            entireCommand.add(command); // then pass the original command on
            entireCommand.addAll(Arrays.asList(fabricReportedArgs)); // along with the original args

            if (System.getProperty("debug") != null) {
                LOGGER.info("Launching with command: " + String.join(" ", entireCommand));
            }

            Process process = new ProcessBuilder(entireCommand)
                    .inheritIO()
                    .start();

            try {
                while (process.isAlive()) {
                    //noinspection BusyWait
                    Thread.sleep(100);
                }
                System.exit(process.exitValue());
            } catch (InterruptedException e) {
                process.destroy();
                System.exit(1);
            }
        }
    }

    @Override
    public <T> T create(ModContainer mod, String value, Class<T> type) throws LanguageAdapterException {
        throw new LanguageAdapterException("This language adapter does not support creating objects.");
    }

    static {
        try {
            init();
        } catch (Throwable t) {
            throw rethrow(t);
        }
    }
}
