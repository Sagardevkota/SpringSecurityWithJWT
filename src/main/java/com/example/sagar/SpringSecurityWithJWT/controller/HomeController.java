package com.example.sagar.SpringSecurityWithJWT.controller;


import com.example.sagar.SpringSecurityWithJWT.model.JsonResponse;
import com.example.sagar.SpringSecurityWithJWT.model.JwtResponse;
import com.example.sagar.SpringSecurityWithJWT.model.User;
import com.example.sagar.SpringSecurityWithJWT.services.UserService;
import com.example.sagar.SpringSecurityWithJWT.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@RestController
public class HomeController
{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;



    @Autowired
    private JwtUtil jwtUtil;
    @RequestMapping(value = "/")
    public ModelAndView home()
    {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public JwtResponse createAuthenticationToken(@RequestBody User user) throws Exception {
   try
   {
       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
     );
   }
   catch (BadCredentialsException e){

       return new JwtResponse("409 Confilct","Incorrect email or password");
   }
   final UserDetails userDetails= userDetailsService
           .loadUserByUsername(user.getUserName());

      final String jwt=jwtUtil.generateToken(userDetails);
     return new JwtResponse(jwt,"200 OK","login successfull");
    }


    @RequestMapping(value = "/isVerified/{userName}",method = RequestMethod.GET)
    public Boolean isVerified(@PathVariable String userName)
    {
        return userService.isVerified(userName);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public JsonResponse register(@RequestBody User user)
    {

        if (userService.checkifUserExists(user.getUserName())>0)
            return  new JsonResponse("409 Conflict","User already exists");
        if (userService.checkIfUserPhoneExists(user.getPhone())>0)
            return new JsonResponse("409 Conflict","Phone number is taken");
        else
        {
            userService.register(user);
            return new JsonResponse("200 Ok","Registered successfully");
        }



    }

    @RequestMapping(value = "/getUserId/{userName}",method = RequestMethod.GET)
    public JsonResponse getUserId(@PathVariable String userName)
    {
        return new JsonResponse("200 OK",String.valueOf(userService.getUserId(userName)));
    }





}