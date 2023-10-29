package com.example.CoEduServer.controller;

import com.example.CoEduServer.service.FileService;
import com.example.CoEduServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/")
    public void getUserInfo(Principal principal){
        String username = principal.getName();
        System.out.println(username);
    }

}
