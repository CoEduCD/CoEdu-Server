package com.example.CoEduServer.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatReqDTO {
    private Long fileId;
    private String fileHash;
}
