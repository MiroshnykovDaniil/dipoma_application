package com.diploma.application.util;

import com.diploma.application.model.course.Lesson;
import com.diploma.application.model.course.data.PdfData;
import lombok.Data;

@Data
public class PdfCreateRequest {

    private Lesson lesson;
    private PdfData pdfData;
    private byte[] pdf;
}
