package com.servfinal.servfinal.repository;

import com.servfinal.servfinal.model.Order;
import com.servfinal.servfinal.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    public List<Order> findByWorkerId(long workerId);
    public List<Order> findByCustomerId(long customerId);
}
