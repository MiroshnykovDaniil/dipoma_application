package com.diploma.application.projection.course;

import com.diploma.application.model.course.Course;
import com.diploma.application.projection.user.UserOnlyInfoProjection;
import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

@Projection(name="Course with lessons",types = {Course.class})
public interface CourseProjectionWithLessons {

    Set<LessonProjectionWithoutData> getLessons();
    String getId();
    String getTitle();
    String getDescription();


    UserOnlyInfoProjection getCreator();


}
