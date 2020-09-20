package com.diploma.application.controller;


import com.diploma.application.model.Group;
import com.diploma.application.model.User;
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

    @PostMapping("/addMembers")
    public ResponseEntity<?> addMembersToGroup(@RequestParam String id, @Valid @RequestBody Set<User> userSet) throws URISyntaxException {
        Group group = groupService.addMembers(id,userSet);
        return ResponseEntity.ok()
                .body(new ApiResponse(true,"Users Added"));
    }

    @GetMapping()
    public Group getGroupById(@RequestParam String id){
        return groupService.getGroupInfo(id);
    }

    @GetMapping("/getList")
    public List<Group> getGroupList(@CurrentUser UserPrincipal userPrincipal){
        return groupService.getGroupList(userPrincipal.getId());
    }

    @GetMapping("/getListForUser")
    public List<Group> getGroupLitByUserId(@RequestParam String id){
        return groupService.getGroupList(id);
    }
}
