package com.diploma.application.user.service;


import com.diploma.application.DiplomaApplicationTests;
import com.diploma.application.domain.User;
import com.diploma.application.repository.UserRepository;
import com.diploma.application.service.UserService;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceTest extends DiplomaApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Test
    public void RolesTest(){
        userService.register("name","pass");
        User user = userService.findByUserName("name");
        assertThat(user.getRoles()).isNotEmpty();
    }
}
