package com.example.springboot_api_fundamentals.controller;

import com.example.springboot_api_fundamentals.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private List<User> users = new ArrayList<>();

    public UserController() {
        users.add(new User(1L, "Saad", "saad@example.com"));
        users.add(new User(2L, "Amine", "amine@example.com"));
    }

    // curl http://localhost:8080/api/users
    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    // curl http://localhost:8080/api/users/1
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // curl "http://localhost:8080/api/users/search?name=Saad"
    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String name) {
        return users.stream()
                .filter(u -> u.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    // curl -X POST http://localhost:8080/api/users -H "Content-Type:
    // application/json" -d '{"id": 3, "name": "Yassine", "email":
    // "yassine@example.com"}'
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        users.add(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User record created successfully.");
    }

    // curl -X PUT http://localhost:8080/api/users/1 -H "Content-Type:
    // application/json" -d '{"name": "Saad updated", "email":
    // "saad.new@example.com"}'
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                user.setName(updatedUser.getName());
                user.setEmail(updatedUser.getEmail());
                return ResponseEntity.ok("User profile updated.");
            }
        }
        return ResponseEntity.notFound().build();
    }

    // curl -X DELETE http://localhost:8080/api/users/2
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean removed = users.removeIf(u -> u.getId().equals(id));
        return removed ? ResponseEntity.ok("User record removed.") : ResponseEntity.notFound().build();
    }
}
