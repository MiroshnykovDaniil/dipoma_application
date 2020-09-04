package com.diploma.application.group.service;


import com.diploma.application.DiplomaApplicationTests;
import com.diploma.application.model.Group;
import com.diploma.application.model.User;
import com.diploma.application.repository.GroupRepository;
import com.diploma.application.repository.UserRepository;
import com.diploma.application.service.GroupService;
import com.diploma.application.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class GroupServiceTest extends DiplomaApplicationTests {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    GroupService groupService;

    @Autowired
    UserRepository userRepository;



    @Test
    public void CreateGroupTest(){
        User user = new User();
        userRepository.save(user);

        Group group = groupService.createGroup(user,"Title");


        assertThat(group.getId()).isNotEmpty();
    }
}
