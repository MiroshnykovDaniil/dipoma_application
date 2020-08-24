package com.diploma.application.security;

import com.diploma.application.model.User;
import com.diploma.application.service.UserService;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class EmailPasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserService userService;

    private final Logger logger = LogManager.getLogger();


    @SneakyThrows
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        logger.info("Authentication authenticate");

        String email = authentication.getName();
        String password = (String) authentication.getCredentials();
        logger.info("email:"+email+"... Password:"+password);


        User user = userService.loadByEmail(email);

        logger.info("user (loadByEmail) password:"+user.getPassword());

        if(!user.getPassword().equals(password))
            throw new BadCredentialsException("Bad credentials");

        logger.info("Authentication return :"+new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword()));
        return new UsernamePasswordAuthenticationToken(user.getId(),user.getPassword());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
