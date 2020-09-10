package com.diploma.application.service.course;

import com.diploma.application.model.User;
import com.diploma.application.model.course.Course;
import com.diploma.application.model.course.Lesson;
import com.diploma.application.repository.course.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
    @Autowired
    LessonRepository lessonRepository;


    public Course createCourse(User creator, String title, String description){
        Course course = new Course();
        course.setTitle(title);
        course.setDescription(description);
        course.setCreator(creator);
        courseRepository.save(course);
        return course;
    }

    public Course getCourse(String id){
        return courseRepository.getOne(id);
    }

    public Course addLesson(Course course, String title, String description){
        course = courseRepository.getOne(course.getId());
        Set<Lesson> lessons = course.getLessons();
        Lesson lesson = new Lesson();
        lesson.setTitle(title);
        lesson.setDescription(description);
        lessonRepository.save(lesson);
        lessons.add(lesson);
        course.setLessons(lessons);
        courseRepository.save(course);
        return course;
    }






}
