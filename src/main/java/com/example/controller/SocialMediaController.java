package com.example.controller;

import java.util.List;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    public void startAPI (){
        //Controller endpoints
        
        Javalin app = Javalin.create();

        //Register endpoint
        app.post("/register", this::registerHandler);

        //Login endpoint
        app.post("/login", this::loginHandler);

        //Get all messages endpoint
        app.get("/messages", this::getAllMessagesHandler);

        //Get message by id endpoint
        app.get("/messages/{message_id}", this::getMessageByIdHandler);

        //Create message endpoint
        app.post("/messages", this::createMessageHandler);

        //Update message by id endpoint
        app.patch("/messages/{message_id}", this::updateMessageByIdHandler);

        //Delete message by id endpoint
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);  

        //Get messages by user id endpoint
        app.get("/accounts/{account_id}/messages", this::getMessagesByAccountIdHandler)
        
        // app.start(8080)
    }

    //Controller handlers

    //Register handler
    private void registerHandler(Context ctx){
        ObjectMapper mapper = new ObjectMapper();
        Account User = mapper.readValue(ctx.body(), Account.class);
        Account addedUser = accountService.addUser(User);
        if(addedUser!=null){
            ctx.json(addedUser);
        }else{
            ctx.status(400);
        }
    }
    //Login handler
    private void loginHandler(Context ctx){
        ObjectMapper mapper = new ObjectMapper();
        Account User = mapper.readValue(ctx.body(), Account.class);
        Account loggedUser = accountService.loginUser(User);
        if(loggedUser!=null) ctx.json(loggedUser);
            else ctx.status(400);
    }
    //Get all messages handler
    private void getAllMessagesHandler(Context ctx){
        List <Message> messages = messageService.getAllMessages();
        ctx.json(messages);
    }
    //Get message by id handler
    private void getMessageByIdHandler(Context ctx){
        String id = ctx.pathParam("message_id");
        int message_id = Integer.valueOf(id);
        Message message = messageService.getMessageById(message_id);
        ctx.json(message);
    }
    //Create message handler
    private void createMessageHandler(Context ctx){
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message addedMessage = messageService.addMessage(message);
        if (addedMessage !=null) ctx.json(addedMessage);
            else ctx.status(400);
    }
    //Update message by id handler
    private void updateMessageByIdHandler(Context ctx){
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        String id = ctx.pathParam("message_id");
        int message_id = Integer.valueOf(id);
        Message updatedMessage = messageService.updateMessageById(message_id, message);
        if (updatedMessage != null) ctx.json(updatedMessage);
            else ctx.status(400);

    }
    //Delete message by id handler
    private void deleteMessageByIdHandler(Context ctx){
        String id = ctx.pathParam("message_id");
        int message_id = Integer.valueOf(id);
        int deleted = messageService.deleteMessageById(message_id);
        if (deleted != 0 ) ctx.json(deleted);
            else {
                ctx.json("");
                ctx.status(200);
            }        
    }
    //Get messages by account id handler
    private void getMessagesByAccountIdHandler(Context ctx){
        String id = ctx.pathParam("account_id");
        int account_id = Integer.valueOf(id);
        List<Message> messages = messageService.getMessagesByAccountId(account_id);
        ctx.json(messages);
    }
}
