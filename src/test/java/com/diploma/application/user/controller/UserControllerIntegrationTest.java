package com.diploma.application.user.controller;

import com.diploma.application.DiplomaApplicationTests;
import com.diploma.application.controller.UserController;
import com.diploma.application.model.User;
import com.diploma.application.util.SignUpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;


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

    @Autowired
    private WebApplicationContext context;


    @Before
    public void startTesting() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userControllerUnderTest).build();
    }

    @Test
    void manualRegistrationTest() throws Exception{
        String name = "User name";
        String pass = "1234pass";
        String email = "123@mail.co";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail(email);
        signUpRequest.setName(name);
        signUpRequest.setPassword(pass);
        params.add("name",name);
        params.add("email",email);
        params.add("password",pass);
        mockMvc.perform(
                post("/auth/signup")
                .contentType("application/json")
                .content(asJsonString(signUpRequest))
        )
                .andExpect(status().isCreated());
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


    @Test
    void selectAllUsers() throws Exception{
        mockMvc.perform(
                get("/users/all")
                .contentType(MediaType.APPLICATION_JSON)

        )
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = "06bd8ece-1a3e-4a85-b97a-5915232e7648")
    void selectUser() throws Exception{
        String token ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyYTY3ZTNkMy00NTBiLTQzYjAtYTRlZC05MTgxZTRiNjdmYzEiLCJpYXQiOjE2MjEyNzAwMDAsImV4cCI6MTYyMjEzNDAwMH0.V8-mrT6uACC-HieMofwE6nW33nhRjZRyLYX_CDRF19DhkSs8TUdBcYBti5pedvN2O7ShSCycQSsbweQHyHTWmg";
        mockMvc.perform(
                get("/users/me")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)

        )
                .andExpect(status().isOk());
    }
}
