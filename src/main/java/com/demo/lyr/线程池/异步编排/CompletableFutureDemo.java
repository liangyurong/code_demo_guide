package com.demo.lyr.线程池.异步编排;

import java.util.concurrent.*;

public class CompletableFutureDemo {

    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws Exception {
        method6();
    }

    /**
     * 方法完成后的感知，只能感知，无法修改返回的数据
     * @throws Exception
     */
    public static void method1() throws Exception {
        System.out.println("main start ==============");
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            // 异步执行内容
            System.out.println("当前线程id：" + Thread.currentThread().getId());
            int i = 10/2;
            return i;
        }, executor).whenComplete((result,exception)->{
            // 如果出现异常，whenComplete可以得到异常信息，但是无法修改返回的数据，此时需要exceptionally进行处理
            System.out.println("执行结果：" + result);
            System.out.println("异常信息：" + exception);
        }).exceptionally(throwable -> {
            return 0;
        });
        Integer result = future.get();
        System.out.println("最终结果：" + result);
        System.out.println("main end ==============");
    }

    /**
     * 方法执行完成后的处理
     * @throws Exception
     */
    public static void method2() throws Exception {
        System.out.println("main start ==============");
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            // 异步执行内容
            System.out.println("当前线程id：" + Thread.currentThread().getId());
            int i = 10/2;
            return i;
        }, executor).handle((result,exception)->{
            // handle可以接收上一步处理结果；有返回值
            // 有结果
            if(result != null){
                return result*2;
            }
            // 没有异常
            if(exception != null){
                return 0;
            }
            return 0;
        });
        Integer result = future.get();
        System.out.println("执行结果：" + result);
        System.out.println("main end ==============");

    }

    /**
     * 线程串行化（ABC都是异步任务，A执行完才能执行B，B执行完才能执行C）
     * @throws Exception
     */
    public static void method3() throws Exception {
        System.out.println("main start ==============");

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            // 异步执行内容
            System.out.println("运行任务A");
            return 20/2;
        }, executor).thenApplyAsync((result) -> {
            // thenAcceptAsync可以接收上一步处理结果；无返回值
            // thenApplyAsync 可以接收上一步处理结果；有返回值
            System.out.println("A运行结果：" + result);
            System.out.println("运行任务B");
            return 20/result;
        }, executor).thenApplyAsync((result) -> {
            System.out.println("B运行结果：" + result);
            System.out.println("运行任务C");
            return 2/result;
        }, executor);

        System.out.println("最终结果："+future.get());
        System.out.println("main end ==============");
    }

    /**
     * 合并任务结果：AB都要完成，才能执行C
     * @throws Exception
     */
    public static void method4() throws Exception {
        System.out.println("main start ==============");

        // 任务A
        CompletableFuture<Integer> futureA = CompletableFuture.supplyAsync(() -> {
            // 异步执行内容
            System.out.println("运行任务A");
            return 20/2;
        }, executor);

        // 任务B
        CompletableFuture<Integer> futureB = CompletableFuture.supplyAsync(() -> {
            // 异步执行内容
            System.out.println("运行任务B");
            return 10/2;
        }, executor);

        // thenAcceptBothAsync : 可以接收上一步的结果；无返回值
        // thenCombineAsync    : 可以接收上一步的结果；有返回值
        // 等同：futureA.thenCombineAsync(futureB, Integer::sum,executor);
        futureA.thenCombineAsync(futureB,(f1,f2)->{
            return f1 + f2;
        },executor);

        System.out.println("main end ==============");
    }

    /**
     * 合并任务结果：AB只要有一个完成，就可以执行C
     * @throws Exception
     */
    public static void method5() throws Exception {
        System.out.println("main start ==============");

        // 任务A
        CompletableFuture<Integer> futureA = CompletableFuture.supplyAsync(() -> {
            // 异步执行内容
            System.out.println("运行任务A");
            return 20/2;
        }, executor);

        // 任务B
        CompletableFuture<Integer> futureB = CompletableFuture.supplyAsync(() -> {
            // 异步执行内容
            System.out.println("运行任务B");
            // 休眠，才能看到acceptEitherAsync的效果
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10/2;
        }, executor);

        // 任务C
        // acceptEitherAsync  : 可以接收上一步的结果；无返回值
        // applyToEitherAsync : 可以接收上一步的结果；有返回值
        CompletableFuture<Integer> futureC = futureA.applyToEitherAsync(futureB, (result) -> {
            return result;
        }, executor);

        System.out.println("任务C结果：" + futureC.get());
        System.out.println("main end ==============");
    }

    /**
     * 多任务组合
     * @throws Exception
     */
    public static void method6() throws Exception {
        System.out.println("main start ==============");

        // 任务A
        CompletableFuture<String> futureImg = CompletableFuture.supplyAsync(() -> {
            // 异步执行内容
            System.out.println("HHH.jpg");
            return "HHH.jpg";
        }, executor);

        // 任务B
        CompletableFuture<String> futureInfo = CompletableFuture.supplyAsync(() -> {
            // 异步执行内容
            System.out.println("华为手机");
            return "华为手机";
        }, executor);

        // 任务C
        CompletableFuture<String> futurePrice = CompletableFuture.supplyAsync(() -> {
            // 异步执行内容
            try {
                Thread.sleep(3000);
                System.out.println("价格...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "256元";
        }, executor);

        // allOf : 需要所有执行完成
        // anyOf : 只需要一个执行完成即可
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futureImg, futureInfo, futurePrice);
        allOf.get(); // 必须所有任务都执行完成，才会执行下一步代码

        System.out.println("main end ==============");
    }

}
