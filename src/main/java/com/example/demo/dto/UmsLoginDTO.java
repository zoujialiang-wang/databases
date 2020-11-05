package com.example.demo.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Zoujialiang
 * @date 2020/9/18 11:06
 */
@Data
@ToString
public class UmsLoginDTO implements Serializable {
    private String phone;
    private String password;
}
