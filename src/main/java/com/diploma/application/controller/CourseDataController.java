package com.diploma.application.controller;


import com.diploma.application.model.course.data.PdfData;
import com.diploma.application.model.course.data.VideoData;
import com.diploma.application.service.course.LessonService;
import com.diploma.application.service.course.data.PdfDataService;
import com.diploma.application.service.course.data.VideoDataService;
import com.diploma.application.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("course/lesson")
public class CourseDataController {

    private final PdfDataService pdfDataService;
    private final VideoDataService videoDataService;
    private final LessonService lessonService;

    public CourseDataController(PdfDataService pdfDataService, VideoDataService videoDataService, LessonService lessonService) {
        this.pdfDataService = pdfDataService;
        this.videoDataService = videoDataService;
        this.lessonService = lessonService;
    }

    @GetMapping("/pdf")
    public byte[] getPdf(@RequestParam String id) throws IOException {
        return pdfDataService.getPdfByteArray(id);
    }

    @GetMapping("/video")
    public byte[] getVideo(@RequestParam String id) throws IOException {
        return videoDataService.getVideoByteArray(id);
    }

    @PostMapping("/pdf")
    public ResponseEntity<?> addPdf(@RequestParam String id,@RequestParam String title, @RequestParam String description, @RequestBody byte[] file) {
        PdfData pdfData = pdfDataService.savePdf(title,description,file);

        lessonService.addPdf(lessonService.getLesson(id),pdfData);
        return ResponseEntity.ok()
                .body(new ApiResponse(true,"PDF added to lesson"));
    }

    @PostMapping("/video/local")
    public ResponseEntity<?> addVideo(@RequestParam String id,@RequestParam String title, @RequestParam String description, @RequestBody byte[] file) {
        VideoData videoData = videoDataService.saveVideo(title,description,file);
        lessonService.addVideo(lessonService.getLesson(id),videoData);
        return ResponseEntity.ok()
                .body(new ApiResponse(true,"Video (mp4) added to lesson"));
    }
}
