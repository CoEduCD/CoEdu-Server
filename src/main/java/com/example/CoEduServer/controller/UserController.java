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
    public ResponseEntity<?> getUserInfo(){
        SessionUser loginUser = (SessionUser) httpSession.getAttribute("user");
        User user = userRepository.findByUser_id(loginUser.getUser_id());
        return ResponseEntity.ok().body(user);
    }
//    @PostMapping("/")
//    public ResponseEntity<?> getUser(@RequestBody Long Id){
//        if(userService.isExistUserId(Id)){
//            return ResponseEntity.status(400).body(new BaseResponse("존재하지 않는 아이디 입니다.", 400));
//        }
//
//        Optional<User> user = userRepository.findByUser_id(Id);
//
//        return ResponseEntity.ok(user);
//    }
}
