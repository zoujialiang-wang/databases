package com.example.demo.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @auther zoujialiang
 * @date 2020/10/31 15:06
 */
@Data
@ToString
public class TestModel {
    private String name;

    private Long age;

    private List<String> list;

    private Map<Object, Object> map;

}
