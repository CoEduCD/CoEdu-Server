package com.example.CoEduServer.dto.req;

import com.example.CoEduServer.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EditAuthorityDTO {
    private String fileHash;
    private Role role;
    private Long user_id;
}
