package com.servfinal.servfinal.repository;

import com.servfinal.servfinal.model.Chat;
import com.servfinal.servfinal.model.Message;
import com.servfinal.servfinal.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

    public List<Message> findByChatId(long chatId);
}
