package com.diploma.application.repository.course;

import com.diploma.application.model.course.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson,String> {
}
