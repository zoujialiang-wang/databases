package com.example.demo.service;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther zoujialiang
 * @date 2020/11/12 10:04
 */
public class ThreadTest implements Callable {
    @Override
    public Object call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        return 2;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("");


        ExecutorService executorService = Executors.newCachedThreadPool();
        FutureTask<Integer> futureTask = new FutureTask<>(new ThreadTest());
        executorService.submit(futureTask);
        System.out.println(futureTask.get());
    }
}

