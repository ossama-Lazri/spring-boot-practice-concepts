package com.example.springboot_api_fundamentals.service;

import com.example.springboot_api_fundamentals.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserManagementService {

    private List<User> users = new ArrayList<>();

    public UserManagementService() {
        users.add(new User(1L, "Saad", "saad@example.com"));
        users.add(new User(2L, "Amine", "amine@example.com"));
    }

    public List<User> listAll() {
        return users;
    }

    public Optional<User> findById(Long id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    public List<User> findByName(String name) {
        return users.stream()
                .filter(u -> u.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    public void add(User user) {
        users.add(user);
    }

    public boolean update(Long id, User updatedUser) {
        return findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return true;
        }).orElse(false);
    }

    public boolean remove(Long id) {
        return users.removeIf(u -> u.getId().equals(id));
    }
}
