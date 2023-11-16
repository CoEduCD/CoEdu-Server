package com.example.CoEduServer.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteAuthorityDTO {
    private Long user_id;
    private String file_hash;
}
