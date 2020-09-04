package com.diploma.application.repository.course;

import com.diploma.application.model.course.data.TextImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextImageDataRepository extends JpaRepository<TextImageData,String> {
}
