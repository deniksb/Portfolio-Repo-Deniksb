package com.servfinal.servfinal.model;

import javax.persistence.*;

@Entity
@Table(name="chats")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long memberOneId;

    private long memberTwoId;

    public Chat(long memberOneId, long memberTwoId) {
        this.memberOneId = memberOneId;
        this.memberTwoId = memberTwoId;
    }

    public Chat() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMemberOneId() {
        return memberOneId;
    }

    public void setMemberOneId(long memberOneId) {
        this.memberOneId = memberOneId;
    }

    public long getMemberTwoId() {
        return memberTwoId;
    }

    public void setMemberTwoId(long memberTwoId) {
        this.memberTwoId = memberTwoId;
    }
}
