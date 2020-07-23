package com.example.sagar.SpringSecurityWithJWT.repository;

import com.example.sagar.SpringSecurityWithJWT.model.ColorAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<ColorAttribute,Integer> {


    @Query(value="SELECT * from color_attribute where product_id=?1 GROUP BY color ",nativeQuery=true)
    List<ColorAttribute> findAllByProduct_id(Integer product_id);

    @Query(value="SELECT count(color) from color_attribute where product_id=?1 and color=?2",nativeQuery=true)
    int countColor(Integer productId, String color);

    @Transactional
    @Modifying
    @Query(value="Delete from color_attribute where id in (SELECT id from color_attribute WHERE product_id=?1)",nativeQuery=true)
    void clearColors(Integer productId);




}
