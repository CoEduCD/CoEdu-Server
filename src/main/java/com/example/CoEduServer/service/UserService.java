package com.example.CoEduServer.service;

import com.example.CoEduServer.domain.User;
import com.example.CoEduServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public boolean isExistEmail(String email){
        User byEmail = userRepository.findByEmail(email).orElse(null);
        return byEmail != null;
    }

    public boolean isExistUserId(Long Id){
        User byId = userRepository.findByUser_id(Id).orElse(null);

        return byId != null;
    }

    public User getUser(Long Id){
        Optional<User> user = userRepository.findByUser_id(Id);

        return user;
    }
}
