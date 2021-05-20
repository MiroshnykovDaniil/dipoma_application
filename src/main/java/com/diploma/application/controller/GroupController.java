package com.diploma.application.controller;


import com.diploma.application.model.Group;
import com.diploma.application.model.User;
import com.diploma.application.model.course.Course;
import com.diploma.application.projection.group.GroupProjection;
import com.diploma.application.security.CurrentUser;
import com.diploma.application.security.UserPrincipal;
import com.diploma.application.service.GroupService;
import com.diploma.application.service.UserService;
import com.diploma.application.util.ApiResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/group")
public class GroupController {
    private final Logger logger = LogManager.getLogger();


    @Autowired
    GroupService groupService;
    @Autowired
    UserService userService;

    @PostMapping
    //@PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    public ResponseEntity<?> createGroup(@RequestParam String title, @CurrentUser UserPrincipal userPrincipal) throws URISyntaxException {

        User creator = userService.findById(userPrincipal.getId());
        Group group = groupService.createGroup(creator,title);
        return ResponseEntity.created(new URI(group.getId()))
                .body(new ApiResponse(true,"Group is created"));
    }

    @DeleteMapping
    //@PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteGroup(@RequestParam String id) throws URISyntaxException {
        groupService.deleteGroup(id);
        return ResponseEntity.ok()
                .body(new ApiResponse(true,"Group is deleted"));
    }

    @PostMapping("/addMembers")
    public ResponseEntity<?> addMembersToGroup(@RequestParam String id, @Valid @RequestBody Set<User> userSet) throws URISyntaxException {
        Group group = groupService.addMembers(id,userSet);
        return ResponseEntity.ok()
                .body(new ApiResponse(true,"Users Added"));
    }

    @PostMapping("/addCourse/assigned")
    public ResponseEntity<?> addAssignedCourseToGroup(@RequestParam String id, @RequestParam String courseId) throws URISyntaxException {
        Group group = groupService.addAssignedCourse(id,courseId);
        return ResponseEntity.ok()
                .body(new ApiResponse(true,"Users Added"));
    }

    @PostMapping("/addCourse/completed")
    public ResponseEntity<?> addCompletedCourseToGroup(@RequestParam String id,  @RequestParam String courseId) throws URISyntaxException {
        Group group = groupService.addCompletedCourse(id,courseId);
        return ResponseEntity.ok()
                .body(new ApiResponse(true,"Users Added"));
    }


    @GetMapping()
    public Group getGroupById(@RequestParam String id){
        return groupService.getGroupInfo(id);
    }

    @PostMapping("/deleteMembers")
    public ResponseEntity<?> deleteMember(@RequestParam String id, @Valid @RequestBody Set<User> userSet){
        Group group = groupService.removeMembers(id,userSet);
        return ResponseEntity.ok()
                .body(new ApiResponse(true,"Users deleted"));

    }

    @GetMapping("/getList")
    public List<GroupProjection> getGroupList(@CurrentUser UserPrincipal userPrincipal){
        List<GroupProjection> groupList = groupService.getGroupList(userPrincipal.getId());
        return groupList;
    }

    @GetMapping("/getCreatorList")
    public List<GroupProjection> getCreatorGroupList(@CurrentUser UserPrincipal userPrincipal){
        List<GroupProjection> groupList = groupService.getGroupListByCreator(userPrincipal.getId());
        return groupList;
    }

    @GetMapping("/getListForUser")
    public List<GroupProjection> getGroupLitByUserId(@RequestParam String id){
        return groupService.getGroupList(id);
    }



}
