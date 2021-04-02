package com.example.sagar.SpringSecurityWithJWT.controller;
/*
 * @created 01/{03}/2021 - 2:44 PM
 * @project SpringSecurityWithJWT
 * @author SAGAR DEVKOTA
 */

import com.example.sagar.SpringSecurityWithJWT.configuration.UserPrincipal;
import com.example.sagar.SpringSecurityWithJWT.model.Feedback;
import com.example.sagar.SpringSecurityWithJWT.model.JsonResponse;
import com.example.sagar.SpringSecurityWithJWT.model.Reviews;
import com.example.sagar.SpringSecurityWithJWT.model.User;
import com.example.sagar.SpringSecurityWithJWT.services.ProductService;
import com.example.sagar.SpringSecurityWithJWT.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ProductService productService;

    @Autowired
    UserController(UserService userService, ProductService productService){
        this.userService = userService;
        this.productService = productService;
    }


    @RequestMapping(value = "/details",method = RequestMethod.GET)
    public User getUserDetails(@CurrentSecurityContext Authentication authentication)
    {
        UserPrincipal loggedUser = (UserPrincipal) authentication.getPrincipal();
        User user = userService.getUser(loggedUser.getId());
        return User.builder()
                .userName(user.getUserName())
                .id(user.getId())
                .deliveryAddress(user.getDeliveryAddress())
                .gender(user.getGender())
                .age(user.getAge())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
    }
    @GetMapping("/userName/{userId}")
    public JsonResponse getUsername(@PathVariable int userId){
        return new JsonResponse("200 OK",userService.getUserName(userId));
    }

    @PutMapping(value = "/{newUserName}")
    public JsonResponse updateEmail(@CurrentSecurityContext Authentication authentication, @Valid @PathVariable String newUserName)
    {
        String message=  userService.updateEmail(getUserId(authentication),newUserName);
        if (message.equalsIgnoreCase("User already exists"))
            return new JsonResponse("409 Conflict",message);
        else{
            return new JsonResponse("200 Ok",message);
        }

    }

    private int getUserId(Authentication authentication){
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal(); //cast principal object to our user principal
        return user.getId();
    }


    @PutMapping(value = "/phone/{newPhone}")
    public JsonResponse updatePhone(@CurrentSecurityContext Authentication authentication,@Valid @PathVariable String newPhone)
    {
        String message=  userService.updatePhone(getUserId(authentication),newPhone);
        if (message.equalsIgnoreCase("Phone is taken"))
            return new JsonResponse("409 Conflict",message);
        else{
            return new JsonResponse("200 Ok",message);
        }

    }
    @PutMapping(value = "/delivery/{newDelivery}")
    public JsonResponse updateDelivery(@CurrentSecurityContext Authentication authentication,@PathVariable String newDelivery)
    {
        String message=  userService.updateDelivery(getUserId(authentication),newDelivery);
        return new JsonResponse("200 OK",message);
    }

    @PostMapping(value = "/reviews")
    public JsonResponse addReview(@RequestBody Reviews reviews) {
        productService.addReviews(reviews);
        return new JsonResponse("200 OK", "Added review");
    }

    @PutMapping(value = "/reviews")
    public JsonResponse updateReview(@RequestBody Reviews reviews) {
        productService.updateReview(reviews);
        return new JsonResponse("200 OK", "Updated review");
    }

    @PostMapping(value = "/feedbacks")
    public JsonResponse addFeedback(@RequestBody Feedback feedback){
        userService.saveFeedback(feedback);
        return new JsonResponse("200 Ok","Added feedback");
    }
}
