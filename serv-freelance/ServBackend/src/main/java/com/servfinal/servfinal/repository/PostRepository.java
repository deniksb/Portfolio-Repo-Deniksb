package com.servfinal.servfinal.repository;

import com.servfinal.servfinal.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByAuthorId(long authorId);
}
