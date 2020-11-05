package com.example.demo.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Zoujialiang
 * @date 2020/9/16 14:27
 */
@Data
@ToString
public class RegisterDTO implements Serializable {
    private String kuaiId;

    private String phone;

    private String verifyCode;

    private String password;

    private String username;
}
