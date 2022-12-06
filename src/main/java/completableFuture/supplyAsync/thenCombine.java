package completableFuture.supplyAsync;

import completableFuture.util.SmallTool;

import java.util.concurrent.CompletableFuture;

/*
 * @author wcy
 * @date 2022-12-06 23:07
 * @description
 * 前面的例子是餐厅已经煮好了饭。
 * 假设小白点餐后，厨师开始炒菜，服务员煮饭。期间小白打王者。
 * 等厨师和服务员都做好后，小白开始吃饭
 *
 */
public class thenCombine {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread("小白点餐了");

        /*
          thenCompose
         */
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("厨师开始炒菜");
            SmallTool.sleepMillis(200);
            return "厨师上菜--炒鸡蛋";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("服务员煮饭");
            SmallTool.sleepMillis(100);
            return "米饭";
        }), (dish, race) -> {
            SmallTool.printTimeAndThread("服务员打饭");
            SmallTool.sleepMillis(100);
            return String.format("%s, %s好了", dish, race);
        });

        // thenCombine 两个线程做同事做事
        // 然后合并结果

        SmallTool.printTimeAndThread("小白在打王者");
        // join()等待任务执行结束，返回任务结果 和Future的get(）类似
        SmallTool.printTimeAndThread(String.format("%s, 小白开吃", cf1.join()));
    }
}
