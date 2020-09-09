package com.diploma.application.repository.course.quiz;

import com.diploma.application.model.course.data.quiz.FreeAnswerQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeAnswerQuestionRepository extends JpaRepository<FreeAnswerQuestion,String> {
}
