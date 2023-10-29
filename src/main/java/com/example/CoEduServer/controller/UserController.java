package com.example.CoEduServer.controller;

import com.example.CoEduServer.config.oauth2.dto.SessionUser;
import com.example.CoEduServer.domain.User;
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

    private final HttpSession httpSession;
    private final UserRepository userRepository;
    @GetMapping("/user")
    public void getUserInfo() {
        SessionUser loginUser = (SessionUser) httpSession.getAttribute("user");
        Optional<User> optionalUser = userRepository.findById(loginUser.getUser_id());
        User findUser = optionalUser.get();
        System.out.println(findUser);
    }

    @GetMapping("/login/oauth2/code/{registrationId}")
    public void googleLogin(@RequestParam String code, @PathVariable String registrationId){
        System.out.println("code =" + code);
        System.out.println("registrationId = "  + registrationId);
    }
}
