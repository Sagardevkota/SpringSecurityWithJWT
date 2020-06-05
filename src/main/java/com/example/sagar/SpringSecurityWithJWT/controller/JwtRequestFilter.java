package com.example.sagar.SpringSecurityWithJWT.controller;

import com.example.sagar.SpringSecurityWithJWT.services.MyUserDetailService;
import com.example.sagar.SpringSecurityWithJWT.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
   @Autowired
   private MyUserDetailService myUserDetailService;
   @Autowired
   private JwtUtil jwtUtil;

   public static String response;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final  String authorizationHeader=httpServletRequest.getHeader("Authorization");
        String userName=null;
        String jwt=null;
        if (authorizationHeader!=null&&authorizationHeader.startsWith("Bearer ")){
            jwt=authorizationHeader.substring(7);
            userName=jwtUtil.getUsernameFromToken(jwt);
        }
        if (userName!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=this.myUserDetailService.loadUserByUsername(userName);

            if (jwtUtil.validateToken(jwt,userDetails).equalsIgnoreCase("ok")){
                response="ok";
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else
                throw new ServletException();
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
