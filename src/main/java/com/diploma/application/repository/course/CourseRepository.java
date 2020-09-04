package com.diploma.application.repository.course;

import com.diploma.application.model.course.Course;
import com.diploma.application.model.course.data.CourseData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,String> {
}
