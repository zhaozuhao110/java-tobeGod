package god.github.haoer.godbase.exception;

/**
 * Java 7引入的try-with-resources语法示例类
 * <p>
 * try-with-resources语法通过实现AutoCloseable接口，自动管理资源的关闭操作。
 * 在try代码块结束后，系统会自动调用资源的close方法，无需显式编写关闭逻辑。
 * </p>
 * <p>
 * 该类展示了try-with-resources语句的各种使用场景，包括：
 * - 基本的资源管理
 * - 多资源管理
 * - 自定义AutoCloseable资源
 * - 异常抑制机制
 * </p>
 *
 * @author zhaozuhao
 * @date 2025/3/12 10:21
 */
public class TryWithResourcesExample {

   public static void main(String[] args) {
      basicTryWithResources();
      multipleResourcesExample();
      customAutoCloseableExample();
      suppressedExceptionExample();
   }

   /**
    * 演示基本的try-with-resources用法
    * 使用BufferedReader读取文件内容，自动关闭资源
    */
   private static void basicTryWithResources() {
      System.out.println("===== 基本 try-with-resources 示例 =====");
      try (java.io.BufferedReader reader = new java.io.BufferedReader(
              new java.io.FileReader("data.txt"))) {
         String line = reader.readLine();
         System.out.println("读取数据: " + (line != null ? line : "<空文件>"));
      } catch (java.io.IOException e) {
         System.err.println("文件读取错误: " + e.getMessage());
      }
   }

   /**
    * 演示多个资源的管理
    * 使用缓冲流复制文件，提高IO效率
    */
   private static void multipleResourcesExample() {
      System.out.println("===== 多个资源管理示例 =====");
      try (
              java.io.BufferedInputStream bis = new java.io.BufferedInputStream(
                      new java.io.FileInputStream("input.txt"));
              java.io.BufferedOutputStream bos = new java.io.BufferedOutputStream(
                      new java.io.FileOutputStream("output.txt"))
      ) {
         byte[] buffer = new byte[8192];
         int bytesRead;
         while ((bytesRead = bis.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
         }
         bos.flush();
         System.out.println("文件复制完成");
      } catch (java.io.IOException e) {
         System.err.println("文件复制错误: " + e.getMessage());
      }
   }

   /**
    * 演示自定义AutoCloseable资源的使用
    * 展示如何创建和使用自定义的可自动关闭资源
    */
   private static void customAutoCloseableExample() {
      System.out.println("===== 自定义 AutoCloseable 资源示例 =====");
      try (MyResource resource = new MyResource()) {
         resource.doSomething();
      } catch (Exception e) {
         System.err.println("资源使用错误: " + e.getMessage());
      }
   }

   /**
    * 演示异常抑制机制
    * 展示当主异常和关闭异常同时发生时的处理方式
    */
   private static void suppressedExceptionExample() {
      System.out.println("===== SuppressedException 处理示例 =====");
      try (MyResourceWithException resource = new MyResourceWithException()) {
         resource.doSomething();
      } catch (Exception e) {
         System.err.println("主异常：" + e.getMessage());
         Throwable[] suppressedExceptions = e.getSuppressed();
         if (suppressedExceptions.length > 0) {
            System.err.println("被抑制的异常：");
            for (Throwable suppressed : suppressedExceptions) {
               System.err.println("  - " + suppressed.getMessage());
            }
         }
      }
   }
}

/**
 * 自定义的AutoCloseable资源示例
 * 展示如何实现一个基本的可自动关闭的资源
 */
class MyResource implements AutoCloseable {
   private boolean isClosed = false;

   @Override
   public void close() throws Exception {
      if (!isClosed) {
         System.out.println("MyResource 被关闭");
         isClosed = true;
      }
   }

   public void doSomething() throws Exception {
      if (isClosed) {
         throw new IllegalStateException("资源已关闭");
      }
      System.out.println("执行任务...");
   }
}

/**
 * 用于演示异常抑制机制的资源类
 * 在操作和关闭时都会抛出异常
 */
class MyResourceWithException implements AutoCloseable {
   private boolean isClosed = false;

   @Override
   public void close() throws Exception {
      if (!isClosed) {
         isClosed = true;
         throw new Exception("关闭资源时发生异常");
      }
   }

   public void doSomething() throws Exception {
      if (isClosed) {
         throw new IllegalStateException("资源已关闭");
      }
      throw new Exception("资源操作时发生异常");
   }
}
