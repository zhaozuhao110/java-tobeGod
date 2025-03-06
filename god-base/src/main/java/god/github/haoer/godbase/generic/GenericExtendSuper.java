package god.github.haoer.godbase.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型中extends和super通配符的用法示例
 * <p>
 * 1. <? extends T>：上界通配符，表示参数类型是T或T的子类，主要用于从泛型读取数据（生产者）
 * 2. <? super T>：下界通配符，表示参数类型是T或T的父类，主要用于向泛型写入数据（消费者）
 * 3. PECS原则：Producer Extends, Consumer Super
 *   - 如果需要从集合中获取数据（生产者），使用extends
 *   - 如果需要向集合中写入数据（消费者），使用super
 *
 * @author zhaoz
 * @version 1.0
 * @since 2025-03-06
 */
public class GenericExtendSuper {

    public static void main(String[] args) {
        // 创建各种动物列表
        List<Animal> animals = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();
        List<Dog> dogs = new ArrayList<>();
        
        // 添加一些动物
        animals.add(new Animal());
        cats.add(new Cat());
        dogs.add(new Dog());
        
        // 演示extends通配符的使用
        System.out.println("===== extends通配符示例 =====");
        // 可以传入Animal及其任何子类的列表
        printAnimalNames(animals); // 可以传入Animal列表
        printAnimalNames(cats);    // 可以传入Cat列表
        printAnimalNames(dogs);    // 可以传入Dog列表
        
        // 演示super通配符的使用
        System.out.println("\n===== super通配符示例 =====");
        // 向列表中添加Cat对象
        addCat(animals);  // 可以向Animal列表添加Cat
        // addCat(cats);  // 可以向Cat列表添加Cat
        // addCat(dogs);  // 编译错误：不能向Dog列表添加Cat
        
        // 泛型方法示例
        System.out.println("\n===== 泛型方法示例 =====");
        Animal animal = new Animal();
        Cat cat = new Cat();
        Dog dog = new Dog();
        
        // 使用泛型方法
        printInfo(animal); // 使用Animal类型调用
        printInfo(cat);    // 使用Cat类型调用
        printInfo(dog);    // 使用Dog类型调用
        
        // 使用受限泛型方法
        List<Animal> animalList = createAnimalList(animal);
        List<Cat> catList = createAnimalList(cat);
        List<Dog> dogList = createAnimalList(dog);
        
        System.out.println("\n===== 复杂示例：结合extends和super =====");
        // 创建一个处理动物的工具类实例
        AnimalProcessor<Animal> processor = new AnimalProcessor<>();
        
        // 使用extends从列表中读取数据
        processor.processAnimals(animals);
        processor.processAnimals(cats);
        processor.processAnimals(dogs);
        
        // 使用super向列表中写入数据
        processor.addAnimal(animals, new Cat());
        // processor.addAnimal(cats, new Dog()); // 编译错误：不能向Cat列表添加Dog
    }
    
    /**
     * 使用上界通配符（extends）- 只能读取元素
     * 这里使用<? extends Animal>表示可以接受Animal或其任何子类的列表
     * 适用于：从列表中读取数据的场景（生产者模式）
     */
    private static void printAnimalNames(List<? extends Animal> animals) {
        for (Animal animal : animals) {
            System.out.println("动物名称: " + animal.getName());
            System.out.println("动物吃: " + animal.eatSomething());
        }
        
        // 编译错误：不能添加任何元素（即使是Animal类型）
        // animals.add(new Animal()); // 错误
        // animals.add(new Cat());    // 错误
        
        // 只能读取元素
        Animal animal = animals.get(0); // 正确，因为无论列表中是什么类型，都可以赋值给Animal
    }

    /**
     * 使用下界通配符（super）- 适用于“写入” Cat 或其子类
     * 这里使用 <? super Cat> 表示这个列表的类型可以是 Cat 或其父类（如 List<Cat>、List<Animal>、List<Object>）
     * 但是，不能往列表里添加 Cat 的父类（如 Animal），只能添加 Cat 或其子类
     * 适用于：向列表中写入 Cat 或其子类的场景（消费者模式）
     */
    private static void addCat(List<? super Cat> list) {
        // 可以添加Cat或Cat的子类
        list.add(new Cat()); // 正确
        
        // 编译错误：不能添加Cat的父类
        // list.add(new Animal()); // 错误
        
        // 编译错误：不能添加Cat的兄弟类
        // list.add(new Dog()); // 错误
        
        // 读取元素时，只能以Object类型读取
        Object obj = list.get(0); // 正确，但类型信息丢失
        // Cat cat = list.get(0);  // 错误，不能保证列表中的元素是Cat类型
    }
    
    /**
     * 泛型方法示例
     * 可以接受任何类型的参数
     */
    private static <T> void printInfo(T obj) {
        System.out.println("对象类型: " + obj.getClass().getSimpleName());
    }
    
    /**
     * 受限泛型方法示例
     * 只接受Animal或其子类作为参数，并返回相应类型的列表
     */
    private static <T extends Animal> List<T> createAnimalList(T animal) {
        List<T> list = new ArrayList<>();
        list.add(animal); // 可以添加T类型的元素
        System.out.println("创建了一个" + animal.getName() + "列表");
        return list;
    }
    
    /**
     * 泛型类示例：动物处理器
     * 演示在类中如何使用extends和super通配符
     */
    static class AnimalProcessor<T extends Animal> {
        
        /**
         * 使用extends通配符处理动物列表（读取操作）
         */
        public void processAnimals(List<? extends Animal> animals) {
            System.out.println("处理动物列表:");
            for (Animal animal : animals) {
                System.out.println(" - " + animal.getName() + ": " + animal.eatSomething());
            }
        }
        
        /**
         * 使用super通配符向动物列表添加元素（写入操作）
         */
        public <E extends Animal> void addAnimal(List<? super E> list, E animal) {
            list.add(animal);
            System.out.println("添加了一个" + animal.getName() + "到列表中");
        }
    }
}