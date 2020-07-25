package com.example.sagar.SpringSecurityWithJWT.repository;

import com.example.sagar.SpringSecurityWithJWT.model.SizeAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<SizeAttribute,Integer> {

    @Query(value="SELECT  * from size_attribute where product_id=?1 ",nativeQuery=true)
    List<SizeAttribute> findAllByProduct_id(Integer product_id);

    @Query(value="SELECT count(size) from size_attribute where product_id=?1 and size =?2",nativeQuery=true)
    int countSize(Integer productId, Float size);


    @Modifying
    @Transactional
    @Query(value = "Delete from size_attribute where id in (SELECT id from size_attribute WHERE product_id=?1)",nativeQuery=true )
    void clearSizes(Integer productId);
}
