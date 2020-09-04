package com.diploma.application.model.course;


import com.diploma.application.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity(name = "course")
public class Course {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotBlank(message = "course title cannot be empty or consist of spaces")
    private String title;

    @NotBlank(message = "course description cannot be empty or consist of spaces")
    private String description;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    @NotNull
    private User creator;

}
