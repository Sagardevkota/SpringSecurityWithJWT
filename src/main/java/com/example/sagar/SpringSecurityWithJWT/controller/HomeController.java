package com.example.sagar.SpringSecurityWithJWT.controller;


import com.example.sagar.SpringSecurityWithJWT.configuration.MyUserDetailService;
import com.example.sagar.SpringSecurityWithJWT.model.JsonResponse;
import com.example.sagar.SpringSecurityWithJWT.model.JwtResponse;
import com.example.sagar.SpringSecurityWithJWT.model.User;
import com.example.sagar.SpringSecurityWithJWT.model.UserDto;
import com.example.sagar.SpringSecurityWithJWT.services.UserService;
import com.example.sagar.SpringSecurityWithJWT.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class HomeController {

    private final AuthenticationManager authenticationManager; //for authenticating with jdbc in login
    private final MyUserDetailService userDetailsService;
    private final UserService userService; //for handling user services
    private final JwtUtil jwtUtil; //for generating token



    @Autowired
    HomeController(AuthenticationManager authenticationManager,
                   MyUserDetailService userDetailsService,
                   UserService userService,
                   JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;

    }


    @PostMapping(value = "/login")
    public JwtResponse createAuthenticationToken(@Valid @RequestBody UserDto user) {

        //this end point is not secured by spring security so we manually authenticate with authentication manager
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
            );
        }

        //if not authenticated throw and catch and show error
        catch (BadCredentialsException e) {

            return new JwtResponse("409 Confilct", "Incorrect email or password");
        }

        //get userDetails with current username
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getUserName());


        //generate jwt by providing userdetails
        int id = userDetailsService.getId();
        final String jwt = jwtUtil.generateToken(userDetails);
        String role = userDetails.getAuthorities().toString();
        return new JwtResponse(jwt, "200 OK", "login successful", role);
    }


    @PostMapping(value = "/register")
    public JsonResponse register( @RequestBody User user) {

        if (userService.checkifUserExists(user.getUserName()) > 0)
            return new JsonResponse("409 Conflict", "User already exists");
        if (userService.checkIfUserPhoneExists(user.getPhone()) > 0)
            return new JsonResponse("409 Conflict", "Phone number is taken");
        else {
            userService.register(user,"USER");
            return new JsonResponse("200 Ok", "Registered successfully");
        }
    }





}