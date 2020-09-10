package com.diploma.application.course.controller;


import com.diploma.application.DiplomaApplicationTests;
import com.diploma.application.controller.CourseController;
import com.diploma.application.controller.UserController;
import com.diploma.application.model.User;
import com.diploma.application.repository.UserRepository;
import com.diploma.application.util.CourseCreateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

    @Before
    public void startTesting() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
    }


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
}
