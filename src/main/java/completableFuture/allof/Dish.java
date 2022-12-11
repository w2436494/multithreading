package completableFuture.allof;

import completableFuture.util.SmallTool;

/*
 * @author wcy
 * @date 2022-12-11 22:25
 * @description
 */
public class Dish {

    String dishName;
    int time = 1000;

    public Dish(String dishName){
        this.dishName = dishName;
    }

    public void make(){
        SmallTool.sleepMillis(time);
        SmallTool.printTimeAndThread(this.dishName + "制作完毕！！！");
    }
}
