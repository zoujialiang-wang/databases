package com.example.demo.concurrent;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @version V1.0
 * @Package com.example.demo.concurrent
 * @auther ZouJiaLiang
 * @data 2021/1/12 上午9:38
 */
public class test {
    //帮助方法，用来获得一个指定元素数量模拟数据的\
    private static ConcurrentHashMap getData(int count) {
        return LongStream.rangeClosed(1, count)
                .boxed()
                .collect(Collectors.toConcurrentMap(i -> UUID.randomUUID().toString(), Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
    }

    public static void main(String[] args) {
        ConcurrentHashMap data = getData(10);
        System.out.println(data);
    }
}
