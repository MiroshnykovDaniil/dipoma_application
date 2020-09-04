package com.diploma.application.model.course.data;



import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity(name = "text_data")
public class TextData extends CourseData {

    @NotBlank
    private String text;

}
