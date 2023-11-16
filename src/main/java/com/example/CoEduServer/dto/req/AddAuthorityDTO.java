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
    private String fileName;
    private String language;
    private String fileDetail;
    private String fileHash;
    private Role role;

    public File toEntity(){
        return File.builder()
                .fileName(fileName)
                .language(language)
                .fileDetail(fileDetail)
                .fileHash(fileHash)
                .role(role)
                .build();
    }
}
