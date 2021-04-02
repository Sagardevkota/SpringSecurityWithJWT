package com.example.sagar.SpringSecurityWithJWT.controller;

import com.example.sagar.SpringSecurityWithJWT.model.Conversation;
import com.example.sagar.SpringSecurityWithJWT.model.ConversationDto;
import com.example.sagar.SpringSecurityWithJWT.model.JsonResponse;
import com.example.sagar.SpringSecurityWithJWT.services.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ConversationController {


    private final ConversationService conversationService;

    @Autowired
    ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping(value = "/conversations/{productId}")
    public List<ConversationDto> getConversation(@PathVariable Integer productId) {
        return conversationService.getConversations(productId);
    }


    @PostMapping(value = "/conversations")
    public JsonResponse addConversation(@RequestBody Conversation conversation) {
        conversationService.addConversation(conversation);
        return new JsonResponse("200 OK", "Conversation added");
    }



}
