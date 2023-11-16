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
    private String email;
    private Role role;

    public File toEntity(File file){
        return File.builder()
                .fileName(file.getFileName())
                .language(file.getLanguage())
                .fileDetail(file.getFileDetail())
                .fileHash(file.getFileHash())
                .role(role)
                .build();
    }
}
