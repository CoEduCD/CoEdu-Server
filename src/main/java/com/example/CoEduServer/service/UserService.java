package com.example.CoEduServer.service;

import com.example.CoEduServer.domain.User;
import com.example.CoEduServer.dto.req.LoginReq;
import com.example.CoEduServer.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    @Transactional
    public Long save(LoginReq loginReq){
        User byUser = userRepository.findByEmail(loginReq.getEmail()).orElse(null);
        System.out.println(byUser);
        if(byUser == null){

            User savedUser = userRepository.save(loginReq.toEntity());
            return savedUser.getId();
        }
        else{
            return byUser.getId();
        }
    }

//    public boolean isExistUserId(Long Id){
//        User byId = userRepository.findByUser_id(Id).orElse(null);
//
//        return byId != null;
//    }

//    public User getUser(Long Id){
//        Optional<User> user = userRepository.findByUser_id(Id);
//
//        return user;
//    }
}
