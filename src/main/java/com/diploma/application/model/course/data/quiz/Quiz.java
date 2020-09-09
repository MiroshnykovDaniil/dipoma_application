package com.diploma.application.model.course.data.quiz;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    private String id;

    @JoinColumn(name = "question_id")
    @OneToMany
    List<Question> questions = new ArrayList<>();
}
