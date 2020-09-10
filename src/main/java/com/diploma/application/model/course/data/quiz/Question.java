package com.diploma.application.model.course.data.quiz;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.swing.plaf.MenuBarUI;

@Data
@NoArgsConstructor
@Entity(name = "questions")

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = FreeAnswerQuestion.class, name = "free"),
                @JsonSubTypes.Type(value = MultipleVariantsQuestion.class, name = "single"),
                @JsonSubTypes.Type(value = ComparisonQuestion.class, name = "comparison")
        }
)
public class Question {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    private String id;

    private String task;

    private String type;
}
