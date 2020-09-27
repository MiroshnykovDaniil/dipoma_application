package com.diploma.application.repository.course;

import com.diploma.application.model.course.data.PdfData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfDataRepository extends JpaRepository<PdfData,String> {
}
