package com.example.CoEduServer.dto.res;

import com.example.CoEduServer.domain.User_File;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetFilesDTO {
    private Long fileId;
    private String fileName;
    private String language;
    private String fileDetail;

    public static GetFilesDTO toEntity(User_File user_file) {
        return GetFilesDTO.builder()
                .fileId(user_file.getFile().getId())
                .fileName(user_file.getFile().getFileName())
                .language(user_file.getFile().getLanguage())
                .fileDetail(user_file.getFile().getFileDetail())
                .build();
    }

}