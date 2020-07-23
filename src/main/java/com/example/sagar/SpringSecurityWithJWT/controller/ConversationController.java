package com.example.sagar.SpringSecurityWithJWT.controller;

import com.example.sagar.SpringSecurityWithJWT.model.Conversation;
import com.example.sagar.SpringSecurityWithJWT.model.ConversationResponse;
import com.example.sagar.SpringSecurityWithJWT.model.JsonResponse;
import com.example.sagar.SpringSecurityWithJWT.model.MessageResponse;
import com.example.sagar.SpringSecurityWithJWT.services.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @RequestMapping(value = "/conversation/{productId}" ,method = RequestMethod.GET)
    public List<ConversationResponse> getConversation(@PathVariable Integer productId)
    {
        return conversationService.getConversations(productId);
    }


    @RequestMapping(value = "/conversation" ,method = RequestMethod.POST)
    public JsonResponse addConversation(@RequestBody Conversation conversation)
    {
        conversationService.addConversation(conversation);
        return new JsonResponse("200 OK","Conversation added");
    }

    @RequestMapping(value = "/conversation/seller/id/{sellerId}" ,method = RequestMethod.GET)
    public List<MessageResponse> getConversationList(@PathVariable Integer sellerId)
    {
        return conversationService.getConversationList(sellerId);
    }

}
