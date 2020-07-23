package com.example.sagar.SpringSecurityWithJWT.repository;

import com.example.sagar.SpringSecurityWithJWT.model.ReviewResponse;
import com.example.sagar.SpringSecurityWithJWT.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Reviews,Integer> {

    @Query(value="SELECT * from reviews where product_id=?1",nativeQuery=true)
    List<Reviews> getReviews(Integer productId);

    @Transactional
    @Modifying
    @Query(value="UPDATE reviews r SET r.user_id=?1,r.product_id=?2,r.message=?3,r.rating=?4,r.date=?5 where r.product_id=?2 AND r.user_id=?1",nativeQuery=true)
    void updateReview(Integer user_id,Integer product_id,String message,String rating,String date);

    @Query(value="SELECT * from reviews where product_id=?1 and user_id=?2",nativeQuery=true)
    Reviews getOneReview(Integer productId, Integer userId);

}
