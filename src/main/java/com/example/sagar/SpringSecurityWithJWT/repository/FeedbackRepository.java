package com.example.sagar.SpringSecurityWithJWT.repository;

import com.example.sagar.SpringSecurityWithJWT.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {
}
