package com.example.CoEduServer.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteAuthorityDTO {
    private Long userId;
    private Long fileId;
    private Long deleteUserId;

}
