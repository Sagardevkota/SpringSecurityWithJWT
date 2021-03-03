package com.example.sagar.SpringSecurityWithJWT.repository;

import com.example.sagar.SpringSecurityWithJWT.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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

    User findAllByUserName(String userName);



    @Modifying
    @Transactional
    @Query(value="UPDATE user_info SET user_name=?2 where user_id=?1",nativeQuery=true)
    void updateUserName(Integer userId, String newUserName);

    @Modifying
    @Transactional
    @Query(value="UPDATE user_info SET phone=?2 where user_id=?1",nativeQuery=true)
    void updatePhone(Integer userId, String newPhone);

    @Modifying
    @Transactional
    @Query(value="UPDATE user_info SET delivery_address=?2 where user_id=?1",nativeQuery=true)
    void updateDelivery(Integer userId, String newDelivery);

    @Query(value="SELECT role from user_info where user_name=?1",nativeQuery=true)
    String getRole(String userName);

    @Query(value="SELECT user_name from user_info where user_id=?1",nativeQuery=true)
    String getSellerName(Integer seller_id);

    @Query(value="SELECT DISTINCT user_id from user_info WHERE latitude BETWEEN ?1 AND ?2 AND longitude BETWEEN ?3 AND ?4 AND user_id!=?5 ",nativeQuery=true)
    List<Integer> findNearByUser( Double max_lat,Double min_lat,Double max_lon,Double min_lon,Integer user_id);

    @Query(value="SELECT * from user_info where user_id=?1",nativeQuery=true)
    User findAllByUserId(Integer userId);

    @Query(value="select user_name from user_info where user_id=?1",nativeQuery=true)
    String getUserName(Integer userId);


    @Query(value="select phone from user_info where user_id=?1",nativeQuery=true)
    String getPhone(Integer userId);
}
