package com.diploma.application.controller;

import com.diploma.application.model.User;
import com.diploma.application.model.course.Course;
import com.diploma.application.security.CurrentUser;
import com.diploma.application.security.UserPrincipal;
import com.diploma.application.service.UserService;
import com.diploma.application.service.course.CourseService;
import com.diploma.application.util.ApiResponse;
import com.diploma.application.util.CourseCreateRequest;
import com.diploma.application.util.LessonCreateRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController()
@RequestMapping("course")

public class CourseController {

    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@CurrentUser UserPrincipal userPrincipal,@Valid @RequestBody CourseCreateRequest createRequest) throws URISyntaxException {
        User creator = userService.findById(userPrincipal.getId());
        Course course = courseService.createCourse(creator,createRequest.getTitle(),createRequest.getDescription());

        return ResponseEntity.created(new URI(course.getId()))
                .body(new ApiResponse(true, "Course was created successfully"));
    }


    @PostMapping("/lesson/create")
    public ResponseEntity<?> createLesson(@Valid @RequestBody LessonCreateRequest createRequest) throws URISyntaxException {
        Course course = courseService.addLesson(createRequest.getCourse(),createRequest.getTitle(),createRequest.getDescription());
        return ResponseEntity.created(new URI(course.getId()))
                .body(new ApiResponse(true, "Lesson for course was created successfully"));
    }

}
