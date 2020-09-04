package com.diploma.application.repository.course;

import com.diploma.application.model.course.data.CourseData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDataRepository extends JpaRepository<CourseData,String> {
}
