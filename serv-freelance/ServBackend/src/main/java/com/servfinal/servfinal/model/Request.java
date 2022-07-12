package com.servfinal.servfinal.model;

import javax.persistence.*;

@Entity
@Table(name="request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long senderId;

    private long receiverId;

    private long postId;


    public Request(long senderId, long receiverId, long postId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.postId = postId;
    }

    public Request(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }
}
