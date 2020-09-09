package com.diploma.application.repository.course.quiz;

import com.diploma.application.model.course.data.quiz.ComparisonQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<ComparisonQuestion.Answer,String> {
}
