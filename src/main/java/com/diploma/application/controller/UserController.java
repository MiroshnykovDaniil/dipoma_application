package com.diploma.application.controller;

import com.diploma.application.repository.UserRepository;
import com.diploma.application.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final Logger logger = LogManager.getLogger();

    UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/all")
    public List<User> getUsers(){
        logger.info(userRepository.findAll());
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id){
        logger.info("getting users");
        return userRepository.getOne(id);
    }

    @PostMapping("/register")
    public void registerUser(
            @RequestParam String name,
            @RequestParam String pass
            ){
        User user = new User();
        user.setName(name);
        user.setPassword(pass);
        logger.info("Creating new user");
        userRepository.save(user);
    }
}
