package com.diploma.application.service;


import com.diploma.application.model.Group;
import com.diploma.application.model.User;
import com.diploma.application.model.course.Course;
import com.diploma.application.projection.group.GroupProjection;
import com.diploma.application.repository.GroupRepository;
import com.diploma.application.service.course.CourseService;
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
    @Autowired
    CourseService courseService;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group createGroup(User creator, String title){
        Group group = new Group();
        group.setCreator(creator);
        group.setTitle(title);
        group.setMembers(new HashSet<>());
        Set<User> members = group.getMembers();
        members.add(creator);
        group.setMembers(members);
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

    public Group addAssignedCourse(String groupId,String courseId){
        Course course = courseService.getCourse(courseId,courseId);

        Group group = groupRepository.getOne(groupId);
        Set<Course> assignedCourses = group.getAssignedCourses();
        Set<Course> completedCourses = group.getCompletedCourses();
        assignedCourses.add(course);
        completedCourses.remove(course);
        groupRepository.save(group);
        return group;
    }

    public Group addCompletedCourse(String groupId,String courseId){
        Course course = courseService.getCourse(courseId,courseId);

        Group group = groupRepository.getOne(groupId);
        Set<Course> completedCourses = group.getCompletedCourses();
        Set<Course> assignedCourses = group.getAssignedCourses();
        completedCourses.add(course);
        assignedCourses.remove(course);
        groupRepository.save(group);
        return group;
    }


    public Group removeMembers(String groupId,Set<User> members){
        Group group = groupRepository.getOne(groupId);
        Set<User> currentMembers = group.getMembers();

        currentMembers.removeAll(members);


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

    public List<GroupProjection> getGroupList(String id){
        logger.info("getGroupList:"+groupRepository.findAllByMembers(userService.findById(id)));
        return groupRepository.findAllByMembers(userService.findById(id));
    }

    public List<GroupProjection> getGroupListByCreator(String id){
        logger.info("getGroupList:"+groupRepository.findAllByCreator(userService.findById(id)));
        return groupRepository.findAllByCreator(userService.findById(id));
    }

    public void deleteGroup(String id){
        logger.info("deleteGroup:"+id);
        Group group = groupRepository.getOne(id);
        groupRepository.delete(group);

    }
}
