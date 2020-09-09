package com.diploma.application.repository.course.quiz;

import com.diploma.application.model.course.data.quiz.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantRepository extends JpaRepository<Variant, String> {
}
