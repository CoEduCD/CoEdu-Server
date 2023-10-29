package com.example.CoEduServer.controller;

import com.example.CoEduServer.service.FileService;
import com.example.CoEduServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final FileService fileService;

    
    @GetMapping("/")
    public void main(){
        System.out.println("print main");
    }

}
