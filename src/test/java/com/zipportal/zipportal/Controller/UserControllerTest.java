package com.zipportal.zipportal.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipportal.zipportal.Model.Assignment;
import com.zipportal.zipportal.Model.Repo;
import com.zipportal.zipportal.Model.User;
import com.zipportal.zipportal.Repository.AssignmentRepository;
import com.zipportal.zipportal.Repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserControllerTest {

    private MockMvc mvc;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    //this is apparently magic
    private JacksonTester<User> jsonUser;

    @Before
    public void setup() {
        // We would need this line if we would not use MockitoJUnitRunner
        // MockitoAnnotations.initMocks(this);
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        //MockMVC standalone approach
        mvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }

    @Test
    public void createUser() throws Exception {

        //given
        given(userRepository.getOne(1L))
                .willReturn(new User(1L, "Dave", "Dave", "real@email.com"));

        //when
        MockHttpServletResponse response = mvc.perform(
                post("/user").
                contentType(MediaType.APPLICATION_JSON).content(
                        jsonUser.write(new User(1L, "Dave", "Dave", "real@email.com")).getJson()
                )).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

}