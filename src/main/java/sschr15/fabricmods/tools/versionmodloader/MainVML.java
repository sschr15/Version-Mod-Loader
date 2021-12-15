package sschr15.fabricmods.tools.versionmodloader;

public class MainVML {
    public static void main(String[] args) throws Throwable {
        // use questionable stuffs to force a modification to fabric loader
        ModifiedFabricLoaderImplDefiner.defineModifiedFabricLoaderImpl();

        Class<?> clazz = Class.forName(args[0]);
        String[] newArgs = new String[args.length - 1];
        System.arraycopy(args, 1, newArgs, 0, newArgs.length);
        clazz.getMethod("main", String[].class).invoke(null, (Object) newArgs);
    }
}
