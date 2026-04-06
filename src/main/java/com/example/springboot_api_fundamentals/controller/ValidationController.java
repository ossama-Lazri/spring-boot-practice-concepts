package com.example.springboot_api_fundamentals.controller;

import com.example.springboot_api_fundamentals.dto.AuthRequest;
import com.example.springboot_api_fundamentals.dto.ProductDTO;
import com.example.springboot_api_fundamentals.dto.UserDTO;
import com.example.springboot_api_fundamentals.service.AuthenticationService;
import com.example.springboot_api_fundamentals.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/practice")
public class ValidationController {

    private final ProductService productService;
    private final AuthenticationService authService;

    public ValidationController(ProductService productService, AuthenticationService authService) {
        this.productService = productService;
        this.authService = authService;
    }

    // curl -X POST http://localhost:8080/api/practice/user -H "Content-Type: application/json" -d '{"username": "admin", "email": "admin@example.com"}'
    @PostMapping("/user")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO user) {
        return ResponseEntity.ok("User registration data is valid: " + user.getUsername());
    }

    // curl -X POST http://localhost:8080/api/practice/product -H "Content-Type: application/json" -d '{"name": "Laptop", "price": 999.0}'
    @PostMapping("/product")
    public ResponseEntity<String> addProduct(@Valid @RequestBody ProductDTO product) {
        productService.save(product);
        return ResponseEntity.ok("Product added successfully: " + product.getName());
    }

    // curl -X POST http://localhost:8080/api/practice/login -H "Content-Type: application/json" -d '{"login": "saad", "password": "password123"}'
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody AuthRequest auth) {
        authService.login(auth);
        return ResponseEntity.ok("Login attempt successful for: " + auth.getLogin());
    }
}
