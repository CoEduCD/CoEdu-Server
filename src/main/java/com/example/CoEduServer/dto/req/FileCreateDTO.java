package com.example.CoEduServer.dto.req;

import com.example.CoEduServer.domain.File;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class FileCreateDTO {
    @NotNull
    private String file_name;
    @NotNull
    private String language;
    @NotNull
    private String file_detail;
    private Long userId;

//    @Builder
//    public FileCreateDTO(String file_name, String language, String file_detail
//    )
}
