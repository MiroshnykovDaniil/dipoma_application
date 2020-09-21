package com.diploma.application.projection.course;

import com.diploma.application.model.course.Lesson;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "Lesson Projection without course data",types = {Lesson.class})
public interface LessonProjectionWithoutData {

    String getId();
    String getTitle();
    String getDescription();
}
