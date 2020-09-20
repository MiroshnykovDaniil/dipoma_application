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

import java.util.HashSet;
import java.util.Set;

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

    @Test
    public void addMembersTest(){
        User user = new User();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        Set<User> userSet = new HashSet<>();
        userSet.add(user1);
        userSet.add(user2);
        userSet.add(user3);

        Group group = groupService.createGroup(user,"Title");

        groupService.addMembers(group.getId(),userSet);
        assertThat(group.getMembers()).isNotEmpty();


    }

    @Test
    public void addingMemberTest(){
        User user = new User();
        User user1 = new User();
        userRepository.save(user);
        userRepository.save(user1);

        Group group = groupService.createGroup(user,"Title");

        groupService.addMember(group,user1);
        assertThat(group.getMembers().contains(user1));

    }

    @Test
    public void removeMemberTest(){
        User user = new User();
        User user1 = new User();
        userRepository.save(user);
        userRepository.save(user1);

        Group group = groupService.createGroup(user,"Title");

        groupService.addMember(group,user1);
        groupService.removeMember(group,user1);

        assertThat(!group.getMembers().contains(user1));

    }
}
