package com.example.CoEduServer.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class FileEditDTO {
    @NotNull
    private String fileName;
    @NotNull
    private String language;
    @NotNull
    private String fileDetail;
    private Long file_id;
}
