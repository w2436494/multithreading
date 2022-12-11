package completableFuture.allof;

import completableFuture.util.SmallTool;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * @author wcy
 * @date 2022-12-11 22:21
 * @description
 * 点n盘菜，让多个厨师去做
 *
 * 将线程数设置为一个合适的数值可适当提高程序的性能
 * java.util.concurrent.ForkJoinPool.common.parallelism
 */
public class AllOfDemo {

    public static void main(String[] args) {

        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "12");
        SmallTool.printTimeAndThread("小白和小伙伴们进餐厅点菜");
        long startTime = System.currentTimeMillis();

        /*
        List<Dish> dishs = new ArrayList<>();

        // 点菜
        for (int i = 1; i <= 10; i++) {
            dishs.add(new Dish("菜" + i));
        }
         */

        // 做菜
//        dishs.forEach((dish -> {
//            CompletableFuture.runAsync(() ->
//                    dish.make()
//            ).join(); // 此处相当于把异步操作做成了同步
//        }));

        /*
        // 修改1
        ArrayList<CompletableFuture> cfList = new ArrayList<>();

        dishs.forEach((dish -> {
            CompletableFuture cf = CompletableFuture.runAsync(() -> dish.make());
            cfList.add(cf);
        }));
        // allof方法等待所以线程执行完毕
        CompletableFuture.allOf(cfList.toArray(new CompletableFuture[cfList.size()])).join();
         */

        // lamda优化 streamapi优化
        // 优雅的代码
        CompletableFuture[] dishes = IntStream.rangeClosed(1, 20).mapToObj(i -> new Dish("菜" + i))
                .map(dish -> CompletableFuture.runAsync(dish::make))
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(dishes).join();

        SmallTool.printTimeAndThread("菜做好了，耗时:"
                + (System.currentTimeMillis() - startTime));
        // 第一次结果:菜做好了，耗时:10215
        // 第二次结果:耗时:4150(性能提升1倍)
        // 第三次执行结果:菜做好了，耗时:4334
    }
}
