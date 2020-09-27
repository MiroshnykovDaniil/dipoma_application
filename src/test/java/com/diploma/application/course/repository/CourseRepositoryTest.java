package com.diploma.application.course.repository;


import com.diploma.application.DiplomaApplicationTests;
import com.diploma.application.model.User;
import com.diploma.application.model.course.Course;
import com.diploma.application.model.course.data.CourseData;
import com.diploma.application.model.course.data.TextData;
import com.diploma.application.repository.UserRepository;
import com.diploma.application.repository.course.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CourseRepositoryTest extends DiplomaApplicationTests {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseDataRepository courseDataRepository;

    @Autowired
    ImageDataRepository imageDataRepository;

    @Autowired
    TextDataRepository textDataRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void courseRepositoryTest(){
        Course course = new Course();
        User creator = new User();
        userRepository.save(creator);
        course.setCreator(creator);
        course.setTitle("title");
        course.setDescription("desc");
        courseRepository.save(course);

        Course test = courseRepository.getOne(course.getId());

        assertThat(test.equals(course));
    }

    @Test
    public void courseDataRepositoryTest(){
        CourseData courseData = new CourseData();
        courseData.setTitle("title");
        courseData.setDescription("desc");
        courseDataRepository.save(courseData);
        assertThat(courseDataRepository.getOne(courseData.getId())).isEqualTo(courseData);
    }


    @Test
    public void textDataRepositoryTest(){
        TextData textData = new TextData();
        textData.setTitle("title");
        textData.setDescription("desc");
        textData.setText("some text here \r\n");
        textDataRepository.save(textData);
        TextData res = textDataRepository.getOne(textData.getId());
        assertThat(res).isEqualTo(textData);
    }




}
