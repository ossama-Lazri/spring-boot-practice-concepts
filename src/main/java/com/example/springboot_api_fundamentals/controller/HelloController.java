package com.example.springboot_api_fundamentals.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "Welcome to the base API service.";
    }

    @GetMapping("/api/status")
    public String status() {
        return "Service is online and running correctly.";
    }
}
