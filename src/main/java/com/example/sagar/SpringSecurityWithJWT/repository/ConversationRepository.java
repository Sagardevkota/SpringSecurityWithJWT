package com.example.sagar.SpringSecurityWithJWT.repository;

import com.example.sagar.SpringSecurityWithJWT.model.Conversation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation,Integer> {

    @Query(value="select * FROM conversations where product_id=?1",nativeQuery=true)
    List<Conversation> getConversations(Integer productId);

    @Query(value="select u.user_name from user_info u inner join conversations c on u.user_id=c.user_id where c.user_id=?1", nativeQuery=true)
    List<String> getUsername(Integer user_id);

//    //get single last conversation
//    @Query(value = "SELECT c.productId,p.picture_path,p.productName,c.message,u.userName FROM conversations c INNER JOIN products p ON p.productId=c.productId  INNER JOIN user_info u ON u.id=p.seller_id WHERE c.id= (SELECT MAX(id) from conversations WHERE productId=?1)")
//    MessageResponse getConversationsOfSeller(Integer productId);

    @Query(value = "SELECT MAX(id) from conversations  WHERE product_id=?1",nativeQuery=true)
    Integer getMaxID(String productId);

    @Query(value = "select * from  conversations  where id=?1",nativeQuery = true)
    Conversation getOneConversation(Integer maxid);
}
