package com.example.CoEduServer.dto.req;

import com.example.CoEduServer.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EditAuthorityDTO {
    private Role role;
    private Long userId;
    private Long fileId;
    private Long editUserId;
}
