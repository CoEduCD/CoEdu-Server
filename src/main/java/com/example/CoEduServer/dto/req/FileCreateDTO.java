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
    String file_name;
    @NotNull
    String language;
    @NotNull
    String file_detail;

    String file_hash;
    Long user_id;
    public File toEntity(){
        return File.builder()
                .file_name(file_name)
                .language(language)
                .file_detail(file_detail)
                .file_hash(file_hash)
                .role(Role.ADMIN)
                .build();
    }
}
