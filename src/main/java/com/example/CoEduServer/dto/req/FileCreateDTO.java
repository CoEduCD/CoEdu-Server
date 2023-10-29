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
    String file_name;
    @NotNull
    String language;
    @NotNull
    String file_detail;

    Long user_id;

}
