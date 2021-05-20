package com.diploma.application.repository.course;

import com.diploma.application.model.User;
import com.diploma.application.model.course.Course;
import com.diploma.application.model.course.data.CourseData;
import com.diploma.application.projection.course.CourseProjection;
import com.diploma.application.projection.course.CourseProjectionWithLessons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CourseRepository extends JpaRepository<Course,String> {

    CourseProjectionWithLessons getById(String id);
    Course getCourseById(String id);


    Set<CourseProjectionWithLessons> getByCreator(User user);

}
