package com.diploma.application.model.course.data;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity(name = "course_data")
@Data
@NoArgsConstructor
public class CourseData {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    private String id;

    @NotBlank(message = "data title cannot be empty")
    private String title;
    @NotBlank(message = "data description cannot be empty")
    private String description;

    private String dataType;
}
