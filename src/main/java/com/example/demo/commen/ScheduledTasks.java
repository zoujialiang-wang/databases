package com.example.demo.commen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * @Author zou18846936743
 * @Date 2020/12/9 下午6:48
 * @Version 1.0
 */
@Component
public class ScheduledTasks {

    public static ConcurrentHashMap<String, ScheduledFuture> map = new ConcurrentHashMap(16);

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    /**
     * 判断此任务是否存在
     *
     * @param name
     * @return
     */
    public Boolean isExit(String name) {
        return map.containsKey(name);
    }

    /**
     * 停止任务
     *
     * @param name
     */
    public void stop(String name) {
        ScheduledFuture scheduledFuture = map.get(name);
        if (isExit(name)) {
            scheduledFuture.cancel(true);
        }
    }

    /**
     * @param runnable  执行的任务
     * @param startTime 执行任务的时间
     * @param name      调度的名字
     */
    public void start(Runnable runnable, Date startTime, String name) {
        ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(runnable, startTime);
        map.put(name, schedule);
    }

    /**
     * @param runnable  执行的任务
     * @param startTime 执行任务的时间
     * @param name      调度的名字
     * @param period    延时多长时间执行
     */
    public void start(Runnable runnable, Date startTime, String name, Long period) {
        ScheduledFuture<?> schedule = threadPoolTaskScheduler.scheduleAtFixedRate(runnable, startTime, period);
        map.put(name, schedule);
    }

    /**
     * @param runnable 执行的任务
     * @param trigger  条件表达式
     * @param name     调度的名字
     */
    public void start(Runnable runnable, Trigger trigger, String name) {
        ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(runnable, trigger);
        map.put(name, schedule);
    }

    @Bean
    private ThreadPoolTaskScheduler getThreadPoolTaskScheduler() {
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.setPoolSize(20);
        executor.setThreadNamePrefix("taskExecutor-");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }

}