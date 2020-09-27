package com.diploma.application.service.course;

import com.diploma.application.model.course.Lesson;
import com.diploma.application.model.course.data.CourseData;
import com.diploma.application.model.course.data.PdfData;
import com.diploma.application.model.course.data.quiz.Quiz;
import com.diploma.application.repository.course.LessonRepository;
import com.diploma.application.service.course.quiz.QuizService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class LessonService {

    private final Logger logger = LogManager.getLogger();

    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    QuizService quizService;


    public Lesson createLesson(String title, String description){
        Lesson lesson = new Lesson();
        lesson.setTitle(title);
        lesson.setDescription(description);
        lesson.setLessonData(new HashSet<>());
        lessonRepository.save(lesson);
        return lesson;
    }

    public Lesson addPdf(Lesson lesson, PdfData pdfData){
        lesson = lessonRepository.getOne(lesson.getId());
        Set<CourseData> courseDataSet = lesson.getLessonData();
        courseDataSet.add(pdfData);
        lesson.setLessonData(courseDataSet);
        lessonRepository.save(lesson);
        return lesson;
    }

    public Lesson addQuiz(Lesson lesson, Quiz quiz){
        lesson = lessonRepository.getOne(lesson.getId());

        quizService.saveQuiz(quiz);

        logger.info("addQuiz: lesson"+lesson);
        logger.info("addQuiz: Quiz"+quiz);

        Set<CourseData> courseDataSet = lesson.getLessonData();
        courseDataSet.add(quiz);
        lesson.setLessonData(courseDataSet);
        lessonRepository.save(lesson);
        return lesson;
    }

    public Lesson getLesson(String id){
        return lessonRepository.getOne(id);
    }
}
