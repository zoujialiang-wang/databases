package com.example.demo.util;

import lombok.Data;

/**
 * 包装异常信息
 * @auther zoujialiang
 * @date 2020/11/19 16:51
 */
@Data
public class ErrorResponse {

    private String message;
    private String errorTypeName;

    public ErrorResponse(Exception e) {
        this(e.getClass().getName(), e.getMessage());
    }

    public ErrorResponse(String errorTypeName, String message) {
        this.errorTypeName = errorTypeName;
        this.message = message;
    }
}