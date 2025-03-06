package god.github.haoer.godbase.spi;

/**
 * @author zhaozuhao
 * @title: MyServiceImplA
 * @projectName java-tobeGod
 * @description: TODO
 * @date 2025/3/619:41
 */
public class MyServiceImplB implements MyService {

    @Override
    public void execute() {
        System.out.println("我是 service B");
    }
}
