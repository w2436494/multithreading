---
typora-root-url: src\main\resources\staic\images
---

# multithreading 笔记

## 1、CompletableFuture入门

CompletableFuture在Java8中提出，Java9中增强

![image-20221205225907610](/img.png)



thenCombine需要两个入参，第二个参数BiFunction是将两个值转换为一个值

```
supplyAsync 开始异步任务
thenCompose 连接两个异步任务
thenCombine 合并两个异步任务，结果由合并函数BiFunction返回
```

![](/summary.png)

![](/summary0.png)