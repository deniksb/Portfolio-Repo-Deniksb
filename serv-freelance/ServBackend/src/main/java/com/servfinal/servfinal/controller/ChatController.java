package com.servfinal.servfinal.controller;

import com.servfinal.servfinal.model.Chat;
import com.servfinal.servfinal.repository.ChatRepository;
import com.servfinal.servfinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChatController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatRepository chatRepository;

    @GetMapping("/chats")
    public List<Chat> getChats(){
        return chatRepository.findAll();
    }

    @GetMapping("/chats/{id}")
    public List<Chat> getChatsById(@PathVariable("id") long id){
        List<Chat> allChatsForUser = new ArrayList<>();

        List<Chat> chats1 = chatRepository.findByMemberOneId(id);
        List<Chat> chats2 = chatRepository.findByMemberTwoId(id);

        allChatsForUser.addAll(chats1);
        allChatsForUser.addAll(chats2);

        return allChatsForUser;
    }

    @PostMapping("/chats/add")
    public Chat addChat(@RequestBody Chat chat){
        Chat foundChat = chatRepository.findByMemberOneIdAndMemberTwoId(chat.getMemberOneId(),chat.getMemberTwoId());
        Chat foundChatTwo = chatRepository.findByMemberOneIdAndMemberTwoId(chat.getMemberTwoId(),chat.getMemberOneId());
        if(foundChat != null || foundChatTwo != null){
            return null;
        }
        else{
            return chatRepository.save(chat);
        }

    }

    @DeleteMapping("chats/delete/{id}")
    public void deleteChat(@PathVariable("id") long id){
        chatRepository.deleteById(id);
    }
}
