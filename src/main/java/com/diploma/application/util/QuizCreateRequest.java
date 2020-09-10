package com.diploma.application.util;

import com.diploma.application.model.course.Lesson;
import com.diploma.application.model.course.data.quiz.Quiz;
import lombok.Data;

@Data
public class QuizCreateRequest {

    private Lesson lesson;
    private Quiz quiz;
}
