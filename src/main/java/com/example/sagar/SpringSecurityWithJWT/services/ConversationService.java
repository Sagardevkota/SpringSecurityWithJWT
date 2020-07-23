package com.example.sagar.SpringSecurityWithJWT.services;

import com.example.sagar.SpringSecurityWithJWT.model.*;
import com.example.sagar.SpringSecurityWithJWT.repository.ConversationRepository;
import com.example.sagar.SpringSecurityWithJWT.repository.ProductRepository;
import com.example.sagar.SpringSecurityWithJWT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ConversationResponse> getConversations(Integer productId){
        List<ConversationResponse> conv=new ArrayList<>();

        for (Conversation c:conversationRepository.getConversations(productId))
        {
            List<String> userName=getUserName(c.getAsker());
            conv.add(new ConversationResponse(c.getMessage(),userName.get(0),c.getDate()));
        }
        return  conv;
    }

    public List<String> getUserName(Integer user_id){
        return conversationRepository.getUsername(user_id);

    }

    public void addConversation(Conversation conversation) {
        conversationRepository.save(conversation);
    }

    public List<MessageResponse> getConversationList(Integer sellerId) {

        List<MessageResponse> messageResponses=new ArrayList<>();
        List<Integer> productIds=productRepository.getProductIds(sellerId);
        for (Integer id:productIds)
        {
            //get last row of conversation of the productid
            Integer maxid=conversationRepository.getMaxID(String.valueOf(id));
            //get conversation of the id
            if (maxid!=null)
            {
                Conversation conversation=conversationRepository.getOneConversation(maxid);

                //get picture path and product name
                Products products=productRepository.getOneProduct(Integer.valueOf(conversation.getProductId()));
                String picture_path=products.getPicture_path();
                String product_name=products.getProductName();

                //get username
                String userName= userRepository.getUserName(products.getSeller_id());

                MessageResponse messageResponse= new MessageResponse(products.getProductId(),
                        picture_path,
                        product_name,
                        conversation.getMessage(),
                        userName,
                        conversation.getDate()
                );

                messageResponses.add(new MessageResponse(messageResponse));

            }


        }
        return messageResponses;

    }
}
