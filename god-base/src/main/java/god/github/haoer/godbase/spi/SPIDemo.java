package god.github.haoer.godbase.spi;

import java.util.ServiceLoader;

/**
 * SPI (Service Provider Interface) 机制示例
 *
 * <p>SPI 是Java提供的一种服务发现机制，它通过在classpath下的META-INF/services目录中配置接口的实现类名称，
 * 实现在运行时动态加载和切换接口的实现类。
 *
 * <p>工作原理：
 * 1. 定义服务接口
 * 2. 提供接口实现类
 * 3. 在META-INF/services目录下创建以接口全限定名命名的文件
 * 4. 文件内容为实现类的全限定名（每行一个）
 * 5. 使用ServiceLoader加载实现类
 *
 * <p>许多 Java 框架都基于 SPI 进行组件扩展，例如：
 * 1.JDBC：数据库驱动发现（java.sql.Driver）
 * 2.Dubbo：服务治理框架的插件机制（com.alibaba.dubbo.common.extension.ExtensionLoader）
 * 3.SLF4J：日志门面框架（绑定不同的日志实现）
 *
 * <p>优点：
 * ✅ 解耦：可以动态切换实现，降低模块间的依赖。
 * ✅ 扩展性：新增实现类无需修改原有代码，只需增加 META-INF/services 配置。
 * ✅ 适用于框架级别开发，如 JDBC、Dubbo、SLF4J、Spring 等。
 *
 * <p>SPI 可能存在的问题：
 * 🚨 无法按优先级加载（默认是 按照 ServiceLoader 加载顺序执行）。
 * 🚨 存在性能开销（每次都会遍历 META-INF/services 目录下的文件，影响启动速度）。
 * 🚨 无法传递参数（SPI 只支持无参构造方法，无法动态传参）。
 *
 * <p>本示例展示了如何：
 * 1. 定义服务接口 {@link MyService}
 * 2. 提供两个实现 {@link MyServiceImplA} 和 {@link MyServiceImplB}
 * 3. 配置META-INF/services/god.github.haoer.godbase.spi.MyService
 * 4. 使用ServiceLoader加载并遍历所有实现
 *
 * @author zhaozuhao
 * @date 2025/3/619:45
 */
public class SPIDemo {

    public static void main(String[] args) {
        ServiceLoader<MyService> spiDemos = ServiceLoader.load(MyService.class);
        for (MyService spiDemo : spiDemos) {
            spiDemo.execute();
        }
    }
}
