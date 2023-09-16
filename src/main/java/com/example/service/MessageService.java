package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    
    @Autowired
    MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageById(int message_id){
        Optional<Message> message = messageRepository.findById(message_id);
        if (message.isPresent())
            return message.get();
            else return null;
    }

    public Message addMessage(Message message){
        if (message.getMessage_text() != ""){
            return messageRepository.save(message);
        } else return null;
    }

    public int updateMessageById(int message_id, Message message){
        Optional<Message> updatedMessage = messageRepository.findById(message_id);
        if (updatedMessage.isPresent() && message.getMessage_text() != "") 
            return 1;
            else return 0;
    }

    public int deleteMessageById(int message_id){
        Optional<Message> message = messageRepository.findById(message_id);
        if (message.isPresent()){
            return 1;
        }else return 0;
    }

    public List<Message> getMessagesByAccountId(int account_id){
        return messageRepository.getMessagesByAccountId(account_id);
    }
}
