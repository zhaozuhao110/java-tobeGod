package god.github.haoer.godbase.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型类示例
 * 演示了List泛型的四种常见用法：
 * 1. List（原始类型）：可以添加任意类型元素，但不安全
 * 2. List<Object>：可以添加任意类型元素，但不能接收List<String>等具体类型的参数
 * 3. List<?>：通配符类型，只能读取元素，不能添加元素（除了null）
 * 4. List<String>：具体类型，只能添加String类型元素
 * 泛型擦除就是将泛型 java 编译成普通 java代码
 *
 * @author zhaoz
 * @version 1.0
 * @since 2025-03-05
 */
public class Generic {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        test(list);  // 原始类型List可以接收任何类型的List参数
        // 编译报错：test2方法需要List<Object>类型的参数
        // 原因：虽然String是Object的子类，但List<String>不是List<Object>的子类型
        //test2(list);
        test3(list);  // List<?>可以接收任何类型的List参数
        test4(list);  // 参数类型完全匹配
    }

    /**
     * 测试方法，允许添加任意类型的元素
     * 注意：使用原始类型List是不安全的，应该避免使用
     */
    private static void test(List list) {
        list.add("没有问题");  // 可以添加String
        list.add(1234);      // 也可以添加Integer，但这可能导致运行时类型转换错误
    }

    /**
     * 测试方法，接受List<Object>类型的参数
     * 注意：不能传入List<String>等具体类型的List
     * 原因：Java泛型是不变的（invariant），List<String>不是List<Object>的子类型
     */
    private static void test2(List<Object> list) {
        // 方法体未实现
    }

    /**
     * 测试方法，使用通配符，不能添加元素，只能查询
     * List<?>是所有List<T>的超类，但只能读取元素，不能写入（除了null）
     */
    private static void test3(List<?> list) {
        // list.add("编译报错"); // 不能添加元素，因为不知道List中的具体类型
        list.get(0); // 编译正常，因为任何类型都可以赋值给Object
    }

    /**
     * 测试方法，接受List<String>类型的参数
     * 类型安全，编译器会确保只能添加String类型的元素
     */
    private static void test4(List<String> list) {
        list.add("编译正常");  // 只能添加String类型
    }
}
