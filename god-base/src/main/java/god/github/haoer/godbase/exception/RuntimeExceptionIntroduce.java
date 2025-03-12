package god.github.haoer.godbase.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Java运行时异常介绍
 * <p>
 * 本类系统地介绍了Java中常见的运行时异常，包括：
 * - 异常的名称和描述
 * - 典型的触发场景和示例代码
 * - 异常产生的原因分析
 * - 避免异常的最佳实践
 * 
 * @author zhaozuhao
 * @version 1.0
 * @date 2025/3/12 09:30
 */
public class RuntimeExceptionIntroduce {

    //==========================================================================
    //                    1. 数组和集合操作相关异常
    //==========================================================================
    
    /**
     * 数组和集合操作相关异常示例
     * <p>
     * 包括：ArrayStoreException, IndexOutOfBoundsException, 
     * NegativeArraySizeException, EmptyStackException, NoSuchElementException等
     */
    public void arrayAndCollectionExceptions() {
        
        //-------------------------------------------------------------------------
        // 1.1 ArrayStoreException: 向数组中存储与声明类型不兼容的对象时抛出
        //-------------------------------------------------------------------------
        /*
         * 【示例代码】
         * Object[] stringArray = new String[3];
         * stringArray[0] = "Hello"; // 正常
         * stringArray[1] = 100;     // 抛出ArrayStoreException
         *
         * 【原因分析】
         * 虽然声明为Object[]类型，但实际创建的是String[]数组，
         * 运行时会检查元素类型是否兼容。
         *
         * 【避免方法】
         * - 确保添加到数组中的元素类型与数组声明的类型兼容
         */

        //-------------------------------------------------------------------------
        // 1.2 IndexOutOfBoundsException: 访问数组或集合时索引越界
        //-------------------------------------------------------------------------
        /*
         * 【示例代码】
         * // 数组索引越界
         * int[] array = new int[5];
         * array[5] = 10;  // 抛出ArrayIndexOutOfBoundsException，因为索引范围是0-4
         *
         * // 字符串索引越界
         * String str = "Hello";
         * char c = str.charAt(5);  // 抛出StringIndexOutOfBoundsException，因为索引范围是0-4
         *
         * // 集合索引越界
         * List<String> list = new ArrayList<>();
         * list.add("A");
         * String s = list.get(1);  // 抛出IndexOutOfBoundsException，因为只有一个元素
         *
         * 【原因分析】
         * 尝试访问超出合法范围的索引位置。
         *
         * 【避免方法】
         * - 在访问前检查索引是否有效
         * - 使用循环时确保边界条件正确
         * - 对于字符串和集合，使用length()/size()方法确认大小
         */

        //-------------------------------------------------------------------------
        // 1.3 NegativeArraySizeException: 创建大小为负数的数组时抛出
        //-------------------------------------------------------------------------
        /*
         * 【示例代码】
         * int size = -5;
         * int[] array = new int[size];  // 抛出NegativeArraySizeException
         *
         * 【原因分析】
         * Java不允许创建负大小的数组，这通常是由于计算数组大小的逻辑错误导致。
         *
         * 【避免方法】
         * - 在创建数组前验证大小是否为正数
         * - 使用Math.max(size, 0)确保大小至少为0
         */

        //-------------------------------------------------------------------------
        // 1.4 EmptyStackException: 当栈为空时执行pop或peek操作时抛出
        //-------------------------------------------------------------------------
        /*
         * 【示例代码】
         * Stack<Integer> stack = new Stack<>();
         * stack.pop();  // 抛出EmptyStackException，因为栈是空的
         *
         * 【原因分析】
         * 尝试从空栈中获取元素。
         *
         * 【避免方法】
         * - 在执行pop或peek操作前使用empty()或size()方法检查栈是否为空
         * - 使用try-catch块捕获异常
         */

        //-------------------------------------------------------------------------
        // 1.5 NoSuchElementException: 访问集合中不存在的元素时抛出
        //-------------------------------------------------------------------------
        /*
         * 【示例代码】
         * // 队列操作
         * Queue<String> queue = new LinkedList<>();
         * String element = queue.element();  // 抛出NoSuchElementException，因为队列为空
         *
         * // 迭代器操作
         * Iterator<String> iterator = new ArrayList<String>().iterator();
         * String next = iterator.next();  // 抛出NoSuchElementException，因为没有下一个元素
         *
         * 【原因分析】
         * 尝试访问不存在的元素，如空队列的首元素或已到达末尾的迭代器的下一个元素。
         *
         * 【避免方法】
         * - 使用安全的替代方法，如peek()代替element()，poll()代替remove()
         * - 在调用next()前使用hasNext()检查是否有下一个元素
         * - 使用增强for循环代替显式迭代器，避免手动管理迭代状态
         */
    }

    //==========================================================================
    //                    2. 类型转换和反射相关异常
    //==========================================================================
    
    /**
     * 类型转换和反射相关异常示例
     * <p>
     * 包括：ClassCastException, IllegalArgumentException, TypeNotPresentException,
     * EnumConstantNotPresentException, WrongMethodTypeException, IncompleteAnnotationException等
     */
    public void typeAndReflectionExceptions() {
        
        //-------------------------------------------------------------------------
        // 2.1 ClassCastException: 强制类型转换失败时抛出
        //-------------------------------------------------------------------------
        /*
         * 【示例代码】
         * Object obj = new Integer(10);
         * String str = (String) obj;  // 抛出ClassCastException，因为Integer不能转换为String
         *
         * 【原因分析】
         * 尝试将对象强制转换为不兼容的类型。
         *
         * 【避免方法】
         * - 在转换前使用instanceof运算符检查对象类型
         * - 使用泛型避免不必要的类型转换
         * - 在设计时考虑类型安全，避免过度依赖向下转型
         */

        //-------------------------------------------------------------------------
        // 2.2 IllegalArgumentException: 向方法传递非法参数时抛出
        //-------------------------------------------------------------------------
        /*
         * 【示例代码】
         * Thread.sleep(-1000);  // 抛出IllegalArgumentException，因为睡眠时间不能为负
         *
         * new ArrayList<>(-5);  // 抛出IllegalArgumentException，因为初始容量不能为负
         *
         * 【原因分析】
         * 方法参数不符合预期的条件或范围。
         *
         * 【避免方法】
         * - 在调用方法前验证参数的有效性
         * - 查阅API文档了解参数的有效范围和约束
         * - 自己编写方法时，对参数进行明确的验证并抛出适当的异常
         */

        //-------------------------------------------------------------------------
        // 2.3 TypeNotPresentException: 类型不存在时抛出
        //-------------------------------------------------------------------------
        /*
         * 【示例代码】
         * // 当使用Class.forName()加载不存在的类时:
         * try {
         *     Class.forName("com.example.NonExistentClass");
         * } catch (ClassNotFoundException e) {
         *     // 处理异常
         * }
         *
         * // 或者在使用注解时引用不存在的类:
         * @SomeAnnotation(value = NonExistentClass.class)
         *
         * 【原因分析】
         * 尝试引用不存在的类型。
         *
         * 【避免方法】
         * - 确保引用的类存在于类路径中
         * - 使用try-catch块处理可能的类加载异常
         * - 在使用反射或注解时仔细检查类型引用
         */

        //-------------------------------------------------------------------------
        // 2.4 EnumConstantNotPresentException: 枚举常量不存在时抛出
        //-------------------------------------------------------------------------
        /*
         * 【示例代码】
         * enum Color { RED, GREEN, BLUE }
         *
         * String name = "YELLOW";
         * Color color = Enum.valueOf(Color.class, name);  // 抛出EnumConstantNotPresentException
         *
         * 【原因分析】
         * 尝试获取枚举类中不存在的常量。
         *
         * 【避免方法】
         * - 使用try-catch块捕获可能的异常
         * - 在转换前验证常量名是否有效
         * - 考虑使用枚举的values()方法获取所有有效值进行比较
         */

        //-------------------------------------------------------------------------
        // 2.5 WrongMethodTypeException: 方法句柄调用类型不匹配时抛出
        //-------------------------------------------------------------------------
        /*
         * 【示例代码】
         * // 使用MethodHandle API时:
         * MethodHandles.Lookup lookup = MethodHandles.lookup();
         * MethodType mt = MethodType.methodType(String.class, int.class);
         * try {
         *     MethodHandle mh = lookup.findVirtual(String.class, "substring", mt);
         *     // 错误调用，参数类型不匹配
         *     String result = (String) mh.invoke("Hello", "World");  // 抛出WrongMethodTypeException
         * } catch (Throwable e) {
         *     // 处理异常
         * }
         *
         * 【原因分析】
         * 使用方法句柄时，调用的参数类型与方法签名不匹配。
         *
         * 【避免方法】
         * - 确保方法句柄的调用类型与目标方法的签名匹配
         * - 使用正确的MethodType定义方法签名
         * - 在调用前验证参数类型
         */

        //-------------------------------------------------------------------------
        // 2.6 IncompleteAnnotationException: 注解缺少必需的元素时抛出
        //-------------------------------------------------------------------------
        /*
         * 【示例代码】
         * // 定义一个需要value属性的注解
         * @interface RequiredAnnotation {
         *     String value();  // 必需的元素
         * }
         *
         * // 使用注解但未提供必需的元素
         * @RequiredAnnotation  // 编译错误，缺少value元素
         * class MyClass { }
         *
         * 【原因分析】
         * 使用注解时未提供其声明的必需元素。
         *
         * 【避免方法】
         * - 确保提供注解的所有必需元素
         * - 如果某些元素是可选的，在注解定义中提供默认值
         * - 查阅注解文档了解其必需元素
         */
    }

    //==========================================================================
    //                    3. 并发操作相关异常
    //==========================================================================
    
    /**
     * 并发操作相关异常示例
     * <p>
     * 包括：ConcurrentModificationException, IllegalMonitorStateException, 
     * RejectedExecutionException等
     */
    public void concurrencyExceptions() {
        
        //-------------------------------------------------------------------------
        // 3.1 ConcurrentModificationException: 并发修改集合时抛出
        //-------------------------------------------------------------------------
        /*
         * 【示例代码】
         * List<String> list = new ArrayList<>();
         * list.add("A"); list.add("B"); list.add("C");
         *
         * // 错误方式：在foreach循环中直接修改集合
         * for (String item : list) {
         *     if ("B".equals(item)) {
         *         list.remove(item);  // 抛出ConcurrentModificationException
         *     }
         * }
         *
         * 【原因分析】
         * 在迭代过程中修改集合结构（添加、删除元素）会导致迭代器状态与集合状态不一致。
         *
         * 【避免方法】
         * - 使用Iterator的remove()方法而不是集合的remove()方法
         * - 使用CopyOnWriteArrayList等并发安全的集合类
         * - 使用Stream API的filter()方法过滤元素
         * - 在遍历前创建集合的副本进行操作
         */

        //-------------------------------------------------------------------------
        // 3.2 IllegalMonitorStateException: 在没有同步的情况下调用wait/notify/notifyAll方法
        //-------------------------------------------------------------------------
        /*
         * 【示例代码】
         * Object obj = new Object();
         * obj.wait();  // 抛出IllegalMonitorStateException，因为没有获取obj的监视器锁
         *
         * 【原因分析】
         * wait()、notify()和notifyAll()方法必须在同步块或同步方法中调用，且必须是对调用这些方法的对象加锁。
         *
         * 【避免方法】
         * - 确保在调用wait/notify/notifyAll方法前获取对象的监视器锁
         * - 使用synchronized块或方法包裹这些调用
         * - 考虑使用java.util.concurrent包中的高级并发工具，如Lock、Condition等
         */

        //-------------------------------------------------------------------------
        // 3.3 RejectedExecutionException: 线程池拒绝任务时抛出
        //-------------------------------------------------------------------------
        /*
         * 【示例代码】
         * ExecutorService executor = Executors.newFixedThreadPool(2);
         * executor.shutdown();
         * executor.execute(() -> System.out.println("Task"));  // 抛出RejectedExecutionException
         *
         * 【原因分析】
         * 线程池已关闭或已达到最大容量且拒绝策略为AbortPolicy时，会拒绝新提交的任务。
         *
         * 【避免方法】
         * - 在提交任务前检查线程池是否已关闭
         * - 使用合适的拒绝策略，如CallerRunsPolicy
         * - 适当调整线程池大小和队列容量
         * - 实现自定义的RejectedExecutionHandler处理被拒绝的任务
         */
    }

    //==========================================================================
    //                    4. 状态相关异常
    //==========================================================================
    
    /**
     * 状态相关异常示例
     * <p>
     * 包括：IllegalStateException, UnsupportedOperationException, 
     * SecurityException等
     */
    public void stateExceptions() {
        
        //-------------------------------------------------------------------------
        // 4.1 IllegalStateException: 对象状态不适合请求的操作时抛出
        //-------------------------------------------------------------------------
        /*
         * 【示例代码】
         * Scanner scanner = new Scanner(System.in);
         * scanner.close();
         * scanner.nextLine();  // 抛出IllegalStateException，因为Scanner已关闭
         *
         * 【原因分析】
         * 对象处于不适合执行特定操作的状态。
         *
         * 【避免方法】
         * - 在执行操作前检查对象的状态
         * - 遵循对象的生命周期和状态转换规则
         * - 使用try-with-resources自动管理资源的关闭
         */

        //-------------------------------------------------------------------------
        // 4.2 UnsupportedOperationException: 对象不支持请求的操作时抛出
        //-------------------------------------------------------------------------
        /*
         * 【示例代码】
         * List<String> list = Collections.unmodifiableList(new ArrayList<>());
         * list.add("Item");  // 抛出UnsupportedOperationException，因为列表是不可修改的
         *
         * 【原因分析】
         * 尝试执行对象不支持的操作，通常是因为对象是只读的或功能受限的。
         *
         * 【避免方法】
         * - 了解集合的特性，区分可变和不可变集合
         * - 在使用不熟悉的API时查阅文档了解支持的操作
         * - 在设计API时，为不支持的操作提供明确的文档说明
         */

        //-------------------------------------------------------------------------
        // 4.3 SecurityException: 违反安全策略时抛出
        //-------------------------------------------------------------------------
        /*
         * 【示例代码】
         * // 尝试修改系统属性但没有权限
         * System.setProperty("java.home", "/new/path");  // 可能抛出SecurityException
         *
         * // 尝试访问文件但没有权限
         * new FileInputStream("/etc/shadow");  // 可能抛出SecurityException
         *
         * 【原因分析】
         * 代码尝试执行当前安全策略不允许的操作。
         *
         * 【避免方法】
         * - 了解应用程序运行环境的安全限制
         * - 使用try-catch块捕获可能的SecurityException
         * - 在需要特权操作时，考虑使用AccessController.doPrivileged()
         * - 设计应用时考虑最小权限原则
         */
    }

    //==========================================================================
    //                    5. 数据操作相关异常
    //==========================================================================
    
    /**
     * 数据操作相关异常示例
     * <p>
     * 包括：NumberFormatException, ArithmeticException, 
     * NullPointerException等
     */
    public void dataOperationExceptions() {
        
        //-------------------------------------------------------------------------
        // 5.1 NumberFormatException: 字符串转换为数字格式失败时抛出
        //-------------------------------------------------------------------------
        /*
         * 【示例代码】
         * String notANumber = "abc";
         * int value = Integer.parseInt(notANumber);  // 抛出NumberFormatException
         *
         * 【原因分析】
         * 尝试将不符合数字格式的字符串转换为数值类型。
         *
         * 【避免方法】
         * - 在转换前验证字符串格式
         * - 使用正则表达式检查字符串是否符合数字格式
         * - 使用try-catch块捕获可能的异常
         * - 考虑使用Integer.valueOf()的替代方法，如Apache Commons的NumberUtils
         */

        //-------------------------------------------------------------------------
        // 5.2 ArithmeticException: 算术运算异常，如除以零
        //-------------------------------------------------------------------------
        /*
         * 【示例代码】
         * int result = 10 / 0;  // 抛出ArithmeticException: / by zero
         *
         * 【原因分析】
         * 执行无效的算术运算，最常见的是整数除以零。
         *
         * 【避免方法】
         * - 在执行除法前检查除数是否为零
         * - 使用安全的数学运算库，如Math.floorDiv()
         * - 对于可能的边界情况提供默认值或特殊处理
         */

        //-------------------------------------------------------------------------
        // 5.3 NullPointerException: 访问null对象的方法或属性时抛出
        //-------------------------------------------------------------------------
        /*
         * 【示例代码】
         * String str = null;
         * int length = str.length();  // 抛出NullPointerException
         *
         * 【原因分析】
         * 尝试访问null引用的方法或属性。
         *
         * 【避免方法】
         * - 在访问对象前检查是否为null
         * - 使用Objects.requireNonNull()验证参数
         * - 使用Optional<T>处理可能为null的值
         * - 在Java 8+中使用Stream API的filter(Objects::nonNull)过滤null值
         * - 遵循防御性编程原则，不假设输入总是有效的
         */
    }

    //==========================================================================
    //                    6. IO和资源相关异常
    //==========================================================================
    
    /**
     * IO和资源相关异常示例
     * <p>
     * 包括：IOException, FileNotFoundException, EOFException等
     */
    public void ioExceptions() {
        
        //-------------------------------------------------------------------------
        // 6.1 IOException: 输入输出操作失败时抛出
        //-------------------------------------------------------------------------
        /*
         * 【示例代码】
         * try {
         *     FileWriter writer = new FileWriter("/path/to/nonexistent/directory/file.txt");
         *     writer.write("Hello");
         *     writer.close();
         * } catch (IOException e) {
         *     e.printStackTrace();
         * }
         *
         * 【原因分析】
         * IO操作失败，可能是因为文件不存在、权限不足、磁盘已满等原因。
         *
         * 【避免方法】
         * - 使用try-with-resources自动关闭资源
         * - 在操作前检查文件或目录是否存在
         * - 使用Files类的现代API代替传统IO
         * - 正确处理异常，不要忽略IOException
         */

        //-------------------------------------------------------------------------
        // 6.2 FileNotFoundException: 尝试打开不存在的文件时抛出
        //-------------------------------------------------------------------------
        /*
         * 【示例代码】
         * try {
         *     FileInputStream fis = new FileInputStream("nonexistent.txt");
         * } catch (FileNotFoundException e) {
         *     e.printStackTrace();
         * }
         *
         * 【原因分析】
         * 尝试访问不存在的文件或没有足够权限访问的文件。
         *
         * 【避免方法】
         * - 在打开文件前使用File.exists()检查文件是否存在
         * - 提供用户友好的错误消息，指导用户创建或提供正确的文件
         * - 考虑使用默认资源或备用文件
         */

        //-------------------------------------------------------------------------
        // 6.3 EOFException: 意外到达文件或流末尾时抛出
        //-------------------------------------------------------------------------
        /*
         * 【示例代码】
         * try (DataInputStream dis = new DataInputStream(new FileInputStream("data.bin"))) {
         *     while (true) {
         *         int value = dis.readInt();  // 如果没有更多数据，抛出EOFException
         *     }
         * } catch (IOException e) {
         *     e.printStackTrace();
         * }
         *
         * 【原因分析】
         * 在读取二进制数据时，尝试读取超出流末尾的数据。
         *
         * 【避免方法】
         * - 在读取前检查可用数据量
         * - 使用带有返回值的方法（如read()返回-1表示EOF）而不是抛出异常的方法
         * - 在读取前写入数据长度信息
         * - 使用try-catch块捕获可能的EOFException
         */
    }
}
