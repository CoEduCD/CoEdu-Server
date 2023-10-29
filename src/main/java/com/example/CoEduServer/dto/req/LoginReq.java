package com.example.CoEduServer.dto.req;


import com.example.CoEduServer.domain.File;
import com.example.CoEduServer.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class LoginReq {
    String name;
    String email;
    String picture;
    List<File> fileList;
    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .build();
    }
}