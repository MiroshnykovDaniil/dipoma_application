package com.diploma.application.controller;

import com.diploma.application.entity.User;
import com.diploma.application.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {

    private final Logger logger = LogManager.getLogger();
    private final UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(path = "/all")
    public List<User> findAll(){
        logger.info("UserController: FindAll");
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id){
        logger.info("UserController: getUserById");
        return userService.findById(id);
    }

    @PostMapping("/register")
    public void registerUser(
            @RequestParam String name,
            @RequestParam String pass
            ){
        logger.info("Creating new user");
        userService.register(name,pass);
        logger.info("User created");
    }

    @PostMapping("/login")
    public void login(
            @RequestParam String name,
            @RequestParam String pass
    ){

    }
}
