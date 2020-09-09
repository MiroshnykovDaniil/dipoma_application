package com.diploma.application.course.service;

import com.diploma.application.DiplomaApplicationTests;
import com.diploma.application.model.User;
import com.diploma.application.model.course.Course;
import com.diploma.application.repository.UserRepository;
import com.diploma.application.service.course.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


public class CourseServiceTest extends DiplomaApplicationTests {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createCourse(){
        User user = new User();
        userRepository.save(user);

        Course course = courseService.createCourse(user,"title","desc");
        assertThat(course.getId()).isNotEmpty();

    }


}
