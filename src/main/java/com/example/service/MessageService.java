package com.example.service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    AccountRepository accountRepository;

    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
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
        Optional<Account> user = accountRepository.findById(message.getPosted_by());
        if (message.getMessage_text() != "" && user.isPresent()){
            return messageRepository.save(message);
        } else return null;
    }

    public int updateMessageById(int message_id, Message message){
        Optional<Message> optionalMessage = messageRepository.findById(message_id);
        if (optionalMessage.isPresent() && message.getMessage_text() != "" && message.getMessage_text().length() < 255) {
            Message updatedMessage = optionalMessage.get();
            updatedMessage.setMessage_text(message.getMessage_text());
            messageRepository.save(updatedMessage);
            return 1;
            }
            else return 0;
    }

    public int deleteMessageById(int message_id){
        Optional<Message> message = messageRepository.findById(message_id);
        if (message.isPresent()){
            messageRepository.deleteById(message_id);
            return 1;
        }else return 0;
    }

    public List<Message> getMessagesByAccountId(int account_id){
        return messageRepository.getMessagesByAccountId(account_id);
    }
}
