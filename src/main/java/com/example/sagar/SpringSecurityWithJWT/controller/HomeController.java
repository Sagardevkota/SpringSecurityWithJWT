package com.example.sagar.SpringSecurityWithJWT.controller;


import com.example.sagar.SpringSecurityWithJWT.model.JwtResponse;
import com.example.sagar.SpringSecurityWithJWT.model.User;
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
    private JwtUtil jwtUtil;
    @RequestMapping(value = "/")
    public ModelAndView home()
    {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user) throws Exception {
   try
   {
       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
     );
   }
   catch (BadCredentialsException e){
       throw new Exception("Incorrect username or password",e);
   }
   final UserDetails userDetails= userDetailsService
           .loadUserByUsername(user.getUserName());

      final String jwt=jwtUtil.generateToken(userDetails);
     return ResponseEntity.ok(new JwtResponse(jwt));
    }




}