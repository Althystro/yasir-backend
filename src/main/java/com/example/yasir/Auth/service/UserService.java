package com.example.yasir.Auth.service;

import com.example.yasir.Auth.entity.User;
import com.example.yasir.Auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }

    public User updateProfile(User user, String address, String phoneNumber){
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);
        return userRepository.save(user);
    }
}