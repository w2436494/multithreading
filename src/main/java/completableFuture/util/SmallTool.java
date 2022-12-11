package completableFuture.util;

import java.util.StringJoiner;

/*
 * @author wcy
 * @date 2022-12-05 22:34
 * @description
 */
public class SmallTool {
    public static void printTimeAndThread(String message) {
        String mess = new StringJoiner("\t")
                .add(String.valueOf(System.currentTimeMillis()))
                .add(String.valueOf(Thread.currentThread().getName()))
                .add(String.valueOf(Thread.currentThread().getId()))
                .add(message).toString();
        System.out.println(mess);
    }

    public static void sleepMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
