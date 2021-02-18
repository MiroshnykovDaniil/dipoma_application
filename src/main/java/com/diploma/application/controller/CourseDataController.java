package com.diploma.application.controller;


import com.diploma.application.model.course.data.PdfData;
import com.diploma.application.service.course.data.PdfDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("course/lesson")
public class CourseDataController {

    private final PdfDataService pdfDataService;

    public CourseDataController(PdfDataService pdfDataService) {
        this.pdfDataService = pdfDataService;
    }

    @GetMapping("/pdf")
    public byte[] getPdf(@RequestParam String id) throws IOException {
        return pdfDataService.getPdfByteArray(id);
    }
}
