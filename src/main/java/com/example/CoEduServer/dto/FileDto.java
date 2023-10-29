package com.example.CoEduServer.dto;

import com.example.CoEduServer.domain.File;
import com.example.CoEduServer.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@AllArgsConstructor
@Builder
@ToString
public class FileDto {
    private Long file_id;
    private String file_name;
    private String file_detail;
    private Long user_id;
    private Role role;

    public static FileDto of(File file){
        return FileDto.builder()
                .file_id(file.getFile_id())
                .file_name(file.getFile_name())
                .file_detail(file.getFile_detail())
                .user_id(file.getUser().getUser_id())
                .role(file.getRole())
                .build();
    }
}
