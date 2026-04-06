package com.example.springboot_api_fundamentals.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthRequest {

    @NotBlank(message = "Login is required.")
    private String login;

    @NotBlank(message = "Password is required.")
    @Size(min = 8, message = "Minimum 8 characters.")
    private String password;

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
