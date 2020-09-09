package com.diploma.application.model.course.data.quiz;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.metamodel.EntityType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Data
@Entity(name = "single_answer_questions")
public class MultipleVariantsQuestion extends Question{

    private String question;


    @JoinColumn(name = "variant_id")
    @OneToMany
    private List<Variant> variants;


    @Override
    public String getType() {
        return "single";
    }
}
