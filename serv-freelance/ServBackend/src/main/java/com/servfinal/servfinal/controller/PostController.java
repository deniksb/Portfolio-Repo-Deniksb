package com.servfinal.servfinal.controller;

import com.servfinal.servfinal.model.Post;
import com.servfinal.servfinal.repository.PostRepository;
import com.servfinal.servfinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;



    @GetMapping("/posts")
    public List<Post> getPosts(){

        return postRepository.findAll();
    }


    @GetMapping("/posts/{keyword}")
    public List<Post> getPostsByCategory(@PathVariable("keyword") String keyword){
        List<Post> posts = postRepository.findAll();

        List<Post> filtered = posts.stream()
                .filter(post -> post.getCategory().toLowerCase().equals(keyword.toLowerCase()))
                .collect(Collectors.toList());

        return filtered;
    }

    @PostMapping("/posts/add")
    @Transactional
    public Post addPost(@RequestBody Post post){

        return postRepository.save(post);
    }

    @GetMapping("/post/findById/{id}")
    public Post getPostById(@PathVariable("id") long id){
        return postRepository.findById(id).get();
    }

    @GetMapping("/posts/findByAuthorId/{id}")
    public List<Post> getPostByAuthorId(@PathVariable("id") long id){
        return postRepository.findByAuthorId(id);
    }

    @DeleteMapping("/post/delete/{id}")
    public void deletePostById(@PathVariable("id") long id){
        postRepository.deleteById(id);
    }



}
