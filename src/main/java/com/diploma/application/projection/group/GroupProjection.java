package com.diploma.application.projection.group;

import com.diploma.application.model.Group;
import com.diploma.application.projection.course.CourseProjection;
import com.diploma.application.projection.user.UserOnlyInfoProjection;
import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

@Projection(name = "Group projection",types = {Group.class})
public interface GroupProjection {

    String getId();
    String getTitle();
    UserOnlyInfoProjection getCreator();
    Set<UserOnlyInfoProjection> getMembers();

    Set<CourseProjection> getAssignedCourses();
    Set<CourseProjection> getCompletedCourses();
}
