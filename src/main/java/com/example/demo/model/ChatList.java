package com.example.demo.model;


import com.example.demo.dto.Chat;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Zoujialiang
 * @date 2020/9/30 11:16
 */
@Data
@ToString
public class ChatList {
    private String roomId;
    private List<Chat> chatList;
}
