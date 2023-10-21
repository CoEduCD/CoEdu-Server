package com.example.CoEduServer.service;

import com.example.CoEduServer.domain.User;
import com.example.CoEduServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public boolean isExistEmail(String email){
        User byEmail = userRepository.findByEmail(email).orElse(null);
        return byEmail != null;
    }
}
