package com.example.demo.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {


    private String _id;


    private String password;
    /**
     * 是否激活
     */
    private Boolean activate;
}
