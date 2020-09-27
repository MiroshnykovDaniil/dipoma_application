package com.diploma.application.course.controller;


import com.diploma.application.DiplomaApplicationTests;
import com.diploma.application.controller.CourseController;
import com.diploma.application.course.data.CourseDataTest;
import com.diploma.application.model.User;
import com.diploma.application.model.course.Lesson;
import com.diploma.application.model.course.data.PdfData;
import com.diploma.application.repository.UserRepository;
import com.diploma.application.service.course.LessonService;
import com.diploma.application.service.course.data.PdfDataService;
import com.diploma.application.util.CourseCreateRequest;
import com.diploma.application.util.PdfCreateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.transaction.Transactional;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class CourseControllerTest extends DiplomaApplicationTests {


    @InjectMocks
    private CourseController courseController;

    @Mock
    private User user;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private PdfDataService pdfDataService;

    @Before
    public void startTesting() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
    }


    @Transactional
    @Test
    public void createCourseTest() throws Exception {
        //User user = new User();
        //userRepository.save(user);

        CourseCreateRequest createRequest = new CourseCreateRequest();

        createRequest.setTitle("TEST_title");
        createRequest.setDescription("TEST_desc");

        mockMvc.perform(
                post("/course/create")
                        .contentType("application/json")
                .content(asJsonString(createRequest))
        ).andExpect(status().isCreated());

    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Test
    public void addPdfToLessonTest() throws Exception {

        PdfCreateRequest pdfCreateRequest = new PdfCreateRequest();
        Lesson lesson = lessonService.getLesson("0ca57fc0-9d37-4522-8fca-80a99d06c9d4");
        PdfData pdfData = new PdfData();
        byte[] pdf = pdfData.getPdf("d:\\","c8173910-542a-44ba-ac2e-8ac952890426");

       // byte[] pdf =  pdfDataService.getPdfByteArray("c8173910-542a-44ba-ac2e-8ac952890426");
        pdfCreateRequest.setLesson(lesson);
        pdfCreateRequest.setPdf(pdf);
        pdfCreateRequest.setPdfData(pdfData);

        mockMvc.perform(
                post("/course/lesson/add/pdf")
                        .contentType("application/json")
                        .content(asJsonString(pdfCreateRequest))
        ).andExpect(status().isCreated());


    }
}
