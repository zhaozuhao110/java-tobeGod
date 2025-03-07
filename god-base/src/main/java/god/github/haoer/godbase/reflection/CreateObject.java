package god.github.haoer.godbase.reflection;

import sun.misc.Unsafe;

import java.io.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Java创建对象的多种方式示例
 * 包含：new关键字、反射、克隆、序列化、MethodHandle、Unsafe等方式
 *
 * @author zhaozuhao
 * @version 1.0
 * @since 2025/3/7
 */
public class CreateObject {

    private static final String SERIALIZE_FILE_PATH = "/Users/zhaozuhao/haoer/javacode/java-tobeGod/god-base/src/main/java/god/github/haoer/godbase/reflection/user";

    public static void main(String[] args) throws Throwable {
        createUserByNew();
        createUserByReflect();
        createUserByClone();
        createUserBySerialize();
        createUserByMethod();
        createUserByUnsafe();
    }

    /**
     * 方式一：使用new关键字创建对象（最常用）
     */
    static void createUserByNew() {
        User user = new User("张三", 18);
        System.out.println("new方式创建对象: " + user);
    }

    /**
     * 方式二：使用反射方式创建对象
     */
    static void createUserByReflect() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // 方式2.1：使用Class.newInstance()方法（只能调用无参构造）
        User user = User.class.newInstance();

        // 方式2.2：使用Constructor创建对象（无参构造）
        Constructor<User> constructor = User.class.getConstructor();
        User user1 = constructor.newInstance();

        // 方式2.3：使用Constructor创建对象（有参构造）
        Constructor<User> constructor1 = User.class.getConstructor(String.class, Integer.class);
        User user2 = constructor1.newInstance("李四", 20);

        System.out.println("反射方式创建对象1: " + user);
        System.out.println("反射方式创建对象2: " + user1);
        System.out.println("反射方式创建对象3: " + user2);
    }

    /**
     * 方式三：使用克隆方式创建对象
     */
    static void createUserByClone() throws CloneNotSupportedException {
        User user = new User("王五", 22);
        User clone = (User) user.clone();
        System.out.println("克隆方式创建对象: " + clone);
    }

    /**
     * 方式四：使用序列化方式创建对象
     */
    static void createUserBySerialize() throws IOException {
        User user = new User("赵老六", 6);

        // 序列化
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(SERIALIZE_FILE_PATH)))) {
            oos.writeObject(user);
        }

        // 反序列化
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(SERIALIZE_FILE_PATH)))) {
            User deserializedUser = (User) ois.readObject();
            System.out.println("序列化方式创建对象: " + deserializedUser);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 方式五：使用MethodHandle方式创建对象
     * <p>
     * MethodHandle是Java 7引入的新特性，提供了一种更轻量级的方法调用机制。
     * <p>
     * ✅ **优势**：
     * 1. **性能较好**：相比反射性能更优，接近原生调用
     * 2. **类型安全**：在编译时进行类型检查
     * 3. **支持动态语言**：适合实现动态语言特性
     * 4. **更好的封装性**：相比反射更难破坏封装
     * <p>
     * ❌ **劣势**：
     * 1. **使用复杂**：需要理解MethodType和Lookup机制
     * 2. **学习成本高**：概念较多，不如反射直观
     * 3. **适用场景较窄**：主要用于框架开发和特殊场景
     * <p>
     * 🎯 **使用场景**：
     * 1. **动态语言支持**（如 Nashorn JavaScript引擎）
     * 2. **高性能框架开发**
     * 3. **需要频繁调用的动态方法**
     * <p>
     * 🏗 **工作原理**：
     * 1. **创建MethodType**：描述方法的参数和返回值类型
     * 2. **获取Lookup对象**：用于查找和访问方法
     * 3. **查找构造方法**：通过 `findConstructor` 获取构造器句柄
     * 4. **调用方法**：通过 `invoke` 执行构造
     *
     * @throws Throwable 可能抛出多种异常，统一使用Throwable处理
     */
    static void createUserByMethod() throws Throwable {
        // 1️⃣ 定义MethodType，表示构造方法的签名（无参数，返回void）
        MethodType methodType = MethodType.methodType(void.class);

        // 2️⃣ 获取MethodHandles.Lookup实例，用于查找方法
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        // 3️⃣ 通过MethodHandles查找User类的无参构造方法
        MethodHandle constructor = lookup.findConstructor(User.class, methodType);

        // 4️⃣ 通过invoke执行构造方法，创建User对象
        User user = (User) constructor.invoke();

        System.out.println("MethodHandle方式创建对象: " + user);

        // ✅ 额外示例：调用User类的实例方法
        MethodHandle sayHello = lookup.findVirtual(User.class, "sayHello", MethodType.methodType(void.class));
        sayHello.invoke(user);

        // ✅ 额外示例：调用User类的静态方法
        MethodHandle staticMethod = lookup.findStatic(User.class, "printInfo", MethodType.methodType(void.class));
        staticMethod.invoke();

        // ✅ 额外示例：修改User类的字段
        MethodHandle nameSetter = lookup.findSetter(User.class, "name", String.class);
        nameSetter.invoke(user, "张三");
    }

    /**
     * 方式六：使用Unsafe方式创建对象
     * <p>
     * Unsafe是Java提供的一个底层操作类，允许直接访问和操作内存。
     * <p>
     * 优势：
     * 1. 性能高：直接操作内存，绕过JVM的安全检查
     * 2. 可以绕过构造方法创建对象：适用于特殊场景（如反序列化）
     * 3. 可以修改final字段：突破Java语言限制
     * 4. 可以直接操作内存：适用于一些底层开发场景
     * <p>
     * 劣势：
     * 1. 不安全：可能会破坏对象的完整性，导致程序不稳定
     * 2. 可移植性差：依赖具体的JVM实现
     * 3. 可能在未来版本被限制使用
     * 4. 不遵循面向对象编程规范，破坏封装性
     * <p>
     * 使用场景：
     * 1. 框架开发（如Spring）
     * 2. 高性能组件开发
     * 3. 特殊场景下的对象创建（如反序列化）
     *
     * @throws NoSuchFieldException   反射获取字段失败时抛出
     * @throws IllegalAccessException 访问权限不够时抛出
     * @throws InstantiationException 实例化对象失败时抛出
     */
    static void createUserByUnsafe() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        // 1️⃣ 获取 Unsafe 实例（Unsafe 是 JVM 内部类，不能直接 new，只能通过反射访问）
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);  // 设置可访问
        Unsafe unsafe = (Unsafe) field.get(null);  // 获取 Unsafe 实例

        // 2️⃣ 获取 User 类中字段的内存偏移量（Unsafe 允许直接操作内存）
        // 获取字段的偏移量与对象实例化是两个独立的操作。
        // 字段的偏移量是在类加载时就已经确定的，它描述了字段在内存中的位置。
        // 因此，获取字段的偏移量与是否创建对象无关。
        // 当使用 Unsafe 创建对象后，可以利用这些已获取的偏移量直接操作对象的字段。
        long nameOffset = unsafe.objectFieldOffset(User.class.getDeclaredField("name"));
        long ageOffset = unsafe.objectFieldOffset(User.class.getDeclaredField("age"));

        // 3️⃣ 通过 Unsafe 直接创建 User 对象（不会调用构造方法）
        User user = (User) unsafe.allocateInstance(User.class);

        // 4️⃣ 直接在内存中修改对象的字段值（绕过 setter 方法）
        unsafe.putObject(user, nameOffset, "许老三");  // 设置 name = "许老三"
        unsafe.putInt(user, ageOffset, 30);  // 设置 age = 30

        // 5️⃣ 打印结果
        System.out.println("Unsafe 创建对象：" + user);
    }
}
