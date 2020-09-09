package com.diploma.application.model.course.data.quiz;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Data
@NoArgsConstructor
@Entity(name = "free_answer_question")
public class FreeAnswerQuestion extends Question{

    @JoinColumn(name = "answer_id")
    @ManyToOne
    private Variant correctAnswer;

    private String userAnswer;

    @Override
    public String getType() {
        return "free";
    }

}
