package com.example.demo.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//订单类
@Data
public class Order {

    public static void main(String[] args) {
        Arrays.asList("a1", "a2", "a3").stream().map(x -> x.toUpperCase()).forEach(System.out::println);

        List<Order> orders = new ArrayList<>();
//        orders.stream()
//                .filter(Objects::nonNull)
//                .filter(order -> order.getPlacedAt().isAfter(LocalDateTime.now().minusMonths(6)))
//                .filter(order -> order.getTotalPrice() > 40)
//                .collect(Collectors.toList())
//                .forEach(System.out::println);


    }

    private Long id;
    private Long customerId;//顾客ID
    private String customerName;//顾客姓名
    private List<OrderItem> orderItemList;//订单商品明细
    private Double totalPrice;//总价格
    private LocalDateTime placedAt;//下单时间
}

//订单商品类
@Data
@AllArgsConstructor
@NoArgsConstructor
class OrderItem {
    private Long productId;//商品ID
    private String productName;//商品名称
    private Double productPrice;//商品价格
    private Integer productQuantity;//商品数量
}

//顾客类
@Data
@AllArgsConstructor
class Customer {
    private Long id;
    private String name;//顾客姓名
}

