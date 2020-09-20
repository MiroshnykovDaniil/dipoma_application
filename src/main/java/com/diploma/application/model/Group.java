package com.diploma.application.model;


import com.diploma.application.model.course.Course;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity(name = "usr_groups")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Group {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotBlank(message = "Group title missed")
    private String title;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "creator_id")
    @NotNull
    private User creator;

    @ManyToMany()
    @JoinColumn(name = "member_id")
    private Set<User> members;

    @ManyToMany
    @JoinColumn(name = "course_assigned_id")
    private Set<Course> assignedCourses;

    @ManyToMany
    @JoinColumn(name = "course_completed_id")
    private Set<Course> completedCourses;


}
