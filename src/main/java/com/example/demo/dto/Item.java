package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class Item implements Serializable {

    private long sum;

    private long add;

    public Item(long sum, long add) {
        this.sum = sum;
        this.add = sum;
    }

    public Item() {
    }


}
