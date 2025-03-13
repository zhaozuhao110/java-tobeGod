package god.github.haoer.godbase.string;

/**
 * 字符串常量池与intern方法示例
 * 该类展示了字符串常量池的工作机制以及intern方法的使用
 * 
 * @author zhaozuhao
 * @date 2025/3/13
 */
public class InternStudy {

    public static void main(String[] args) {
        // 示例1：字符串常量池中的"a"
        String s1 = new String("a");
        s1.intern();
        String s2 = "a";
        System.out.println(s1 == s2); // false，s1在堆中，s2在常量池中

        // 示例2：字符串拼接与intern
        String s3 = new String("a") + new String("a");
        s3.intern();
        String s4 = "aa";
        System.out.println(s3 == s4); // true，s3的引用被存入常量池

        // 解释：
        // s1 != s2 的原因：
        // - "a" 在类加载时已经进入字符串常量池。
        // - new String("a") 创建了一个新的字符串对象 s1 在堆中。
        // - s1.intern() 调用后，s1 的引用没有改变，因为常量池中已经存在 "a"。
        // - s2 直接引用常量池中的 "a"，因此 s1 和 s2 指向不同的内存地址。

        // s3 == s4 的原因：
        // - new String("a") + new String("a") 创建了一个新的字符串对象 "aa" 在堆中。
        // - s3.intern() 将 "aa" 的引用存入字符串常量池，因为常量池中没有 "aa"。
        // - s4 直接引用常量池中的 "aa"，而此时常量池中的 "aa" 是 s3 的引用。
        // - 因此，s3 和 s4 指向相同的内存地址。
    }
}
