package com.example.CoEduServer.dto;

import lombok.Data;

@Data
public class ChatDTO {
    // 각 채널을 구분할 수 있는 id
    private Integer channelId;
    private Integer writerId;
    private String chat;
}
