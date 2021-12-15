package sschr15.fabricmods.tools.versionmodloader;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;

@SuppressWarnings("unchecked")
public class Util {
    // using generics to skip exception handling
    public static <T extends Throwable> RuntimeException rethrow(Throwable t) throws T {
        throw (T) t;
    }

    // use unsafe to define a new class
    private static final Class<?> unsafeClass;
    private static final Object theUnsafe; // sun.misc.Unsafe

    private static final Method defineClass;

    static {
        Class<?> clazz;
        Object obj;
        Method defineClassMethod;
        try {
            clazz = Class.forName("sun.misc.Unsafe");
            Field unsafeField = clazz.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            obj = unsafeField.get(null);
        } catch (Exception e) {
            throw rethrow(e);
        }
        unsafeClass = clazz;
        theUnsafe = obj;

        try {
            defineClassMethod = unsafeClass.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class, ClassLoader.class, ProtectionDomain.class);
            defineClassMethod.setAccessible(true);
        } catch (Exception e) {
            defineClassMethod = null;
        }
        defineClass = defineClassMethod;
    }

    public static Class<?> defineClass(String name, byte[] b, ClassLoader cl, ProtectionDomain pd) {
        try {
            if (defineClass == null) {
                // ok instead of unsafe we use reflection into the classloader
                Class<ClassLoader> clazz = ClassLoader.class;
                Method method = clazz.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class, ProtectionDomain.class);
                MethodHandle handle = Unsafe.trustedLookup.unreflect(method);
                return (Class<?>) handle.invokeExact(cl, name, b, 0, b.length, pd);
            } else {
                return (Class<?>) defineClass.invoke(theUnsafe, name, b, 0, b.length, cl, pd);
            }
        } catch (Throwable t) {
            throw rethrow(t);
        }
    }
}
