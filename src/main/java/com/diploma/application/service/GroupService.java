package com.diploma.application.service;


import com.diploma.application.model.Group;
import com.diploma.application.model.User;
import com.diploma.application.model.course.Course;
import com.diploma.application.repository.GroupRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GroupService {
    private final Logger logger = LogManager.getLogger();

    private final GroupRepository groupRepository;

    @Autowired
    UserService userService;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group createGroup(User creator, String title){
        Group group = new Group();
        group.setCreator(creator);
        group.setTitle(title);
        group.setMembers(new HashSet<>());
        group.setAssignedCourses(new HashSet<>());
        group.setCompletedCourses(new HashSet<>());
        groupRepository.save(group);
        return group;
    }

    public Group addMembers(String groupId,Set<User> members){
        Group group = groupRepository.getOne(groupId);
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

    public Group getGroupInfo(String groupId){
        Group group = groupRepository.getOne(groupId);
        return group;
    }

    public List<Group> getGroupList(String id){
        logger.info("getGroupList:"+groupRepository.findAllByMembers(userService.findById(id)));
        return groupRepository.findAllByMembers(userService.findById(id));
    }
}
