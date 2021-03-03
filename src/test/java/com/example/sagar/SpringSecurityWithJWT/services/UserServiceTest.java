package com.example.sagar.SpringSecurityWithJWT.services;
/*
 * @created 02/{03}/2021 - 2:58 PM
 * @project SpringSecurityWithJWT
 * @author SAGAR DEVKOTA
 */

import com.example.sagar.SpringSecurityWithJWT.model.User;
import com.example.sagar.SpringSecurityWithJWT.repository.UserRepository;
import com.example.sagar.SpringSecurityWithJWT.services.UserService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest //@SpringBootTest : This annotation is used to load complete application context for end to end integration testing
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("This checks phone number exists or not")
    public void checkIfPhoneExists(){
        when(userRepository.checkIfUserPhoneExist("9846710086")) //mocking db as calling actual db is not good
                .thenReturn(Collections.singletonList(User.builder()
                        .userName("sagardevkota55@fb.com")
                        .password("sagar123")
                        .build()));
        Assertions.assertTrue(true, String.valueOf(userService.checkIfUserPhoneExists("9846710086")>1));
    }


}
