package com.diploma.application.user.controller;

import com.diploma.application.DiplomaApplicationTests;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerIntegrationTest extends DiplomaApplicationTests {

    @Autowired
    private MockMvc mockMvc;


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
}
