package com.example.demo.controller;

import com.example.demo.model.ChatList;
import com.example.demo.model.ChatUserList;
import com.example.demo.service.MogoService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
    public Object test() {
        List<Document> test = mogoService.aggregation();
        return test;
    }
}
