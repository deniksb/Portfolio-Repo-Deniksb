package com.servfinal.servfinal.repository;

import com.servfinal.servfinal.model.Post;
import com.servfinal.servfinal.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {

    public List<Request> findBySenderId(long senderId);
    public List<Request> findByReceiverId(long receiverId);

}
