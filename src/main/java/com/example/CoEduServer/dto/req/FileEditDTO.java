package com.example.CoEduServer.dto.req;

import com.example.CoEduServer.domain.User_File;
import com.example.CoEduServer.domain.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class FileEditDTO {
    private String fileName;
    private String language;
    private String fileDetail;
    private Long fileId;
    private Long userId;

}

