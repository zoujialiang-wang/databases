package com.example.demo.model;

import lombok.Data;
import lombok.ToString;

/**
 * @author Zoujialiang
 * @date 2020/9/30 14:14
 */
@Data
@ToString
public class ChatUserList {
    private String dyId;
    private Long fansCount;
    private Long followCount;
    private String nickname;
    private String headUrl;
    private String shortId;
    private String fansUrl;
    private String consumeUrl;
    private String roomId;
}
