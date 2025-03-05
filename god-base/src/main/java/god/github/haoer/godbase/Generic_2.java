package god.github.haoer.godbase;

/**
 * @author zhaozuhao
 * @title: Generic_2
 * @projectName java-tobeGod
 * @description: 泛型中的 K T V E ? Object等分别代码什么含义
 * @date 2025/3/521:45
 */
public class Generic_2<T> {

    /**
     * 在 Java 泛型中，K、T、V、E、S、U 等只是 类型参数的名称，它们的具体含义并没有严格限制，可以相互替换，但在实际编码中，遵循 通用命名规范 可以提高代码的可读性和可维护性
     * 常见的泛型命名规范
     * 虽然 Java 泛型的类型参数可以是任何单个字母或自定义名称，但通常按照以下惯例命名：
     * •	T（Type）：表示任意类型，常用于通用类型（如 List<T>）。
     * •	K（Key）：键，通常用于 键值映射（如 Map<K, V>）。
     * •	V（Value）：值，通常用于 键值映射（如 Map<K, V>）。
     * •	E（Element）：元素，常用于 集合类型（如 List<E>）。
     * •	S, U, V：一般用于 额外的泛型类型，表示 Second、Third 类型参数，例如：
     * •	R（Return）：返回值类型，通常用于 函数式接口（如 Function<T, R>）。
     */
    class Pair<T, U> {
    }

    /**
     * 可以相互替换吗？
     * <p>
     * 是的，这些字母只是约定俗成的命名方式，在语法上可以相互替换，但这样做会降低代码的可读性。例如：
     */
    class MyMap<K, V> {
        private K key;
        private V value;
    }

    /**
     * 从语法角度看完全没问题，但如果 T1, T2 取代 K, V，就不够直观，尤其是当其他开发者阅读代码时，可能会有困惑。
     *
     * @param <T1>
     * @param <T2>
     */
    class MyMap2<T1, T2> {
        private T1 key;
        private T2 value;
    }


}
