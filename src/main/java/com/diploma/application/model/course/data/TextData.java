package com.diploma.application.model.course.data;



import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity(name = "text_data")
@Data
@NoArgsConstructor
public class TextData extends CourseData {

    @NotBlank
    private String text;

}
