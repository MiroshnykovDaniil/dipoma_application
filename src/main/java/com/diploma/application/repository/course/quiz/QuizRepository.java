package com.diploma.application.repository.course.quiz;

import com.diploma.application.model.course.data.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,String> {
}
