package com.example.sagar.SpringSecurityWithJWT.configuration;

import com.example.sagar.SpringSecurityWithJWT.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

   private final MyUserDetailService myUserDetailService;
   private final JwtUtil jwtUtil;

    @Autowired
   public JwtRequestFilter(MyUserDetailService myUserDetailService, JwtUtil jwtUtil){
       this.myUserDetailService = myUserDetailService;
       this.jwtUtil = jwtUtil;
   }


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String userName = null;
        String jwt = null;
        if (authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
            jwt =  authorizationHeader.substring(7);
            userName = jwtUtil.getUsernameFromToken(jwt);
        }

        //if there is no user logged in in security context and username from given jwt is not null
        if (userName!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = this.myUserDetailService.loadUserByUsername(userName);

            if (jwtUtil.validateToken(jwt,userDetails).equalsIgnoreCase("ok")){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                //if authenticated set context with the authenticationtoken
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                logger.info(userDetails.getAuthorities());
            }
            else
                throw new ServletException();
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
