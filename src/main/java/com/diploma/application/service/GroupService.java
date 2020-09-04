package com.diploma.application.service;


import com.diploma.application.model.Group;
import com.diploma.application.model.User;
import com.diploma.application.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group createGroup(User creator, String title){
        Group group = new Group();
        group.setCreator(creator);
        group.setTitle(title);
        group.setParticipants(new HashSet<>());
        groupRepository.save(group);
        return group;
    }
}
