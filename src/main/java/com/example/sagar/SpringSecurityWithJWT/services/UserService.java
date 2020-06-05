package com.example.sagar.SpringSecurityWithJWT.services;

import com.example.sagar.SpringSecurityWithJWT.model.User;
import com.example.sagar.SpringSecurityWithJWT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;


    public Boolean isVerified(String userName){
        return   repository.isVerified(userName);
    }

    public String register(User user){

       repository.save(new User(user.getUserName(),new BCryptPasswordEncoder().encode(user.getPassword()),user.getDeliveryAddress(),user.getPhone(),user.getVerifed()));

       return "ok";

    }

    public int getUserId(String userName){
        return repository.getUserId(userName);
    }

    public int checkifUserExists(String userName){
        return repository.checkIfUserExist(userName).size();
    }
    public int checkIfUserPhoneExists(String phone){
        return repository.checkIfUserPhoneExist(phone).size();
    }
}
