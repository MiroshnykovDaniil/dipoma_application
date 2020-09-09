package com.diploma.application.course.quiz;

import com.diploma.application.DiplomaApplicationTests;
import com.diploma.application.model.course.data.quiz.*;
import com.diploma.application.service.course.quiz.QuizService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class QuizServiceTest extends DiplomaApplicationTests {

    @Autowired
    private QuizService service;

    private final Logger logger = LogManager.getLogger();


    @Test
    public void createQuestion(){

        Quiz quiz = new Quiz();
        quiz = service.createQuiz();

        List<Question> questions = new ArrayList<>();
        Question question = new FreeAnswerQuestion();
        question.setTask("Count 5*10");
        Variant variant = new Variant();
        variant.setText("50");
        ((FreeAnswerQuestion) question).setCorrectAnswer(variant);
        ((FreeAnswerQuestion) question).setUserAnswer("40");

        questions.add(question);

        question = new MultipleVariantsQuestion();
        question.setTask("Count 5*10");
        question.setTask("aa");

        List<Variant> variants = new ArrayList<>();
        variants.add(variant);
        variant = new Variant();
        variant.setText("40");
        variants.add(variant);
        ((MultipleVariantsQuestion) question).setVariants(variants);
        questions.add(question);


        quiz.setQuestions(questions);
        logger.info("quiz questions count: "+quiz.getQuestions().size());
        service.saveQuiz(quiz);
        System.out.println(quiz);

        assertThat(quiz.getQuestions().get(1).getType()).isEqualTo(question.getType());
    }
}
