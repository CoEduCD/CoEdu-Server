package com.example.CoEduServer.config.oauth2.dto;

import com.example.CoEduServer.domain.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String email;
    private String name;
    private Long user_id;

    public SessionUser(User user){
        this.email = user.getEmail();
        this.name = user.getName();
        this.user_id = user.getUser_id();
    }
}
