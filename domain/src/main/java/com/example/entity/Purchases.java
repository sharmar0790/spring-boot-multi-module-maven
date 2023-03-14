package com.example.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity(name = "purchases")
public class Purchases implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardType;
    @Size(min = 16, max = 16, message = "Card Number length must be equal to 16")
    private String cardNumber;
    private Integer totalAmount;
    private String txnCurr;

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTxnCurr() {
        return txnCurr;
    }

    public void setTxnCurr(String txnCurr) {
        this.txnCurr = txnCurr;
    }
}
