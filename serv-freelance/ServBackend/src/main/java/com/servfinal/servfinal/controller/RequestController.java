package com.servfinal.servfinal.controller;

import com.servfinal.servfinal.model.Post;
import com.servfinal.servfinal.model.Request;
import com.servfinal.servfinal.repository.PostRepository;
import com.servfinal.servfinal.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RequestController {

    @Autowired
    PostRepository  postRepository;

    @Autowired
    RequestRepository requestRepository;


    @PostMapping("/requests/add")
    @Transactional
    public Request addRequest(@RequestBody Request request){

        requestRepository.save(request);

        return request;
    }

    @GetMapping("/requests")
    public List<Request> getRequest(){

        return requestRepository.findAll();
    }

    @GetMapping("/requests/getBySender/{senderId}")
    public List<Request> getRequestsBySenderId(@PathVariable("senderId") String senderId){
        return requestRepository.findBySenderId(Long.parseLong(senderId));
    }

    @GetMapping("/requests/getByReceiver/{receiverId}")
    public List<Request> getRequestsByReceiverId(@PathVariable("receiverId") String receiverId){
        return requestRepository.findByReceiverId(Long.parseLong(receiverId));
    }

    @DeleteMapping("/requests/delete/{id}")
    public void deleteRequest(@PathVariable("id") String id){
         requestRepository.deleteById(Long.parseLong(id));
    }


}
