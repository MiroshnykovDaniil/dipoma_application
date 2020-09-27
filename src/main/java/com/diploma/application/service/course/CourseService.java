package com.diploma.application.service.course;

import com.diploma.application.model.User;
import com.diploma.application.model.course.Course;
import com.diploma.application.model.course.Lesson;
import com.diploma.application.projection.course.CourseProjectionWithLessons;
import com.diploma.application.repository.course.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CourseService {

    private final Logger logger = LogManager.getLogger();

    @Autowired
    CourseDataRepository courseDataRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    ImageDataRepository imageDataRepository;
    @Autowired
    TextDataRepository textDataRepository;

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

    public CourseProjectionWithLessons getCourse(String id){
        logger.info("getCourse:id:"+id);
        logger.info("getCourse:courserep.getone"+courseRepository.getOne(id));
        return courseRepository.getById(id);
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
