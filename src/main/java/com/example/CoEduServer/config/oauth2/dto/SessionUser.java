package com.example.CoEduServer.config.oauth2.dto;

import com.example.CoEduServer.domain.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String email;

    public SessionUser(User user){
        this.email = user.getEmail();
    }
}
