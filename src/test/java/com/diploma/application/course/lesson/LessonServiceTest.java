package com.diploma.application.course.lesson;

import com.diploma.application.DiplomaApplicationTests;
import com.diploma.application.model.course.Lesson;
import com.diploma.application.model.course.data.quiz.Quiz;
import com.diploma.application.repository.course.LessonRepository;
import com.diploma.application.repository.course.quiz.QuizRepository;
import com.diploma.application.service.course.LessonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class LessonServiceTest extends DiplomaApplicationTests {

    @Autowired
    LessonService lessonService;
    @Autowired
    QuizRepository quizRepository;

    @Test
    public void createLessonWithQuiz(){
        Quiz quiz = new Quiz();
        quiz = quizRepository.save(quiz);

        Lesson lesson = lessonService.createLesson("title","desc");
        lessonService.addQuiz(lesson,quiz);
        assertThat(lesson.getLessonData()).isNotEmpty();
    }
}
