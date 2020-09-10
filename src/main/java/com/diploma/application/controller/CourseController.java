package com.diploma.application.controller;

import com.diploma.application.model.User;
import com.diploma.application.model.course.Course;
import com.diploma.application.model.course.Lesson;
import com.diploma.application.model.course.data.quiz.FreeAnswerQuestion;
import com.diploma.application.model.course.data.quiz.Question;
import com.diploma.application.security.CurrentUser;
import com.diploma.application.security.UserPrincipal;
import com.diploma.application.service.UserService;
import com.diploma.application.service.course.CourseService;
import com.diploma.application.service.course.LessonService;
import com.diploma.application.util.ApiResponse;
import com.diploma.application.util.CourseCreateRequest;
import com.diploma.application.util.LessonCreateRequest;
import com.diploma.application.util.QuizCreateRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController()
@RequestMapping("course")

public class CourseController {
    private final Logger logger = LogManager.getLogger();


    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;
    @Autowired
    LessonService lessonService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@CurrentUser UserPrincipal userPrincipal,@Valid @RequestBody CourseCreateRequest createRequest) throws URISyntaxException {
        User creator = userService.findById(userPrincipal.getId());
        Course course = courseService.createCourse(creator,createRequest.getTitle(),createRequest.getDescription());

        return ResponseEntity.created(new URI(course.getId()))
                .body(new ApiResponse(true, "Course was created successfully"));
    }


    @PostMapping("/lesson/create")
    public ResponseEntity<?> createLesson(@Valid @RequestBody LessonCreateRequest createRequest) throws URISyntaxException {
        Course course = courseService.addLesson(createRequest.getCourse(),createRequest.getTitle(),createRequest.getDescription());
        return ResponseEntity.created(new URI(course.getId()))
                .body(new ApiResponse(true, "Lesson for course was created successfully"));
    }

    @PostMapping("/lesson/add/quiz")
    public ResponseEntity<?> createQuizForLesson(@Valid @RequestBody QuizCreateRequest createRequest) throws URISyntaxException{

        logger.info(createRequest.getQuiz().getQuestions());
        createRequest.getQuiz().getQuestions().stream().forEach(question ->{
            logger.info("Type:"+question.getType()+"Task:"+question.getTask());
            if(question instanceof FreeAnswerQuestion)
                logger.info("FREE!!");
            else
                logger.info("NOT FREE");
        } );


        Lesson lesson = lessonService.addQuiz(createRequest.getLesson(),createRequest.getQuiz());
        return ResponseEntity.created(new URI(lesson.getId()))
                .body(new ApiResponse(true, "Quiz for lesson was created successfully"));
    }

}
