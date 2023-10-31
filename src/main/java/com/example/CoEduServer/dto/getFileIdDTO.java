package com.example.CoEduServer.dto;

import com.example.CoEduServer.domain.User_File;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class getFileIdDTO {
    private Long id;

    public static getFileIdDTO of(User_File user_file){
        return new getFileIdDTO(user_file.getId());
    }
}
