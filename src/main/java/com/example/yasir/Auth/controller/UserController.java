package com.example.yasir.Auth.controller;


import com.example.yasir.Auth.bo.UpdateProfileRequest;
import com.example.yasir.Auth.entity.User;
import com.example.yasir.Auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }

    @PostMapping("/me")
    public ResponseEntity<User> updateProfile(@RequestBody UpdateProfileRequest profileRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        currentUser = userService.updateProfile(currentUser, profileRequest.getAddress(), profileRequest.getPhoneNumber());

        return ResponseEntity.ok(currentUser);
    }
}