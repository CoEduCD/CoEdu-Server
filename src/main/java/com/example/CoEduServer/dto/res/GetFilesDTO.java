package com.example.CoEduServer.dto.res;

import com.example.CoEduServer.domain.BaseTimeEntity;
import com.example.CoEduServer.domain.User_File;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetFilesDTO extends BaseTimeEntity {
    private Long fileId;
    private String fileName;
    private String language;
    private String fileDetail;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

    public static GetFilesDTO toEntity(User_File user_file) {
        return GetFilesDTO.builder()
                .fileId(user_file.getFile().getId())
                .fileName(user_file.getFile().getFileName())
                .language(user_file.getFile().getLanguage())
                .fileDetail(user_file.getFile().getFileDetail())
                .createdTime(user_file.getFile().getCreatedTime())
                .modifiedTime(user_file.getFile().getModifiedTime())
                .build();
    }

}