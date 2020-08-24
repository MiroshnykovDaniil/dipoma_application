package com.diploma.application.controller;

import com.diploma.application.model.User;
import com.diploma.application.security.CurrentUser;
import com.diploma.application.security.UserPrincipal;
import com.diploma.application.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
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


    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal){
        return userService.findById(userPrincipal.getId());
    }
}
