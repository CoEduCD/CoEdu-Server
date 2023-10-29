package com.example.CoEduServer.controller;

import com.example.CoEduServer.domain.User;
import com.example.CoEduServer.dto.req.LoginReq;
import com.example.CoEduServer.dto.res.BaseResponse;
import com.example.CoEduServer.repository.UserRepository;
import com.example.CoEduServer.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/")
    public ResponseEntity<Long> createUser(@RequestBody LoginReq loginReq){
        Long userId = userService.save(loginReq);
        return ResponseEntity.ok().body(userId);
    }
}
