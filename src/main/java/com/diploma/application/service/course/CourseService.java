package com.diploma.application.service.course;

import com.diploma.application.model.User;
import com.diploma.application.model.course.Course;
import com.diploma.application.repository.course.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    CourseDataRepository courseDataRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    ImageDataRepository imageDataRepository;
    @Autowired
    TextDataRepository textDataRepository;
    @Autowired
    TextImageDataRepository textImageDataRepository;


    public void createCourse(User creator, String title, String description){
        Course course = new Course();
        course.setTitle(title);
        course.setDescription(description);
        course.setCreator(creator);
        courseRepository.save(course);
    }




}
