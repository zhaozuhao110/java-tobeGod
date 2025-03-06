package god.github.haoer.godbase;

import lombok.Data;

/**
 * @author zhaozuhao
 * @title: Animal
 * @projectName java-tobeGod
 * @description: TODO
 * @date 2025/3/609:38
 */
@Data
public class Animal {

    private String name;

    private String eat;

    public  String getName(){
        return "动物";
    }

    public  String eatSomething(){
        return "吃东西";
    }
}
