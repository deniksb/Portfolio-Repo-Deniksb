package com.servfinal.servfinal.repository;

import com.servfinal.servfinal.model.Chat;
import com.servfinal.servfinal.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Long> {

    public List<Chat> findByMemberOneId(long memberOneId);

    public List<Chat> findByMemberTwoId(long memberTwoId);

    public Chat findByMemberOneIdAndMemberTwoId(long memberOneId, long memberTwoId);
}
