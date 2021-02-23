package com.example.demo.lambda;

import java.awt.geom.Point2D;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * @version V1.0
 * @Package com.example.demo.lambda
 * @auther ZouJiaLiang
 * @data 2021/1/8 下午7:09
 */
public class Test1 {

    private Map<Long, Object> cache = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Optional<String> reduce = stream.reduce((s1, s2) -> s1.length() > s2.length() ? s1 : s2);
        System.out.println(reduce.get());
    }

    private static void aaaaaaa() {
        //Supplier是提供一个数据的接口。这里我们实现获取一个随机数
        Supplier<Integer> stringSupplier = () -> ThreadLocalRandom.current().nextInt();
        System.out.println(stringSupplier.get());
        //Predicate接口是输入一个参数，返回布尔值,我们通过and方法组合两个Predicate条件，判断是否值大于0并且是偶数
        Predicate<Integer> integerPredicate = i -> i < 0;
        System.out.println(integerPredicate.and((a) -> a > 4).test(5));
        //Consumer接口是消费一个数据。我们通过andThen方法组合调用两个Consumer，输出两行abcdefg
        Consumer<String> consumer = System.out::println;
        consumer.andThen(consumer).accept("abcdefg");
        //Function接口是输入一个数据，计算后输出一个数据。我们先把字符串转换为大写，然后通过andThen组合另一个Function实现字符串拼接F
        Function<String, String> upperCase = String::toUpperCase;
        Function<String, String> duplicate = s -> s.concat(s);
        System.out.println(upperCase.andThen(duplicate).apply("asd"));
        //BinaryOperator是输入两个同类型参数，输出一个同类型参数的接口。这里我们通过方法引用获得一个整数加法操作，通过Lambda表达式定义一个减法操作，然后依次调用
        BinaryOperator<Integer> add = Integer::sum;
        BinaryOperator<Integer> subtraction = (a, b) -> a - b;
        System.out.println(subtraction.apply(add.apply(1, 3), 2));

        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println(ints.stream().map(i -> new Point2D.Double((double) i % 3, (double) i / 3)).filter(i -> i.getY() > 2).mapToDouble(i -> i.distance(0, 0)).average().orElse(0));

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("ab");
        list.add("abc");

        System.out.println(Optional.ofNullable(2L).map(s -> s * s).get());
        Stream<String> stringStream = Optional.of(list).map(a -> a.stream().filter(b -> b.length() >= 2)).get();
        System.out.println(stringStream.toArray());
    }

    private void test() {
        cache.computeIfAbsent(123l, a -> (a.doubleValue()));
    }
}
