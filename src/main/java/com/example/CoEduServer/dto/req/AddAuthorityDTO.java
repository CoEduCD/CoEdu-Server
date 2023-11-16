package com.example.CoEduServer.dto.req;

import com.example.CoEduServer.domain.File;
import com.example.CoEduServer.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddAuthorityDTO{
    private Long file_id;
    private Long user_id;
    private String file_name;
    private String language;
    private String file_detail;
    private String file_hash;
    private Role role;

    public File toEntity(){
        return File.builder()
                .file_name(file_name)
                .language(language)
                .file_detail(file_detail)
                .file_hash(file_hash)
                .role(role)
                .build();
    }
}
