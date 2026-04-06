package com.example.springboot_api_fundamentals.controller;

import com.example.springboot_api_fundamentals.model.User;
import com.example.springboot_api_fundamentals.service.UserManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserManagementService userService;

    public UserController(UserManagementService userService) {
        this.userService = userService;
    }

    // curl http://localhost:8080/api/users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.listAll();
    }

    // curl http://localhost:8080/api/users/1
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // curl "http://localhost:8080/api/users/search?name=Saad"
    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String name) {
        return userService.findByName(name);
    }

    // curl -X POST http://localhost:8080/api/users -H "Content-Type: application/json" -d '{"id": 3, "name": "Yassine", "email": "yassine@example.com"}'
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.add(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User record created successfully.");
    }

    // curl -X PUT http://localhost:8080/api/users/1 -H "Content-Type: application/json" -d '{"name": "Saad updated", "email": "saad.new@example.com"}'
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.update(id, updatedUser) 
                ? ResponseEntity.ok("User profile updated.") 
                : ResponseEntity.notFound().build();
    }

    // curl -X DELETE http://localhost:8080/api/users/2
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return userService.remove(id) 
                ? ResponseEntity.ok("User record removed.") 
                : ResponseEntity.notFound().build();
    }
}
