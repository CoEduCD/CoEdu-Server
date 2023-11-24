package com.example.CoEduServer.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FileDeleteDTO {
    @NotNull
    private Long fileId;
    private Long userId;
}
