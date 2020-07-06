package com.diploma.application.controller;

import com.diploma.application.repository.UserRepository;
import com.diploma.application.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/users")
public class UserController {

    private final UserRepository userRepository;

    UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/get/")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public User getUser(@PathVariable String id){
        return userRepository.getOne(id);
    }


}
