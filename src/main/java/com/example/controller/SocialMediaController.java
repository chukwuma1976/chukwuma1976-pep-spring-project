package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping
public class SocialMediaController {
    
    @Autowired
    AccountService accountService;

    @Autowired
    MessageService messageService;

    //Controller endpoint and handlers below

    //Register endpoint and handler
    @PostMapping("/register")
    public ResponseEntity<Account> registerHandler(@RequestBody Account user){
        Account addedUser = accountService.addUser(user);
        if(addedUser!=null){
            return ResponseEntity.status(200).body(addedUser);
        }else{
            return ResponseEntity.status(409).body(null);
        }
    }
    //Login endpoint and handler
    @PostMapping("/login")
    public ResponseEntity<Account> loginHandler(@RequestBody Account user){
        Account loggedUser = accountService.loginUser(user);
        if(loggedUser!=null) {
            return ResponseEntity.status(200).body(loggedUser);
        } else {
            return ResponseEntity.status(401).body(loggedUser);
        }
    }
    //Get all messages endpoint and handler
    @GetMapping("/messages")
    public ResponseEntity<List <Message>> getAllMessagesHandler(){
        List <Message> messages = messageService.getAllMessages();
        return ResponseEntity.status(200).body(messages);
    }
    //Get message by id endpoint and handler
    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessageByIdHandler(@PathVariable("message_id") int message_id){
        Message message = messageService.getMessageById(message_id);
        return ResponseEntity.status(200).body(message);
    }
    //Create message endpoint and handler
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessageHandler(@RequestBody Message message){
        Message addedMessage = messageService.addMessage(message);
        if (addedMessage !=null) {
            return ResponseEntity.status(200).body(addedMessage);
        } else {
            return ResponseEntity.status(400).body(addedMessage);
        }
    }
    //Update message by id endpoint and handler
    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Integer>updateMessageByIdHandler(@PathVariable("message_id") int message_id, @RequestBody Message message){
        int updatedMessage = messageService.updateMessageById(message_id, message);
        if (updatedMessage > 0) {
            return ResponseEntity.status(200).body(updatedMessage);
        } else {
            return ResponseEntity.status(400).body(0);
        }

    }
    //Delete message by id endpoint and handler
    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Integer>deleteMessageByIdHandler(@PathVariable("message_id") int message_id){
        int deleted = messageService.deleteMessageById(message_id);
        if (deleted != 0 ) {
            return ResponseEntity.status(200).body(deleted);
        } else {
            return ResponseEntity.status(200).body(0);
            }        
    }
    //Get messages by account id endpoint and handler
    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getMessagesByAccountIdHandler(@PathVariable("account_id") int account_id){
        List<Message> messages = messageService.getMessagesByAccountId(account_id);
        return ResponseEntity.status(200).body(messages);
    }
}
