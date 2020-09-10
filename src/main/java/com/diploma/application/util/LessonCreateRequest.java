package com.diploma.application.util;


import com.diploma.application.model.course.Course;
import lombok.Data;

@Data
public class LessonCreateRequest {

    private String title;
    private String description;

    private Course course;

}
