package com.diploma.application.repository.course.quiz;

import com.diploma.application.model.course.data.quiz.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,QuestionRepository> {
}
