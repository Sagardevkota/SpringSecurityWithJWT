package com.example.sagar.SpringSecurityWithJWT.controller;
/*
 * @created 01/{03}/2021 - 2:54 PM
 * @project SpringSecurityWithJWT
 * @author SAGAR DEVKOTA
 */

import com.example.sagar.SpringSecurityWithJWT.model.JsonResponse;
import com.example.sagar.SpringSecurityWithJWT.model.User;
import com.example.sagar.SpringSecurityWithJWT.services.AzureBlobService;
import com.example.sagar.SpringSecurityWithJWT.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AzureBlobService azureBlobService;
    private final UserService userService;

    @Autowired
    AdminController(AzureBlobService azureBlobService,UserService userService){
        this.azureBlobService = azureBlobService;
        this.userService = userService;
    }

    @DeleteMapping("/images/{imageName}")
    JsonResponse deleteImage(@PathVariable String imageName){
        String response = azureBlobService.deleteFile(imageName);
        return new JsonResponse("200 OK",response);
    }

    @PostMapping(value = "/images", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    JsonResponse addImage(@RequestPart("file") List<MultipartFile> file, @RequestPart("name")String name) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.readValue(name, Map.class);
        String name1 =(String) map.get("imageName");
        MultipartFile image = file.get(0);
        String response = azureBlobService.storeFile(name1,image.getInputStream(),image.getSize());
        return new JsonResponse("200 OK",response);
    }

    @PostMapping(value = "/register")
    public JsonResponse register(@Valid @RequestBody User user) {

        if (userService.checkifUserExists(user.getUserName()) > 0)
            return new JsonResponse("409 Conflict", "User already exists");
        if (userService.checkIfUserPhoneExists(user.getPhone()) > 0)
            return new JsonResponse("409 Conflict", "Phone number is taken");
        else {
            userService.register(user,"SELLER");
            return new JsonResponse("200 Ok", "Registered successfully");
        }
    }
}
