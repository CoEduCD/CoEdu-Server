package com.example.CoEduServer.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void socialLogin(String code, String registrationId) {
        System.out.println("code = " + code);
        System.out.println("registrationId = " + registrationId);
    }
}
