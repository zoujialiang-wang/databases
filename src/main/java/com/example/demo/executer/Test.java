package com.example.demo.executer;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @version V1.0
 * @Package com.example.demo.executer
 * @auther ZouJiaLiang
 * @data 2021/1/26 上午8:56
 */
public class Test {
    private int queueSize = 10;
    private int value;
    ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(queueSize);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        cyclicBarrierTest();
    }

    /**
     * 同步（调用get方法是阻塞的）
     *
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
     *
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
            System.out.println(threadPoolService.poll(5, TimeUnit.SECONDS).get());
        }
    }

    /**
     * 使用FutureTask处理带返回值的线程
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void futureTest() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor test = CommonExecutor.buildThreadFirstExecutor("futureTest");
        FutureTask futureTask = new FutureTask(() -> {
            long sum = 0;
            for (int i = 0; i < 10000; i++) {
                sum += i;
            }
            TimeUnit.SECONDS.sleep(2);
            return sum;
        });
        test.submit(futureTask);
    }

    /**
     * 使用阻塞队列实现
     */
    private void atomicTest() {
        ThreadPoolExecutor test = CommonExecutor.buildThreadFirstExecutor("atomicTest");
        test.execute(() -> {
            while (true) {
                try {
                    arrayBlockingQueue.take();
                    System.out.println("从队列取走一个元素，队列剩余" + arrayBlockingQueue.size() + "个元素");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        test.execute(() -> {
            while (true) {
                try {
                    arrayBlockingQueue.put(1);
                    System.out.println("向队列取中插入一个元素，队列剩余空间：" + (queueSize - arrayBlockingQueue.size()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 限制线程数量
     */
    private void semaphoreTest() {
        Semaphore semaphore = new Semaphore(3);
        ThreadPoolExecutor test = CommonExecutor.buildThreadFirstExecutor("semaphoreTest");
        for (int i = 0; i < 10; i++) {
            test.execute(() -> {
                try {
                    semaphore.acquire(); // 获取permit
                    System.out.println(String.format("当前线程是%d, 还剩%d个资源，还有%d个线程在等待",
                            value, semaphore.availablePermits(), semaphore.getQueueLength()));
                    // 睡眠随机时间，打乱释放顺序
                    Random random = new Random();
                    Thread.sleep(random.nextInt(1000));
                    System.out.println(String.format("线程%d释放了资源", value));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(); // 释放permit
                }
            });
        }
    }

    /**
     * 线程之间交换数据(只能是两个线程)
     */
    private static void exchangerTest() {
        ThreadPoolExecutor test = CommonExecutor.buildThreadFirstExecutor("exchangerTest");
        Exchanger<String> exchanger = new Exchanger();
        test.execute(() -> {
            try {
                System.out.println("这是线程A，得到了另一个线程的数据："
                        + exchanger.exchange("这是来自线程A的数据"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        test.execute(() -> {
            try {
                System.out.println("这是线程B，得到了另一个线程的数据："
                        + exchanger.exchange("这是来自线程B的数据"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * 等待其他线程完成时才能继续进行
     */
    private static void countDownLatchDemoTest() {
        ThreadPoolExecutor test = CommonExecutor.buildThreadFirstExecutor("countDownLatchDemoTest");
        CountDownLatch countDownLatch = new CountDownLatch(3);
        test.execute(() -> {
            try {
                System.out.println("等待数据加载...");
                System.out.println(String.format("还有%d个前置任务", countDownLatch.getCount()));
                countDownLatch.await();
                System.out.println("数据加载完成，正式开始游戏！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            test.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                    System.out.println("加载前置" + finalI);
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    /**
     * countDownLatch的加强版，可以重复设置
     */
    private static void cyclicBarrierTest() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println("本关卡所有前置任务完成，开始游戏...");
        });
        ThreadPoolExecutor test = CommonExecutor.buildThreadFirstExecutor("cyclicBarrierTest");
        for (int i = 0; i < 3; i++) {
            test.execute(() -> {
                for (int j = 1; j < 4; j++) {
                    try {
                        Random random = new Random();
                        Thread.sleep(random.nextInt(1000));
                        System.out.println(String.format("关卡%d的任务完成", j));
                        cyclicBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    cyclicBarrier.reset(); // 重置屏障
                }
            });
        }
    }
}
