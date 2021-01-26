package com.example.demo.executer;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Zoujialiang
 * @date 2020/10/19 10:17
 */
@Slf4j
public class CommonExecutor {
    //private static final ThreadPoolExecutor BASE_EXECUTOR;
    //
    //static {
    //    String executorName = "BaseExecutor";
    //    BASE_EXECUTOR = buildThreadFirstExecutor(executorName);
    //    ExecutorManager.registerThreadPoolExecutor(executorName, BASE_EXECUTOR);
    //}

    /**
     * 构建线程优先的线程池
     * 线程池默认是当核心线程数满了后，将任务添加到工作队列中，当工作队列满了之后，再创建线程直到达到最大线程数。
     * 线程优先的线程池，就是在核心线程满了之后，继续创建线程，直到达到最大线程数之后，再把任务添加到工作队列中。
     * 此方法默认设置核心线程数为 CPU 核数，最大线程数为 8倍 CPU 核数，空闲线程超过 5 分钟销毁，工作队列大小为 65536。
     *
     * @param poolName
     * @return
     */
    public static ThreadPoolExecutor buildThreadFirstExecutor(String poolName) {
        int coreSize = CommonExecutor.getCpuProcessors();
        int maxSize = coreSize * 8;
        return buildThreadFirstExecutor(coreSize, maxSize, 5, TimeUnit.MINUTES, 1 << 16, poolName);
    }

    /**
     * 返回cpu核心数  默认为8
     *
     * @return
     */
    private static int getCpuProcessors() {
        return Runtime.getRuntime() != null && Runtime.getRuntime().availableProcessors() > 0 ? Runtime.getRuntime().availableProcessors() : 8;
    }

    /**
     * @param corePoolSize    核心线程数
     * @param maximumPoolSize 最大线程数
     * @param keepAliveTime   空闲线程的空闲时间
     * @param unit            时间单位
     * @param workQueueSize   工作队列容量大小
     * @param poolName        线程池名称
     * @return
     */
    public static ThreadPoolExecutor buildThreadFirstExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, int workQueueSize, String poolName) {
        // 自定义队列，优先开启更多线程，而不是放入队列
        LinkedBlockingDeque<Runnable> queue = new LinkedBlockingDeque<Runnable>(workQueueSize) {
            @Override
            public boolean offer( Runnable runnable) {
                return false; // 造成队列已满的假象
            }
        };

        // 当线程达到 maximumPoolSize 时会触发拒绝策略，此时将任务 put 到队列中
        RejectedExecutionHandler rejectedExecutionHandler = (runnable, executor) -> {
            try {
                queue.put(runnable);
            } catch (InterruptedException e) {
                ////log.error("{} Queue offer interrupted", poolName, e);
            }
        };

        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, queue, new ThreadFactoryBuilder().setNameFormat(poolName).setUncaughtExceptionHandler((Thread thread, Throwable throwable) -> {
            //log.error("{} catching the uncaught exception, ThreadName: [{}]", poolName, thread.toString(), throwable);
        }).build(), rejectedExecutionHandler);
        //超时后核心线程会关闭
        executor.allowCoreThreadTimeOut(true);
        //开启自动监控线程的功能
        //CommonExecutor.displayThreadPoolStatus(executor, poolName);
        ExecutorManager.registerThreadPoolExecutor(poolName, executor);
        CommonExecutor.hookShutdownThreadPool(executor, poolName);
        return executor;
    }

    /**
     * 添加Hook在Jvm关闭时优雅的关闭线程池
     *
     * @param threadPool     线程池
     * @param threadPoolName 线程池名称
     */
    private static void hookShutdownThreadPool(ThreadPoolExecutor threadPool, String threadPoolName) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            //log.info("[>>ExecutorShutdown<<] Start to shutdown the thead pool: [{}]", threadPoolName);
            // 使新任务无法提交
            threadPool.shutdown();
            // 等待未完成任务结束
            try {
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    // 取消当前执行的任务
                    threadPool.shutdownNow();
                    //log.error("[>>ExecutorShutdown<<] Interrupt the worker, which may cause some task inconsistent. Please check the biz //logs.");
                }
            } catch (InterruptedException e) {
                // 重新取消当前线程进行中断
                threadPool.shutdownNow();
            }
        }));
    }

    /**
     * @param threadPool     线程池
     * @param threadPoolName 线程池名称
     */
    private static void displayThreadPoolStatus(ThreadPoolExecutor threadPool, String threadPoolName) {
        displayThreadPoolStatus(threadPool, threadPoolName, new Random().nextInt(30)+30, TimeUnit.SECONDS);
    }

    /**
     * @param threadPool     线程池
     * @param threadPoolName 线程池名称
     * @param period         周期
     * @param unit           时间单位
     */
    public static void displayThreadPoolStatus(ThreadPoolExecutor threadPool, String threadPoolName, long period, TimeUnit unit) {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            String payload = "[>>ExecutorStatus<<] ThreadPool Name: [{}], Pool Status: [shutdown={}, Terminated={}], Pool Thread Size: {}, Largest Pool Size: {}, Active Thread Count: {}, Task Count: {}, Tasks Completed: {}, Tasks in Queue: {}";
            Object[] params = new Object[]{threadPoolName, threadPool.isShutdown(), threadPool.isTerminated(), // 线程是否被终止
                    threadPool.getPoolSize(), // 线程池线程数量
                    threadPool.getLargestPoolSize(), // 线程最大达到的数量
                    threadPool.getActiveCount(), // 工作线程数
                    threadPool.getTaskCount(), // 总任务数
                    threadPool.getCompletedTaskCount(), // 已完成的任务数
                    threadPool.getQueue().size()};
            if (threadPool.getQueue().remainingCapacity() < 64) {
                //log.warn(payload, params);
            } else {
                //log.info(payload, params);
            }
        }, 0, period, unit);
    }


}


