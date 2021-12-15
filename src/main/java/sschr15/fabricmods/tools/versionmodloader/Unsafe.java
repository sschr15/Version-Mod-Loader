package sschr15.fabricmods.tools.versionmodloader;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.security.ProtectionDomain;
import java.util.logging.Logger;

@SuppressWarnings({"unchecked", "unused", "RedundantSuppression", "ConstantConditions", "SameParameterValue", "CodeBlock2Expr", "Convert2MethodRef"})
public class Unsafe {
    public static final MethodHandles.Lookup trustedLookup;
    public static final Class<?> SunUnsafe;
    public static final Class<?> Unsafe;
    public static final Object theSunUnsafe;
    public static final Object theUnsafe;

    public static final int ARRAY_BOOLEAN_BASE_OFFSET;
    public static final int ARRAY_BYTE_BASE_OFFSET;
    public static final int ARRAY_SHORT_BASE_OFFSET;
    public static final int ARRAY_CHAR_BASE_OFFSET;
    public static final int ARRAY_INT_BASE_OFFSET;
    public static final int ARRAY_LONG_BASE_OFFSET;
    public static final int ARRAY_FLOAT_BASE_OFFSET;
    public static final int ARRAY_DOUBLE_BASE_OFFSET;
    public static final int ARRAY_OBJECT_BASE_OFFSET;

    public static final int ARRAY_BOOLEAN_INDEX_SCALE;
    public static final int ARRAY_BYTE_INDEX_SCALE;
    public static final int ARRAY_SHORT_INDEX_SCALE;
    public static final int ARRAY_CHAR_INDEX_SCALE;
    public static final int ARRAY_INT_INDEX_SCALE;
    public static final int ARRAY_LONG_INDEX_SCALE;
    public static final int ARRAY_FLOAT_INDEX_SCALE;
    public static final int ARRAY_DOUBLE_INDEX_SCALE;
    public static final int ARRAY_OBJECT_INDEX_SCALE;

    public static final int ADDRESS_SIZE;

    public static final int INVALID_FIELD_OFFSET = -1;

    private static final MethodHandle getObjectInt;
    private static final MethodHandle putObjectInt;
    private static final MethodHandle getObjectReference;
    private static final MethodHandle putObjectReference;
    private static final MethodHandle getObjectBoolean;
    private static final MethodHandle putObjectBoolean;
    private static final MethodHandle getObjectByte;
    private static final MethodHandle putObjectByte;
    private static final MethodHandle getObjectShort;
    private static final MethodHandle putObjectShort;
    private static final MethodHandle getObjectChar;
    private static final MethodHandle putObjectChar;
    private static final MethodHandle getObjectLong;
    private static final MethodHandle putObjectLong;
    private static final MethodHandle getObjectFloat;
    private static final MethodHandle putObjectFloat;
    private static final MethodHandle getObjectDouble;
    private static final MethodHandle putObjectDouble;
    private static final MethodHandle getByte;
    private static final MethodHandle putByte;
    private static final MethodHandle getShort;
    private static final MethodHandle putShort;
    private static final MethodHandle getChar;
    private static final MethodHandle putChar;
    private static final MethodHandle getInt;
    private static final MethodHandle putInt;
    private static final MethodHandle getLong;
    private static final MethodHandle putLong;
    private static final MethodHandle getFloat;
    private static final MethodHandle putFloat;
    private static final MethodHandle getDouble;
    private static final MethodHandle putDouble;
    private static final MethodHandle getAddress;
    private static final MethodHandle getObjectAddress;
    private static final MethodHandle putAddress;
    private static final MethodHandle putObjectAddress;
    private static final MethodHandle getUncompressedObject;
    private static final MethodHandle allocateMemory;
    private static final MethodHandle reallocateMemory;
    private static final MethodHandle setObjectMemory;
    private static final MethodHandle setMemory;
    private static final MethodHandle copyObjectMemory;
    private static final MethodHandle copyMemory;
    private static final MethodHandle freeMemory;
    private static final MethodHandle objectFieldOffset;
    private static final MethodHandle staticFieldOffset;
    private static final MethodHandle staticFieldBase;
    private static final MethodHandle shouldBeInitialized;
    private static final MethodHandle ensureClassInitialized;
    private static final MethodHandle arrayBaseOffset;
    private static final MethodHandle arrayIndexScale;
    private static final MethodHandle addressSize;
    private static final MethodHandle pageSize;
    private static final MethodHandle defineClass;
    private static final MethodHandle allocateInstance;
    private static final MethodHandle compareAndSwapReference;
    private static final MethodHandle compareAndSwapInt;
    private static final MethodHandle compareAndSwapLong;
    private static final MethodHandle getReferenceVolatile;
    private static final MethodHandle putReferenceVolatile;
    private static final MethodHandle getIntVolatile;
    private static final MethodHandle putIntVolatile;
    private static final MethodHandle getBooleanVolatile;
    private static final MethodHandle putBooleanVolatile;
    private static final MethodHandle getByteVolatile;
    private static final MethodHandle putByteVolatile;
    private static final MethodHandle getShortVolatile;
    private static final MethodHandle putShortVolatile;
    private static final MethodHandle getCharVolatile;
    private static final MethodHandle putCharVolatile;
    private static final MethodHandle getLongVolatile;
    private static final MethodHandle putLongVolatile;
    private static final MethodHandle getFloatVolatile;
    private static final MethodHandle putFloatVolatile;
    private static final MethodHandle getDoubleVolatile;
    private static final MethodHandle putDoubleVolatile;
    private static final MethodHandle putOrderedReference;
    private static final MethodHandle putOrderedInt;
    private static final MethodHandle putOrderedLong;
    private static final MethodHandle getLoadAverage;
    private static final MethodHandle getAndAddInt;
    private static final MethodHandle getAndAddLong;
    private static final MethodHandle getAndSetInt;
    private static final MethodHandle getAndSetLong;
    private static final MethodHandle getAndSetReference;
    private static final MethodHandle loadFence;
    private static final MethodHandle storeFence;
    private static final MethodHandle fullFence;
    private static final MethodHandle invokeCleaner;

    public static int getInt(Object o, long offset) {
        return get(() -> (int) getObjectInt.invokeExact(o, offset));
    }

    public static void putInt(Object o, long offset, int x) {
        run(() -> {putObjectInt.invokeExact(o, offset, x);});
    }

    @Deprecated
    public static <T> T getObject(Object o, long offset) {
        return get(() -> (T) getObjectReference.invokeExact(o, offset));
    }

    @Deprecated
    public static void putObject(Object o, long offset, Object x) {
        run(() -> {putObjectReference.invokeExact(o, offset, x);});
    }

    public static <T> T getReference(Object o, long offset) {
        return get(() -> (T) getObjectReference.invokeExact(o, offset));
    }

    public static void putReference(Object o, long offset, Object x) {
        run(() -> {putObjectReference.invokeExact(o, offset, x);});
    }

    public static boolean getBoolean(Object o, long offset) {
        return get(() -> (boolean) getObjectBoolean.invokeExact(o, offset));
    }

    public static void putBoolean(Object o, long offset, boolean x) {
        run(() -> {putObjectBoolean.invokeExact(o, offset, x);});
    }

    public static byte getByte(Object o, long offset) {
        return get(() -> (byte) getObjectByte.invokeExact(o, offset));
    }

    public static void putByte(Object o, long offset, byte x) {
        run(() -> {putObjectByte.invokeExact(o, offset, x);});
    }

    public static short getShort(Object o, long offset) {
        return get(() -> (short) getObjectShort.invokeExact(o, offset));
    }

    public static void putShort(Object o, long offset, short x) {
        run(() -> {putObjectShort.invokeExact(o, offset, x);});
    }

    public static char getChar(Object o, long offset) {
        return get(() -> (char) getObjectChar.invokeExact(o, offset));
    }

    public static void putChar(Object o, long offset, char x) {
        run(() -> {putObjectChar.invokeExact(o, offset, x);});
    }

    public static long getLong(Object o, long offset) {
        return get(() -> (long) getObjectLong.invokeExact(o, offset));
    }

    public static void putLong(Object o, long offset, long x) {
        run(() -> {putObjectLong.invokeExact(o, offset, x);});
    }

    public static float getFloat(Object o, long offset) {
        return get(() -> (float) getObjectFloat.invokeExact(o, offset));
    }

    public static void putFloat(Object o, long offset, float x) {
        run(() -> {putObjectFloat.invokeExact(o, offset, x);});
    }

    public static double getDouble(Object o, long offset) {
        return get(() -> (double) getObjectDouble.invokeExact(o, offset));
    }

    public static void putDouble(Object o, long offset, double x) {
        run(() -> {putObjectDouble.invokeExact(o, offset, x);});
    }

    public static byte getByte(long address) {
        return get(() -> (byte) getByte.invokeExact(address));
    }

    public static void putByte(long address, byte x) {
        run(() -> {putByte.invokeExact(address, x);});
    }

    public static short getShort(long address) {
        return get(() -> (short) getShort.invokeExact(address));
    }

    public static void putShort(long address, short x) {
        run(() -> {putShort.invokeExact(address, x);});
    }

    public static char getChar(long address) {
        return get(() -> (char) getChar.invokeExact(address));
    }

    public static void putChar(long address, char x) {
        run(() -> {putChar.invokeExact(address, x);});
    }

    public static int getInt(long address) {
        return get(() -> (int) getInt.invokeExact(address));
    }

    public static void putInt(long address, int x) {
        run(() -> {putInt.invokeExact(address, x);});
    }

    public static long getLong(long address) {
        return get(() -> (long) getLong.invokeExact(address));
    }

    public static void putLong(long address, long x) {
        run(() -> {putLong.invokeExact(address, x);});
    }

    public static float getFloat(long address) {
        return get(() -> (float) getFloat.invokeExact(address));
    }

    public static void putFloat(long address, float x) {
        run(() -> {putFloat.invokeExact(address, x);});
    }

    public static double getDouble(long address) {
        return get(() -> (double) getDouble.invokeExact(address));
    }

    public static void putDouble(long address, double x) {
        run(() -> {putDouble.invokeExact(address, x);});
    }

    public static long getAddress(long address) {
        return get(() -> (long) getAddress.invokeExact(address));
    }

    public static long getAddress(Object object, long address) {
        return get(() -> (long) getObjectAddress.invokeExact(object, address));
    }

    public static void putAddress(long address, long x) {
        run(() -> {putAddress.invokeExact(address, x);});
    }

    public static void putAddress(Object object, long address, long x) {
        run(() -> {putObjectAddress.invokeExact(object, address, x);});
    }

    public static <T> T getUncompressedObject(long address) {
        return get(() -> (T) getUncompressedObject.invokeExact(address));
    }

    public static long allocateMemory(long bytes) {
        return get(() -> (long) allocateMemory.invokeExact(bytes));
    }

    public static long reallocateMemory(long address, long bytes) {
        return get(() -> (long) reallocateMemory.invokeExact(address, bytes));
    }

    public static void setMemory(Object o, long offset, long bytes, byte value) {
        run(() -> {setObjectMemory.invokeExact(o, offset, bytes, value);});
    }

    public static void setMemory(long address, long bytes, byte value) {
        run(() -> {setMemory.invokeExact(address, bytes, value);});
    }

    public static void copyMemory(Object srcBase, long srcOffset, Object destBase, long destOffset, long bytes) {
        run(() -> {copyObjectMemory.invokeExact(srcBase, srcOffset, destBase, destOffset, bytes);});
    }

    public static void copyMemory(long srcAddress, long destAddress, long bytes) {
        run(() -> {copyMemory.invokeExact(srcAddress, destAddress, bytes);});
    }

    public static void freeMemory(long address) {
        run(() -> {freeMemory.invokeExact(address);});
    }

    public static long objectFieldOffset(Field field) {
        return get(() -> (long) objectFieldOffset.invokeExact(field));
    }

    public static long staticFieldOffset(Field field) {
        return get(() -> (long) staticFieldOffset.invokeExact(field));
    }

    public static Object staticFieldBase(Field field) {
        return get(() -> (Object) staticFieldBase.invokeExact(field));
    }

    public static boolean shouldBeInitialized(Class<?> klass) {
        return get(() -> (boolean) shouldBeInitialized.invokeExact(klass));
    }

    public static void ensureClassInitialized(Class<?> c) {
        run(() -> {ensureClassInitialized.invokeExact(c);});
    }

    public static int arrayBaseOffset(Class<?> arrayClass) {
        return get(() -> (int) arrayBaseOffset.invokeExact(arrayClass));
    }

    public static <T> int arrayIndexScale(Class<T> arrayClass) {
        return get(() -> (int) arrayIndexScale.invokeExact(arrayClass));
    }

    public static int addressSize() {
        return get(() -> (int) addressSize.invokeExact());
    }

    public static int pageSize() {
        return get(() -> (int) pageSize.invokeExact());
    }

    public static <T> Class<T> defineClass(String name, byte[] bytecode, int offset, int length, ClassLoader classLoader, ProtectionDomain protectionDomain) {
        return get(() -> (Class<T>) defineClass.invokeExact(name, bytecode, offset, length, classLoader, protectionDomain));
    }

    public static <T> T allocateInstance(Class<T> cls) {
        return get(() -> (T) allocateInstance.invokeExact(cls));
    }

    public static RuntimeException throwException(Throwable throwable) {
        return throw0(throwable);
    }

    public static boolean compareAndSwapObject(Object o, long offset, Object expected, Object x) {
        return get(() -> (boolean) compareAndSwapReference.invokeExact(o, offset, expected, x));
    }

    public static boolean compareAndSwapInt(Object o, long offset, int expected, int x) {
        return get(() -> (boolean) compareAndSwapInt.invokeExact(o, offset, expected, x));
    }

    public static boolean compareAndSwapLong(Object object, long offset, long expected, long x) {
        return get(() -> (boolean) compareAndSwapLong.invokeExact(object, offset, expected, x));
    }

    @Deprecated
    public static <T> T getObjectVolatile(Object object, long offset) {
        return getReferenceVolatile(object, offset);
    }

    @Deprecated
    public static void putObjectVolatile(Object o, long offset, Object x) {
        putReferenceVolatile(o, offset, x);
    }

    public static <T> T getReferenceVolatile(Object object, long offset) {
        return get(() -> (T) getReferenceVolatile.invokeExact(object, offset));
    }

    public static void putReferenceVolatile(Object o, long offset, Object x) {
        run(() -> {putReferenceVolatile.invokeExact(o, offset, x);});
    }

    public static int getIntVolatile(Object o, long offset) {
        return get(() -> (int) getIntVolatile.invokeExact(o, offset));
    }

    public static void putIntVolatile(Object o, long offset, int x) {
        run(() -> {putIntVolatile.invokeExact(o, offset, x);});
    }

    public static boolean getBooleanVolatile(Object o, long offset) {
        return get(() -> (boolean) getBooleanVolatile.invokeExact(o, offset));
    }

    public static void putBooleanVolatile(Object o, long offset, boolean x) {
        run(() -> {putBooleanVolatile.invokeExact(o, offset, x);});
    }

    public static byte getByteVolatile(Object o, long offset) {
        return get(() -> (byte) getByteVolatile.invokeExact(o, offset));
    }

    public static void putByteVolatile(Object o, long offset, byte x) {
        run(() -> {putByteVolatile.invokeExact(o, offset, x);});
    }

    public static short getShortVolatile(Object o, long offset) {
        return get(() -> (short) getShortVolatile.invokeExact(o, offset));
    }

    public static void putShortVolatile(Object o, long offset, short x) {
        run(() -> {putShortVolatile.invokeExact(o, offset, x);});
    }

    public static char getCharVolatile(Object o, long offset) {
        return get(() -> (char) getCharVolatile.invokeExact(o, offset));
    }

    public static void putCharVolatile(Object o, long offset, char x) {
        run(() -> {putCharVolatile.invokeExact(o, offset, x);});
    }

    public static long getLongVolatile(Object o, long offset) {
        return get(() -> (long) getLongVolatile.invokeExact(o, offset));
    }

    public static void putLongVolatile(Object o, long offset, long x) {
        run(() -> {putLongVolatile.invokeExact(o, offset, x);});
    }

    public static float getFloatVolatile(Object o, long offset) {
        return get(() -> (float) getFloatVolatile.invokeExact(o, offset));
    }

    public static void putFloatVolatile(Object o, long offset, float x) {
        run(() -> {putFloatVolatile.invokeExact(o, offset, x);});
    }

    public static double getDoubleVolatile(Object o, long offset) {
        return get(() -> (double) getDoubleVolatile.invokeExact(o, offset));
    }

    public static void putDoubleVolatile(Object o, long offset, double x) {
        run(() -> {putDoubleVolatile.invokeExact(o, offset, x);});
    }

    @Deprecated
    public static void putOrderedObject(Object o, long offset, Object x) {
        putOrderedReference(o, offset, x);
    }

    public static void putOrderedReference(Object o, long offset, Object x) {
        run(() -> {putOrderedReference.invokeExact(o, offset, x);});
    }

    public static void putOrderedInt(Object o, long offset, int x) {
        run(() -> {putOrderedInt.invokeExact(o, offset, x);});
    }

    public static void putOrderedLong(Object o, long offset, long x) {
        run(() -> {putOrderedLong.invokeExact(o, offset, x);});
    }

    public static int getLoadAverage(double[] loadavg, int nelems) {
        return get(() -> (int) getLoadAverage.invokeExact(loadavg, nelems));
    }
    public static int getAndAddInt(Object o, long offset, int delta) {
        return get(() -> (int) getAndAddInt.invokeExact(o, offset, delta));
    }

    public static long getAndAddLong(Object o, long offset, long delta) {
        return get(() -> (long) getAndAddLong.invokeExact(o, offset, delta));
    }

    public static int getAndSetInt(Object o, long offset, int newValue) {
        return get(() -> (int) getAndSetInt.invokeExact(o, offset, newValue));
    }

    public static long getAndSetLong(Object o, long offset, long newValue) {
        return get(() -> (long) getAndSetLong.invokeExact(o, offset, newValue));
    }

    @Deprecated
    public static <T> T getAndSetObject(Object o, long offset, T newValue) {
        return getAndSetReference(o, offset, newValue);
    }

    public static <T> T getAndSetReference(Object o, long offset, T newValue) {
        return get(() -> (T) getAndSetReference.invokeExact(o, offset, (Object) newValue));
    }

    public static void loadFence() {
        run(() -> {loadFence.invokeExact();});
    }

    public static void storeFence() {
        run(() -> {storeFence.invokeExact();});
    }

    public static void fullFence() {
        run(() -> {fullFence.invokeExact();});
    }

    public static void invokeCleaner(ByteBuffer directBuffer) {
        run(() -> {invokeCleaner.invokeExact(directBuffer);});
    }

    private static <T extends Throwable> T throw0(Throwable throwable) throws T {
        throw (T) throwable;
    }

    private static void run(Runnable runnable) {
        try {
            runnable.run();
        } catch (Throwable throwable) {
            throw throwException(throwable);
        }
    }

    private static <T> T get(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Throwable throwable) {
            throw throwException(throwable);
        }
    }

    private static MethodHandle bind(String method, String alternative, Class<?> returnType, Class<?>... parameterTypes) {
        try {
            try {
                return trustedLookup.bind(theUnsafe, method, MethodType.methodType(returnType, parameterTypes));
            } catch (NoSuchMethodException exception) {
                return trustedLookup.bind(theSunUnsafe, method, MethodType.methodType(returnType, parameterTypes));
            }
        } catch (Throwable throwable) {
            if (alternative == null) {
                Logger.getLogger("Unsafe").warning(String.format("Unable to access Unsafe method %s%s.%n", method, MethodType.methodType(returnType, parameterTypes)));
                throwable.printStackTrace();

                return null;
            }

            return bind(method, null, returnType, parameterTypes);
        }
    }

    private static MethodHandle bind(String method, Class<?> returnType, Class<?>... parameterTypes) {
        return bind(method, null, returnType, parameterTypes);
    }

    private interface Runnable {
        void run() throws Throwable;
    }

    private interface Supplier<T> {
        T get() throws Throwable;
    }

    static {
        try {
            SunUnsafe = Class.forName("sun.misc.Unsafe");
            Field field = SunUnsafe.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            theSunUnsafe = field.get(null);
            Method getObject = SunUnsafe.getDeclaredMethod("getObject", Object.class, long.class);
            Method statFieldOffset = SunUnsafe.getDeclaredMethod("staticFieldOffset", Field.class);
            //noinspection RedundantCast
            trustedLookup = (MethodHandles.Lookup) getObject.invoke(
                    theSunUnsafe,
                    MethodHandles.Lookup.class,
                    (long) statFieldOffset.invoke(theSunUnsafe, MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP"))
            );

            Unsafe = Class.forName("jdk.internal.misc.Unsafe");
            theUnsafe = trustedLookup.findStatic(Unsafe, "getUnsafe", MethodType.methodType(Unsafe)).invoke();

            getObjectInt = bind("getInt", int.class, Object.class, long.class);
            getObjectReference = bind("getReference", Object.class, Object.class, long.class);
            getObjectBoolean = bind("getBoolean", boolean.class, Object.class, long.class);
            getObjectByte = bind("getByte", byte.class, Object.class, long.class);
            getObjectShort = bind("getShort", short.class, Object.class, long.class);
            getObjectChar = bind("getChar", char.class, Object.class, long.class);
            getObjectLong = bind("getLong", long.class, Object.class, long.class);
            getObjectFloat = bind("getFloat", float.class, Object.class, long.class);
            getObjectDouble = bind("getDouble", double.class, Object.class, long.class);

            putObjectInt = bind("putInt", void.class, Object.class, long.class, int.class);
            putObjectReference = bind("putReference", void.class, Object.class, long.class, Object.class);
            putObjectBoolean = bind("putBoolean", void.class, Object.class, long.class, boolean.class);
            putObjectByte = bind("putByte", void.class, Object.class, long.class, byte.class);
            putObjectShort = bind("putShort", void.class, Object.class, long.class, short.class);
            putObjectChar = bind("putChar", void.class, Object.class, long.class, char.class);
            putObjectLong = bind("putLong", void.class, Object.class, long.class, long.class);
            putObjectFloat = bind("putFloat", void.class, Object.class, long.class, float.class);
            putObjectDouble = bind("putDouble", void.class, Object.class, long.class, double.class);

            getByte = bind("getByte", byte.class, long.class);
            getShort = bind("getShort", short.class, long.class);
            getChar = bind("getChar", char.class, long.class);
            getInt = bind("getInt", int.class, long.class);
            getLong = bind("getLong", long.class, long.class);
            getFloat = bind("getFloat", float.class, long.class);
            getDouble = bind("getDouble", double.class, long.class);
            getAddress = bind("getAddress", long.class, long.class);
            getObjectAddress = bind("getAddress", long.class, Object.class, long.class);

            putByte = bind("putByte", void.class, long.class, byte.class);
            putShort = bind("putShort", void.class, long.class, short.class);
            putChar = bind("putChar", void.class, long.class, char.class);
            putInt = bind("putInt", void.class, long.class, int.class);
            putLong = bind("putLong", void.class, long.class, long.class);
            putFloat = bind("putFloat", void.class, long.class, float.class);
            putDouble = bind("putDouble", void.class, long.class, double.class);
            putAddress = bind("putAddress", void.class, long.class, long.class);
            putObjectAddress = bind("putAddress", void.class, Object.class, long.class, long.class);

            getUncompressedObject = bind("getUncompressedObject", Object.class, long.class);
            allocateMemory = bind("allocateMemory", long.class, long.class);
            reallocateMemory = bind("reallocateMemory", long.class, long.class, long.class);
            setObjectMemory = bind("setMemory", void.class, Object.class, long.class, long.class, byte.class);
            setMemory = bind("setMemory", void.class, long.class, long.class, byte.class);
            copyObjectMemory = bind("copyMemory", void.class, Object.class, long.class, Object.class, long.class, long.class);
            copyMemory = bind("copyMemory", void.class, long.class, long.class, long.class);
            freeMemory = bind("freeMemory", void.class, long.class);
            objectFieldOffset = bind("objectFieldOffset", long.class, Field.class);
            staticFieldOffset = bind("staticFieldOffset", long.class, Field.class);
            staticFieldBase = bind("staticFieldBase", Object.class, Field.class);
            shouldBeInitialized = bind("shouldBeInitialized", boolean.class, Class.class);
            ensureClassInitialized = bind("ensureClassInitialized", void.class, Class.class);
            arrayBaseOffset = bind("arrayBaseOffset", int.class, Class.class);
            arrayIndexScale = bind("arrayIndexScale", int.class, Class.class);
            addressSize = bind("addressSize", int.class);
            pageSize = bind("pageSize", int.class);
            defineClass = bind("defineClass", Class.class, String.class, byte[].class, int.class, int.class, ClassLoader.class, ProtectionDomain.class);
            allocateInstance = bind("allocateInstance", Object.class, Class.class);

            getReferenceVolatile = bind("getReferenceVolatile", Object.class, Object.class, long.class);
            getIntVolatile = bind("getIntVolatile", int.class, Object.class, long.class);
            getBooleanVolatile = bind("getBooleanVolatile", boolean.class, Object.class, long.class);
            getByteVolatile = bind("getByteVolatile", byte.class, Object.class, long.class);
            getShortVolatile = bind("getShortVolatile", short.class, Object.class, long.class);
            getCharVolatile = bind("getCharVolatile", char.class, Object.class, long.class);
            getLongVolatile = bind("getLongVolatile", long.class, Object.class, long.class);
            getFloatVolatile = bind("getFloatVolatile", float.class, Object.class, long.class);
            getDoubleVolatile = bind("getDoubleVolatile", double.class, Object.class, long.class);

            putReferenceVolatile = bind("putReferenceVolatile", void.class, Object.class, long.class, Object.class);
            putIntVolatile = bind("putIntVolatile", void.class, Object.class, long.class, int.class);
            putBooleanVolatile = bind("putBooleanVolatile", void.class, Object.class, long.class, boolean.class);
            putByteVolatile = bind("putByteVolatile", void.class, Object.class, long.class, byte.class);
            putShortVolatile = bind("putShortVolatile", void.class, Object.class, long.class, short.class);
            putCharVolatile = bind("putCharVolatile", void.class, Object.class, long.class, char.class);
            putLongVolatile = bind("putLongVolatile", void.class, Object.class, long.class, long.class);
            putFloatVolatile = bind("putFloatVolatile", void.class, Object.class, long.class, float.class);
            putDoubleVolatile = bind("putDoubleVolatile", void.class, Object.class, long.class, double.class);

            compareAndSwapReference = bind("compareAndSetReference", boolean.class, Object.class, long.class, Object.class, Object.class);
            compareAndSwapInt = bind("compareAndSetInt", boolean.class, Object.class, long.class, int.class, int.class);
            compareAndSwapLong = bind("compareAndSetLong", boolean.class, Object.class, long.class, long.class, long.class);

            putOrderedReference = bind("putReferenceRelease", void.class, Object.class, long.class, Object.class);
            putOrderedInt = bind("putIntRelease", void.class, Object.class, long.class, int.class);
            putOrderedLong = bind("putLongRelease", void.class, Object.class, long.class, long.class);

            getLoadAverage = bind("getLoadAverage", int.class, double[].class, int.class);
            getAndAddInt = bind("getAndAddInt", int.class, Object.class, long.class, int.class);
            getAndAddLong = bind("getAndAddLong", long.class, Object.class, long.class, long.class);
            getAndSetInt = bind("getAndSetInt", int.class, Object.class, long.class, int.class);
            getAndSetLong = bind("getAndSetLong", long.class, Object.class, long.class, long.class);
            getAndSetReference = bind("getAndSetReference", Object.class, Object.class, long.class, Object.class);

            loadFence = bind("loadFence", void.class);
            storeFence = bind("storeFence", void.class);
            fullFence = bind("fullFence", void.class);

            invokeCleaner = bind("invokeCleaner", void.class, ByteBuffer.class);

            ARRAY_BOOLEAN_BASE_OFFSET = arrayBaseOffset(boolean[].class);
            ARRAY_BYTE_BASE_OFFSET = arrayBaseOffset(byte[].class);
            ARRAY_SHORT_BASE_OFFSET = arrayBaseOffset(short[].class);
            ARRAY_CHAR_BASE_OFFSET = arrayBaseOffset(char[].class);
            ARRAY_INT_BASE_OFFSET = arrayBaseOffset(int[].class);
            ARRAY_LONG_BASE_OFFSET = arrayBaseOffset(long[].class);
            ARRAY_FLOAT_BASE_OFFSET = arrayBaseOffset(float[].class);
            ARRAY_DOUBLE_BASE_OFFSET = arrayBaseOffset(double[].class);
            ARRAY_OBJECT_BASE_OFFSET = arrayBaseOffset(Object[].class);

            ARRAY_BOOLEAN_INDEX_SCALE = arrayIndexScale(boolean[].class);
            ARRAY_BYTE_INDEX_SCALE = arrayIndexScale(byte[].class);
            ARRAY_SHORT_INDEX_SCALE = arrayIndexScale(short[].class);
            ARRAY_CHAR_INDEX_SCALE = arrayIndexScale(char[].class);
            ARRAY_INT_INDEX_SCALE = arrayIndexScale(int[].class);
            ARRAY_LONG_INDEX_SCALE = arrayIndexScale(long[].class);
            ARRAY_FLOAT_INDEX_SCALE = arrayIndexScale(float[].class);
            ARRAY_DOUBLE_INDEX_SCALE = arrayIndexScale(double[].class);
            ARRAY_OBJECT_INDEX_SCALE = arrayIndexScale(Object[].class);

            ADDRESS_SIZE = addressSize();
        } catch (Throwable throwable) {
            throw throwException(throwable);
        }
    }
}
