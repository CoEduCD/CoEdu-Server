package com.example.CoEduServer.dto.req;

import com.example.CoEduServer.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddAuthorityDTO{
    private Long fileId;
    private Long userId;
    private String email;
}
