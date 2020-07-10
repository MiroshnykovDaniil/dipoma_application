package com.diploma.application.user.controller;

import com.diploma.application.DiplomaApplicationTests;
import com.diploma.application.controller.UserController;
import com.diploma.application.domain.User;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerIntegrationTest extends DiplomaApplicationTests {

    @InjectMocks
    private UserController userControllerUnderTest;

    @Mock
    private User user;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void startTesting() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userControllerUnderTest).build();
    }

    @Test
    void manualRegistrationTest() throws Exception{
        String name = "User name";
        String pass = "1234pass";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name",name);
        params.add("pass",pass);
        mockMvc.perform(
                post("/users/register")
                .contentType("application/json")
                .params(params)
        )
                .andExpect(status().isOk());
    }

    @Test
    void selectAllUsers() throws Exception{
        mockMvc.perform(
                get("/users/all")
                .contentType(MediaType.APPLICATION_JSON)

        )
                .andExpect(status().isOk());
    }

    @Test
    void selectUser() throws Exception{
        mockMvc.perform(
                get("/users/0")
                        .contentType(MediaType.APPLICATION_JSON)

        )
                .andExpect(status().isOk());
    }
}
