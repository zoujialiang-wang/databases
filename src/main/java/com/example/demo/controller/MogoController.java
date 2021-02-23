package com.example.demo.controller;

import cn.hutool.http.server.HttpServerRequest;
import com.example.demo.service.MogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther zoujialiang
 * @date 2020/10/31 16:00
 */
@RestController
public class MogoController {

    @Autowired
    private MogoService mogoService;


    @PostMapping("/test")
    public Object test(@RequestBody Object params, HttpServerRequest httpServerRequest) {
        System.out.println(params);
        return "test";
    }

    @GetMapping("/test2")
    public Object test2(HttpServerRequest httpServerRequest) {
        return "test222222222222222";
    }
}
