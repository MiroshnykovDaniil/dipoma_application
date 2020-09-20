package com.diploma.application.model.course;


import com.diploma.application.model.course.data.CourseData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "lesson")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Lesson {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String title;
    private String description;

    @OneToMany
    @JoinColumn(name = "lesson_data_id")
    private Set<CourseData> lessonData;

}
