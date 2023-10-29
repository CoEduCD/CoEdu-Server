package com.example.CoEduServer.controller;

import com.example.CoEduServer.config.oauth2.dto.SessionUser;
import com.example.CoEduServer.service.FileService;
import com.example.CoEduServer.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final HttpSession httpSession;
    @GetMapping("/")
    public void getUserInfo(){
        SessionUser loginUser = (SessionUser) httpSession.getAttribute("user");
        System.out.println("loginuser=" + loginUser.getUser_id());
    }

}
