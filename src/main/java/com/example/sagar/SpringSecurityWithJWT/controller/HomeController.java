package com.example.sagar.SpringSecurityWithJWT.controller;


import com.example.sagar.SpringSecurityWithJWT.model.JwtResponse;
import com.example.sagar.SpringSecurityWithJWT.model.UserInfo;
import com.example.sagar.SpringSecurityWithJWT.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
    @RequestMapping(value = "/")
    public ModelAndView home(){
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserInfo userInfo) throws Exception {
   try {
       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(userInfo.getUserName(), userInfo.getPassword())
     );
   }
   catch (BadCredentialsException e){
       throw new Exception("Incorrect username or password",e);
   }
   final UserDetails userDetails= userDetailsService
           .loadUserByUsername(userInfo.getUserName());

      final String jwt=jwtUtil.generateToken(userDetails);
     return ResponseEntity.ok(new JwtResponse(jwt));
    }



}