package com.diploma.application.service;


import com.diploma.application.model.Group;
import com.diploma.application.model.User;
import com.diploma.application.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
        group.setMembers(new HashSet<>());
        groupRepository.save(group);
        return group;
    }

    public Group addMembers(Group group,Set<User> members){
        Set<User> currentMembers = group.getMembers();

        currentMembers.addAll(members);

        groupRepository.save(group);
        return group;
    }

    public Group addMember(Group group, User user){
        Set<User> currentMembers = group.getMembers();
        currentMembers.add(user);
        group.setMembers(currentMembers);
        groupRepository.save(group);
        return group;
    }

    public Group removeMember(Group group, User user){
        Set<User> currentMembers = group.getMembers();
        currentMembers.remove(user);
        group.setMembers(currentMembers);
        groupRepository.save(group);
        return group;
    }
}
