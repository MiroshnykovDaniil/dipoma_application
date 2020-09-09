package com.diploma.application.model.course.data.quiz;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "multiple_answer")
public class ComparisonQuestion extends Question{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    private String id;


    @JoinColumn(name = "answer_id")
    @OneToMany
    private List<Answer> answers;

    @Override
    public String getType() {
        return "multiple";
    }


    @NoArgsConstructor
    @Data
    @Entity(name = "question_answer")
    public class Answer {

        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name="uuid",strategy = "uuid2")
        private String id;

        private String text;

        @JoinColumn(name = "variant_id")
        @ManyToOne
        private Variant variant;
    }
}
