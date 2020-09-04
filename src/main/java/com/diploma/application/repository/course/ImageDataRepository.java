package com.diploma.application.repository.course;

import com.diploma.application.model.course.data.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDataRepository extends JpaRepository<ImageData,String> {
}
