package com.diploma.application.service.course;

import com.diploma.application.model.course.Lesson;
import com.diploma.application.model.course.data.CourseData;
import com.diploma.application.model.course.data.quiz.Quiz;
import com.diploma.application.repository.course.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class LessonService {
    @Autowired
    LessonRepository lessonRepository;


    public Lesson createLesson(String title, String description){
        Lesson lesson = new Lesson();
        lesson.setTitle(title);
        lesson.setDescription(description);
        lesson.setLessonData(new HashSet<>());
        lessonRepository.save(lesson);
        return lesson;
    }

    public Lesson addQuiz(Lesson lesson, Quiz quiz){
        Set<CourseData> courseDataSet = lesson.getLessonData();
        courseDataSet.add(quiz);
        lesson.setLessonData(courseDataSet);
        lessonRepository.save(lesson);
        return lesson;
    }
}
