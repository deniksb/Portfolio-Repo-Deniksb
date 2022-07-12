package com.servfinal.servfinal.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long workerId;

    private long customerId;

    private long postId;

    private LocalDate endDate;

    private LocalDate startDate;

    private boolean workFinished;

    private boolean paymentFinished;


    public Order(long workerId, long customerId, long postId, LocalDate endDate, LocalDate startDate) {
        this.workerId = workerId;
        this.customerId = customerId;
        this.postId = postId;
        this.endDate = endDate;
        this.startDate = startDate;
        this.workFinished = false;
        this.paymentFinished = false;
    }

    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isWorkFinished() {
        return workFinished;
    }

    public void setWorkFinished(boolean workFinished) {
        this.workFinished = workFinished;
    }

    public boolean isPaymentFinished() {
        return paymentFinished;
    }

    public void setPaymentFinished(boolean paymentFinished) {
        this.paymentFinished = paymentFinished;
    }
}
