package com.example.demo.service;

import com.example.demo.model.ChatList;
import com.example.demo.model.ChatUserList;
import org.bson.Document;

import java.util.List;

/**
 * @auther zoujialiang
 * @date 2020/10/31 16:11
 */
public interface MogoService {
    /**
     * 聚合查询
     *
     * @return
     */
    public List<Document> aggregation();
}
