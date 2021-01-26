package com.example.demo.executer;

import java.util.concurrent.*;

/**
 * @version V1.0
 * @Package com.example.demo.executer
 * @auther ZouJiaLiang
 * @data 2021/1/26 上午8:56
 */
public class Test {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long l = System.currentTimeMillis();
        asy();
//      syn();
        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);

    }

    /**
     * 同步（调用get方法是阻塞的）
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void syn() throws InterruptedException, ExecutionException {
        ThreadPoolExecutor test = CommonExecutor.buildThreadFirstExecutor("test");
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 5; i++) {
            int seqNo = i;
            Future<String> submit = test.submit(() -> {
                TimeUnit.SECONDS.sleep(3);
                return "syn======HelloWorld-" + seqNo + "-" + Thread.currentThread().getName();
            });
            System.out.println(submit.get());
        }
    }

    /**
     * 异步（用一个阻塞队列去优化）
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void asy() throws InterruptedException, ExecutionException {
        ThreadPoolExecutor test = CommonExecutor.buildThreadFirstExecutor("test");
        CompletionService threadPoolService = new ExecutorCompletionService(test);
        for (int i = 0; i < 5; i++) {
            int seqNo = i;
            threadPoolService.submit((Callable<String>) () -> {
                TimeUnit.SECONDS.sleep(3);
                return "HelloWorld-" + seqNo + "-" + Thread.currentThread().getName();
            });
        }
        for (int j = 0; j < 5; j++) {
            System.out.println(threadPoolService.poll(5,TimeUnit.SECONDS).get());
        }
    }
}
