package com.example.CoEduServer.dto.req;

import com.example.CoEduServer.domain.File;
import com.example.CoEduServer.domain.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class FileCreateDTO {
    @NotNull
    String fileName;
    @NotNull
    String language;
    @NotNull
    String fileDetail;

    String fileHash;
    Long user_id;
    public File toEntity(){
        return File.builder()
                .fileName(fileName)
                .language(language)
                .fileDetail(fileDetail)
                .fileHash(fileHash)
                .role(Role.ADMIN)
                .build();
    }
}
