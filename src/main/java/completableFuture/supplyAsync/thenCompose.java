package completableFuture.supplyAsync;

import completableFuture.util.SmallTool;

import java.util.concurrent.CompletableFuture;

/*
 * @author wcy
 * @date 2022-12-05 23:25
 * @description
 * 厨师只负责炒菜，将打饭的操作交给服务员
 */
public class thenCompose {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread("小白点餐了");

        /**
         * thenCompose 等待前一个线程的执行结果
         */
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("厨师开始炒菜");
            SmallTool.sleepMillis(200);
            return "厨师上菜--炒鸡蛋";
        }).thenCompose(dish -> CompletableFuture.supplyAsync(()->{
            SmallTool.printTimeAndThread("服务员打饭");
            SmallTool.sleepMillis(100);
             return dish + " + 米饭";
        }));

        SmallTool.printTimeAndThread("小白在打王者");
        // join()等待任务执行结束，返回任务结果 和Future的get(）类似
        SmallTool.printTimeAndThread(String.format("%s, 小白开吃", cf1.join()));
    }
}
