package com.example.sagar.SpringSecurityWithJWT.controller;


import com.example.sagar.SpringSecurityWithJWT.model.*;
import com.example.sagar.SpringSecurityWithJWT.services.UserService;
import com.example.sagar.SpringSecurityWithJWT.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;



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
    String role= getUserRole(user.getUserName());
     return new JwtResponse(jwt,"200 OK","login successfull",role);
    }


    @RequestMapping(value = "/verified/{userName}",method = RequestMethod.GET)
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


    public String getUserRole( String userName)
    {
        return userService.getUserRole(userName);
    }

    @RequestMapping(value = "/user/id/{userName}",method = RequestMethod.GET)
    public JsonResponse getUserId(@PathVariable String userName)
    {
        return new JsonResponse("200 OK",String.valueOf(userService.getUserId(userName)));
    }


    @RequestMapping(value = "/user/{userName}",method = RequestMethod.GET)
    public User getUserDetails(@PathVariable String userName)
    {
        User user=userService.findUserDetails(userName);
        User user1=new User(user.getId(),user.getUserName(),user.getDeliveryAddress(),user.getPhone(),user.getAge(),user.getGender(),user.getRole());
        return user1;

            }

    @RequestMapping(value = "/user/{userId}/userName/{newUserName}",method = RequestMethod.PUT)
    public JsonResponse updateEmail(@PathVariable Integer userId,@PathVariable String newUserName)
    {
      String message=  userService.updateEmail(userId,newUserName);
      if (message.equalsIgnoreCase("User already exists"))

        return new JsonResponse("409 Conflict",message);
      else{
          return new JsonResponse("200 Ok",message);
      }

    }

    @RequestMapping(value = "/user/{userId}/phone/{newPhone}",method = RequestMethod.PUT)
    public JsonResponse updatePhone(@PathVariable Integer userId,@PathVariable String newPhone)
    {

        String message=  userService.updatePhone(userId,newPhone);
        if (message.equalsIgnoreCase("Phone is taken"))

            return new JsonResponse("409 Conflict",message);
        else{
            return new JsonResponse("200 Ok",message);
        }

    }
    @RequestMapping(value = "/user/{userId}/delivery/{newDelivery}",method = RequestMethod.PUT)
    public JsonResponse updateDelivery(@PathVariable Integer userId,@PathVariable String newDelivery)
    {
        String message=  userService.updateDelivery(userId,newDelivery);
        return new JsonResponse("200 OK",message);


    }


    @RequestMapping(value = "/seller/{sellerId}/name",method = RequestMethod.GET)
    public JsonResponse getSellerName(@PathVariable Integer sellerId){
        String userName=  userService.getSellerName(sellerId);
      return new JsonResponse("200 OK",userName);
    }

    @RequestMapping(value = "/order/nearby/{userId}",method = RequestMethod.GET)
    public List<Products> getNearByPeopleOrders(@PathVariable Integer userId){
        return userService.getNearbyPeopleOrders(userId);
    }


    @RequestMapping(value = "/feedback",method = RequestMethod.POST)
    public JsonResponse getNearByPeopleOrders(@RequestBody Feedback feedback){
         userService.saveFeedback(feedback);
         return new JsonResponse("200 Ok","Added feedback");
    }


}