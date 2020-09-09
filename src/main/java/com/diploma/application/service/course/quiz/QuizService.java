package com.diploma.application.service.course.quiz;


import com.diploma.application.model.course.data.quiz.*;
import com.diploma.application.repository.course.quiz.AnswerRepository;
import com.diploma.application.repository.course.quiz.QuestionRepository;
import com.diploma.application.repository.course.quiz.QuizRepository;
import com.diploma.application.repository.course.quiz.VariantRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class QuizService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private VariantRepository variantRepository;

    @Autowired
    private AnswerRepository answerRepository;

    private final Logger logger = LogManager.getLogger();



    public Quiz createQuiz(){
        logger.info("createQuiz()");
        Quiz quiz = new Quiz();
        quizRepository.save(quiz);
        return quiz;
    }

    public void saveQuiz(Quiz quiz){
        logger.info("saveQuiz: "+quiz.getId());
        logger.info("saveQuiz.Count of questions:"+quiz.getQuestions().size());
        quiz.getQuestions().stream().forEach(this::createQuestion);
        quizRepository.save(quiz);
    }

    public void addQuestion(Quiz quiz,Question question){
        questionRepository.save(question);
        List<Question> questions = quiz.getQuestions();
        questions.add(question);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
        return;
    }

    public void createVariant(Variant variant){
        logger.info("createVariant: "+variant.getId());

        variantRepository.save(variant);
    }

    public void createQuestion(Question question){
        logger.info("createQuestion: "+question.getId()+";Type:"+question.getType());
        switch (question.getType()){
            case "single":{
                    ((MultipleVariantsQuestion) question).getVariants().stream().forEach(variantRepository::save);
            } break;
            case "comparison":{
                ((ComparisonQuestion)question).getAnswers().stream().forEach(answerRepository::save);
            } break;
            case "free":{
                createVariant(((FreeAnswerQuestion)question).getCorrectAnswer());
            }break;
        }
        questionRepository.save(question);

    }

}
