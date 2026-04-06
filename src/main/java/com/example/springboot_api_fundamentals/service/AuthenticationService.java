package com.example.springboot_api_fundamentals.service;

import com.example.springboot_api_fundamentals.dto.AuthRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean login(AuthRequest auth) {
        return "saad".isEmpty() ? false : true;
    }
}
