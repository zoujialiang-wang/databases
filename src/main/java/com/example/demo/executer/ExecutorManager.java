package com.example.demo.executer;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Zoujialiang
 * @date 2020/10/19 10:58
 */
@Component
public class ExecutorManager {
    private static final ConcurrentHashMap<String, ThreadPoolExecutor> EXECUTORS = new ConcurrentHashMap<>(8);

    /**
     * 向管理器注册线程池
     * @param threadPoolName
     * @param executor
     */
    public static void registerThreadPoolExecutor(String threadPoolName, ThreadPoolExecutor executor) {
        EXECUTORS.put(threadPoolName,executor);
    }

    /**
     * 根据名称获取线程池
     * @param threadPoolName
     * @return
     */
    public static ThreadPoolExecutor getThreadPoolExecutor(String threadPoolName) {
        return EXECUTORS.get(threadPoolName);
    }

    /**
     * 获取所有已注册的线程池
     * @return
     */
    public static Map<String, ThreadPoolExecutor> getAllThreadPoolExecutor() {
        return ImmutableMap.copyOf(EXECUTORS);
    }

    /**
     * 根据名称移除已注册的线程池
     * @param threadPoolName
     */
    public static void removeThreadPoolExecutor(String threadPoolName) {
        EXECUTORS.remove(threadPoolName);
    }
}
