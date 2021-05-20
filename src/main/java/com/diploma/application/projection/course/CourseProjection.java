package com.diploma.application.projection.course;

import com.diploma.application.model.course.Course;
import com.diploma.application.projection.user.UserOnlyInfoProjection;
import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

@Projection(name = "CourseProjection", types = {Course.class})
public interface CourseProjection {

    String getId();
    String getTitle();
    String getDescription();


    UserOnlyInfoProjection getCreator();


}
