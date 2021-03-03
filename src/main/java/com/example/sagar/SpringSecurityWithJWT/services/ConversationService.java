package com.example.sagar.SpringSecurityWithJWT.services;

import com.example.sagar.SpringSecurityWithJWT.model.Conversation;
import com.example.sagar.SpringSecurityWithJWT.model.ConversationDto;
import com.example.sagar.SpringSecurityWithJWT.model.MessageDto;
import com.example.sagar.SpringSecurityWithJWT.model.Products;
import com.example.sagar.SpringSecurityWithJWT.repository.ConversationRepository;
import com.example.sagar.SpringSecurityWithJWT.repository.ProductRepository;
import com.example.sagar.SpringSecurityWithJWT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    ConversationService(ConversationRepository conversationRepository, ProductRepository productRepository, UserRepository userRepository){
        this.conversationRepository = conversationRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<ConversationDto> getConversations(Integer productId){
        List<ConversationDto> conv=new ArrayList<>();
        conversationRepository.getConversations(productId).forEach(conversation -> {
            List<String> userName=getUserName(conversation.getAsker());
            conv.add(new ConversationDto(conversation.getMessage(),
                    userName.get(0),
                    conversation.getDate()));
        });

        return  conv;
    }

    public List<String> getUserName(Integer user_id){
        return conversationRepository.getUsername(user_id);

    }

    public void addConversation(Conversation conversation) {
        conversationRepository.save(conversation);
    }

    public List<MessageDto> getConversationList(Integer sellerId) {

        List<MessageDto> messageRespons =new ArrayList<>();
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
                String picture_path=products.getPicturePath();
                String product_name=products.getProductName();

                //get username
                String userName= userRepository.getUserName(products.getSeller_id());

                MessageDto messageDto = new MessageDto(products.getProductId(),
                        picture_path,
                        product_name,
                        conversation.getMessage(),
                        userName,
                        conversation.getDate()
                );

                messageRespons.add(new MessageDto(messageDto));

            }


        }
        return messageRespons;

    }
}
