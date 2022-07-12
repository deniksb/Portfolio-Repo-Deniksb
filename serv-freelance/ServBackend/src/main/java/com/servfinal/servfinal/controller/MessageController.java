package com.servfinal.servfinal.controller;

import com.servfinal.servfinal.model.Message;
import com.servfinal.servfinal.repository.ChatRepository;
import com.servfinal.servfinal.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ChatRepository chatRepository;

    @GetMapping("messages/{id}")
    public List<Message> getMessagesByChatId(@PathVariable("id") long id){

        List<Message> messages = messageRepository.findByChatId(id);

        messages.sort((e1,e2) -> e1.getSentDate().compareTo(e2.getSentDate()));

        return messages;
    }

    @PostMapping("messages/add")
    public Message addMessage(@RequestBody Message message){
        message.setSentDate(LocalDateTime.now());
        return messageRepository.save(message);
    }





}
