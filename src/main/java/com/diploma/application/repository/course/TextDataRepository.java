package com.diploma.application.repository.course;

import com.diploma.application.model.course.data.TextData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextDataRepository extends JpaRepository<TextData,String> {
}
