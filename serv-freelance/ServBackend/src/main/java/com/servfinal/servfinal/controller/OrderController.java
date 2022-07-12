package com.servfinal.servfinal.controller;

import com.servfinal.servfinal.model.Order;
import com.servfinal.servfinal.model.Post;
import com.servfinal.servfinal.model.Request;
import com.servfinal.servfinal.repository.OrderRepository;
import com.servfinal.servfinal.repository.PostRepository;
import com.servfinal.servfinal.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RequestRepository requestRepository;


    @PostMapping("/orders/add")
    @Transactional
    public Order addOrder(@RequestBody Request request){
        Post post = postRepository.findById(request.getPostId()).get();
        LocalDate currentDate = LocalDate.now();
        LocalDate endDate = currentDate.plusDays(post.getDuration());
        Order newOrder = new Order(request.getReceiverId(),request.getSenderId(),request.getPostId(),endDate, currentDate);

        requestRepository.deleteById(request.getId());


        return orderRepository.save(newOrder);
    }

    @GetMapping("/orders")
    public List<Order> getOrders(){

        return orderRepository.findAll();
    }

    @GetMapping("/orders/getByWorker/{workerId}")
    public List<Order> getRequestsBySenderId(@PathVariable("workerId") String workerId){
        return orderRepository.findByWorkerId(Long.parseLong(workerId));
    }

    @GetMapping("/orders/getByCustomer/{customerId}")
    public List<Order> getRequestsByReceiverId(@PathVariable("customerId") String customerId){
        return orderRepository.findByCustomerId(Long.parseLong(customerId));
    }

    @PostMapping("/orders/updateWorkFinished/{id}")
    public Order updateOrderWorkFinished(@PathVariable("id") long id){
        Order order = orderRepository.findById(id).get();
        order.setWorkFinished(true);
        orderRepository.deleteById(id);
        return orderRepository.save(order);
    }

    @PostMapping("/orders/updatePaymentFinished/{id}")
    public Order updateOrderPaymentFinished(@PathVariable("id") long id){
        Order order = orderRepository.findById(id).get();
        order.setPaymentFinished(true);
        orderRepository.deleteById(id);
        return orderRepository.save(order);
    }


}
