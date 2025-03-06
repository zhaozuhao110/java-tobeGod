package god.github.haoer.godbase.generic;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Java泛型通配符使用示例
 * 展示了泛型中常用的类型参数命名规范和实际应用场景
 *
 * @author zhaozuhao
 * @date 2025/3/5
 */
public class GenericWildCard<T> {

    /**
     * 在Java泛型中，常用的类型参数命名规范：
     * T（Type）：表示任意类型，用于通用场景
     * K（Key）：表示键的类型
     * V（Value）：表示值的类型
     * E（Element）：表示元素类型，主要用于集合
     * R（Result）：表示返回值类型
     * S、U、V：表示多个类型参数时的附加类型
     */

    /**
     * 基础泛型类示例：键值对
     * 展示了如何使用K和V两个类型参数创建通用的键值对数据结构
     *
     * @param <K> 键的类型参数
     * @param <V> 值的类型参数
     */
    @Getter
    public static class Pair<K, V> {
        /**
         *  获取键对象
         */
        private final K key;
        /**
         *  获取值对象
         */
        private final V value;

        /**
         * 创建一个新的键值对
         *
         * @param key   键对象，类型为K
         * @param value 值对象，类型为V
         */
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Pair{" + "key=" + key + ", value=" + value + "}";
        }
    }

    /**
     * 集合类型示例：使用E表示元素类型的简单列表实现
     * 展示了如何使用泛型创建类型安全的集合类
     *
     * @param <E> 列表中元素的类型参数
     */
    public static class SimpleList<E> {
        private final List<E> elements = new ArrayList<>();

        /**
         * 向列表中添加元素
         *
         * @param element 要添加的元素，类型为E
         */
        public void add(E element) {
            elements.add(element);
        }

        /**
         * 获取指定索引位置的元素
         *
         * @param index 要获取元素的索引位置
         * @return 返回指定位置的元素，类型为E
         * @throws IndexOutOfBoundsException 如果索引越界
         */
        public E get(int index) {
            return elements.get(index);
        }
    }

    /**
     * 函数式接口示例：使用T和R表示输入和返回类型的转换器
     * 展示了如何使用泛型和函数式接口创建通用的数据转换器
     *
     * @param <T> 输入数据的类型参数
     * @param <R> 输出数据的类型参数
     */
    public static class Transformer<T, R> {
        private final Function<T, R> function;

        /**
         * 创建一个新的转换器
         *
         * @param function 用于转换数据的函数对象，接收T类型参数，返回R类型结果
         */
        public Transformer(Function<T, R> function) {
            this.function = function;
        }

        /**
         * 转换输入数据
         *
         * @param input 要转换的输入数据，类型为T
         * @return 返回转换后的数据，类型为R
         */
        public R transform(T input) {
            return function.apply(input);
        }
    }

    // 实际应用示例
    public static void main(String[] args) {
        System.out.println("===== 键值对示例 =====");
        Pair<String, Integer> pair = new Pair<>("年龄", 25);
        System.out.println(pair);

        System.out.println("\n===== 集合操作示例 =====");
        SimpleList<Animal> animalList = new SimpleList<>();
        animalList.add(new Animal());
        Animal animal = animalList.get(0);
        System.out.println("动物：" + animal.getName());

        System.out.println("\n===== 转换器示例 =====");
        Transformer<Animal, String> nameTransformer =
                new Transformer<>(Animal::getName);
        String animalName = nameTransformer.transform(new Animal());
        System.out.println("转换后的名称：" + animalName);

        System.out.println("\n===== 实际应用：动物信息管理 =====");
        // 使用Map存储动物信息
        Map<String, Animal> animalMap = new HashMap<>();
        Animal cat = new Animal();
        animalMap.put(cat.getName(), cat);

        // 使用List存储动物列表
        List<Animal> animals = new ArrayList<>();
        animals.add(cat);

        // 使用函数式接口处理动物信息
        Transformer<Animal, String> eatTransformer =
                new Transformer<>(Animal::eatSomething);
        System.out.println("动物进食：" +
                eatTransformer.transform(animals.get(0)));
    }
}
