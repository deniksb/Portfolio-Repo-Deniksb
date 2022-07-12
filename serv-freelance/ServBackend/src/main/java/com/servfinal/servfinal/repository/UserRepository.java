package com.servfinal.servfinal.repository;

import com.servfinal.servfinal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByPassword(String password);
    @Transactional
    User findUserByEmailAndPassword(String email,String password);
}
