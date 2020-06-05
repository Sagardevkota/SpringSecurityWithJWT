package com.example.sagar.SpringSecurityWithJWT.repository;

import com.example.sagar.SpringSecurityWithJWT.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<com.example.sagar.SpringSecurityWithJWT.model.User,Integer>
{
    User findByUserName(String userName);

    @Query(value="select verified from user_info  where user_name=?1", nativeQuery=true)
    Boolean isVerified(String userName);

    @Query(value="SELECT * from user_info where user_name=?1",nativeQuery=true)
    List<User> checkIfUserExist(String userName);

    @Query(value="SELECT * from user_info where phone=?1",nativeQuery=true)
    List<User> checkIfUserPhoneExist(String phone);

    @Query(value="SELECT user_id from user_info where user_name=?1",nativeQuery=true)
    Integer getUserId(String userName);
}
