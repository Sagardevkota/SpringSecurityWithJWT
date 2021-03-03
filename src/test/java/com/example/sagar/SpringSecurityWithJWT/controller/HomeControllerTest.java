package com.example.sagar.SpringSecurityWithJWT.controller;

import com.example.sagar.SpringSecurityWithJWT.model.JwtResponse;
import com.example.sagar.SpringSecurityWithJWT.model.User;
import com.example.sagar.SpringSecurityWithJWT.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class HomeControllerTest {


    public MockMvc mockMvc;
    //MockMVC class is part of Spring MVC test framework which helps in testing the controllers explicitly starting a Servlet container

    @Autowired
    public WebApplicationContext webApplicationContext;


    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    //convert to jwt response object from json string
    public static JwtResponse toJsonResponse(String jsonResult) throws JsonProcessingException {
        return new ObjectMapper().readValue(jsonResult,JwtResponse.class);
    }

    public static String toJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void createAuthenticationToken() throws Exception {
        User user = User.builder()
                .userName("sagardevkota55@fb.com")
                .password("S@gar123")
                .build();

        MvcResult mvcResult = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(user))) //convert above user object to json and send it as content
                .andExpect(status().isOk()).andReturn(); //expect 200
        JwtResponse jsonResponse = toJsonResponse(mvcResult.getResponse().getContentAsString()); //get the actual response from controller as string and convert to jwt response object
        log.info(jsonResponse.toString());
        boolean result = jsonResponse.getMessage().equals("login successful");
        assertTrue(result);
    }

    @Test
    void register() throws Exception {
        User user = User.builder()
                .userName("sagardevkota55@fb.com")
                .password("Sagar123")
                .phone("980123451")
                .age("25")
                .gender("male")
                .build();

        MvcResult mvcResult = mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(user))) //convert above user object to json and send it as content
                .andExpect(status().isOk()).andReturn();//expect 200

        String result = mvcResult.getResponse().getContentAsString();
        log.debug(result);
        JwtResponse jwtResponse = toJsonResponse(result);
        assertEquals("Registered Successfully",jwtResponse.getMessage());
        log.info(jwtResponse.toString());

    }
}