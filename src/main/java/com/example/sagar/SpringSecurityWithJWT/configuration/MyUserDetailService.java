package com.example.sagar.SpringSecurityWithJWT.configuration;

import com.example.sagar.SpringSecurityWithJWT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;




@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository repository;
    private int id;

    @Autowired
    MyUserDetailService(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        com.example.sagar.SpringSecurityWithJWT.model.User user = repository.findByUserName(userName);

        if (user==null)
            throw new UsernameNotFoundException("no username in database");

        this.id = user.getId();

        return new UserPrincipal(user);
    }


    public int getId() {
        return id;
    }
}
