package com.example.CoEduServer.controller;

import com.example.CoEduServer.dto.res.BaseResponse;
import com.example.CoEduServer.service.FileService;
import com.example.CoEduServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final FileService fileService;

    
    @GetMapping("/main")
    public void main(){
        System.out.println("print main");
    }
//    @GetMapping("/main/{email}")w
//    public ResponseEntity<? extends BaseResponse> getFiles(@PathVariable String email) throws Exception{
//        return ResponseEntity.status(200).body(new BaseResponse("전체 조회가 성공하였습니다.", 200));
//    }

}
