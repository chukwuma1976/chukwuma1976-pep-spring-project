package com.example.service;

import com.example.entity.Message;
import java.util.*;

public class MessageService {
    // public MessageDAO messageDAO;

    // public MessageService(){
    //     this.messageDAO =  = new MessageDAO(); 
    // }

    public List<Message> getAllMessages(){
        // messageDAO.getAllMessages();
        return new ArrayList<Message>();
    }

    public Message getMessageById(int message_id){
        // messageDAO.getMessageById(message_id)
        return new Message();
    }

    public Message addMessage(Message message){
        // if (message.getMessage_text() != "" ){
        //     return messageDAO.addMessage();
        // } else return null;
        return new Message();
    }

    public Message updateMessageById(int message_id, Message message){
        // if (messageDAO.getMessageById(message_id) && message.getMessage_text() != "" ){
        //     return messageDAO.updateMessageById(message_id, message);
        // } else return null;
        return new Message();
    }

    public int deleteMessageById(int message_id){
        // if (messageDAO.getMessageById(message_id) != null) messageDAO.deleteMessageById("");
        //     else return 0;
        return 1;
    }

    public List<Message> getMessagesByAccountId(int account_id){
        // messageDAO.getMessagesByAccountId(account_id);
        return new ArrayList<Message>();
    }
}
