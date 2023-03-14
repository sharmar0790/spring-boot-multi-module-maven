package com.example.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;

@Entity(name = "user_txn")
public class UserTransaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String txnCurr;
    private Double txnAmount;

    @Size(min = 16, max = 16, message = "Card Number length must be equal to 16")
    private String cardNumber;
    private Date txnDate;
    private String cardHolderName;

    public UserTransaction() {
    }

    public UserTransaction(String userId, String txnCurr, Double txnAmount, String cardNumber, Date txnDate, String cardHolderName) {
        this.userId = userId;
        this.txnCurr = txnCurr;
        this.txnAmount = txnAmount;
        this.cardNumber = cardNumber;
        this.txnDate = txnDate;
        this.cardHolderName = cardHolderName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTxnCurr() {
        return txnCurr;
    }

    public void setTxnCurr(String txnCurr) {
        this.txnCurr = txnCurr;
    }

    public Double getTxnAmount() {
        return txnAmount;
    }

    public void setTxnAmount(Double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(Date txnDate) {
        this.txnDate = txnDate;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
}
