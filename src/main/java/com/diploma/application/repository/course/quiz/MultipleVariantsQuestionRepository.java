package com.diploma.application.repository.course.quiz;

import com.diploma.application.model.course.data.quiz.MultipleVariantsQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultipleVariantsQuestionRepository extends JpaRepository<MultipleVariantsQuestion,String> {
}
