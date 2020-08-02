package com.diploma.application.service;

import com.diploma.application.entity.Role;
import com.diploma.application.entity.User;
import com.diploma.application.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByName(s);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return user;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(String id){
        return userRepository.findById(id).orElseThrow(() ->new EntityNotFoundException(id));
    }

    public void register(String name, String pass){
        User user = new User();
        user.setName(name);
        user.setPassword(pass);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return;
    }

    public User findByUserName(String name){
        return userRepository.findByName(name);
    }
}
