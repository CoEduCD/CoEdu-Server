package com.example.CoEduServer.dto.res;

import com.example.CoEduServer.domain.BaseTimeEntity;
import com.example.CoEduServer.domain.User_File;
import com.example.CoEduServer.domain.enums.Role;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetFilesDTO extends BaseTimeEntity {
    private Long userId;
    private Long fileId;
    private Role role;
    private String name;
    private String fileName;
    private String language;
    private String fileDetail;
    private String fileHash;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

    public static GetFilesDTO toEntity(User_File user_file) {
        return GetFilesDTO.builder()
                .fileId(user_file.getFile().getId())
                .userId(user_file.getUser().getId())
                .name(user_file.getUser().getName())
                .fileName(user_file.getFile().getFileName())
                .language(user_file.getFile().getLanguage())
                .fileDetail(user_file.getFile().getFileDetail())
                .fileHash(user_file.getFile().getFileHash())
                .role(user_file.getFile().getRole())
                .createdTime(user_file.getFile().getCreatedTime())
                .modifiedTime(user_file.getFile().getModifiedTime())
                .build();
    }

}