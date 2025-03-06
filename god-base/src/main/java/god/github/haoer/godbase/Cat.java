package god.github.haoer.godbase;

/**
 * @author zhaozuhao
 * @title: Cat
 * @projectName java-tobeGod
 * @description: TODO
 * @date 2025/3/609:39
 */
public class Cat extends Animal {


    @Override
    public String getName() {
        return "猫";
    }

    @Override
    public String eatSomething() {
        return "吃鱼";
    }
}
