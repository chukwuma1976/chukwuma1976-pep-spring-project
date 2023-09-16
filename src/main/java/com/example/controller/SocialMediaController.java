package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
// import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    
    @Autowired
    AccountService accountService;

    @Autowired
    MessageService messageService;

    // public void startAPI (){
    //     //Controller endpoints
        
    //     Javalin app = Javalin.create();

    //     //Register endpoint
    //     app.post("/register", this::registerHandler);

    //     //Login endpoint
    //     app.post("/login", this::loginHandler);

    //     //Get all messages endpoint
    //     app.get("/messages", this::getAllMessagesHandler);

    //     //Get message by id endpoint
    //     app.get("/messages/{message_id}", this::getMessageByIdHandler);

    //     //Create message endpoint
    //     app.post("/messages", this::createMessageHandler);

    //     //Update message by id endpoint
    //     app.patch("/messages/{message_id}", this::updateMessageByIdHandler);

    //     //Delete message by id endpoint
    //     app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);  

    //     //Get messages by user id endpoint
    //     app.get("/accounts/{account_id}/messages", this::getMessagesByAccountIdHandler)
        
    //     // app.start(8080)
    // }

    //Controller handlers below

    @PostMapping("/register")
    private Account registerHandler(@RequestBody Account user){
        Account addedUser = accountService.addUser(user);
        if(addedUser!=null){
            return addedUser;
        }else{
            return null;
        }
    }
    //Login handler
    @PostMapping("/login")
    private Account loginHandler(@RequestBody Account user){
        Account loggedUser = accountService.loginUser(user);
        if(loggedUser!=null) {
            return loggedUser;
        } else {
            return null;
        }
    }
    //Get all messages handler
    @GetMapping("/messages")
    private List <Message> getAllMessagesHandler(){
        List <Message> messages = messageService.getAllMessages();
        return messages;
    }
    //Get message by id handler
    @GetMapping("/messages/{message_id}")
    private Message getMessageByIdHandler(@PathVariable("message_id") int message_id){
        Message message = messageService.getMessageById(message_id);
        return message;
    }
    //Create message handler
    @PostMapping("/messages")
    private Message createMessageHandler(@RequestBody Message message){
        Message addedMessage = messageService.addMessage(message);
        if (addedMessage !=null) {
            return addedMessage;
        } else {
            return null;
        }
    }
    //Update message by id handler
    @PatchMapping("/messages/{message_id}")
    private int updateMessageByIdHandler(@PathVariable("message_id") int message_id, @RequestBody Message message){
        int updatedMessage = messageService.updateMessageById(message_id, message);
        if (updatedMessage > 0) {
            return 1;
        } else {
            return 0;
        }

    }
    //Delete message by id handler
    @DeleteMapping("/messages/{message_id}")
    private int deleteMessageByIdHandler(@PathVariable("message_id") int message_id){
        int deleted = messageService.deleteMessageById(message_id);
        if (deleted != 0 ) {
            return deleted;
        } else {
            return 0;
            }        
    }
    //Get messages by account id handler
    @GetMapping("/accounts/{account_id}/messages")
    private List<Message> getMessagesByAccountIdHandler(@PathVariable("account_id") int account_id){
        List<Message> messages = messageService.getMessagesByAccountId(account_id);
        return messages;
    }
}
