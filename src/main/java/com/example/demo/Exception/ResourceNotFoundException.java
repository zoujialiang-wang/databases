package com.example.demo.Exception;

/**
 * @auther zoujialiang
 * @date 2020/11/19 16:53
 */
public class ResourceNotFoundException extends RuntimeException{
    private String message;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
