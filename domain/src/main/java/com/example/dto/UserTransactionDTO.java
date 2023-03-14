package com.example.dto;

import java.io.Serializable;
import java.util.Date;

public class UserTransactionDTO implements Serializable {
    private String userId;
    private String txnCurr;
    private Double txnAmount;

    private String cardNumber;
    private Date txnDate;
    private String cardHolderName;

    private UserTransactionDTO(UserTransactionDTOBuilder builder) {
        this.userId = builder.userId;
        this.txnAmount = builder.txnAmount;
        this.txnCurr = builder.txnCurr;
        this.cardHolderName = builder.cardHolderName;
        this.cardNumber = builder.cardNumber;
        this.txnDate = builder.txnDate;
    }

    public UserTransactionDTO() {
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

    public static class UserTransactionDTOBuilder {
        private final String userId;
        private String txnCurr;
        private Double txnAmount;

        private String cardNumber;
        private Date txnDate;
        private String cardHolderName;

        public UserTransactionDTOBuilder(String userId) {
            this.userId = userId;
        }

        public UserTransactionDTOBuilder txnCurr(String txnCurr) {
            this.txnCurr = txnCurr;
            return this;
        }

        public UserTransactionDTOBuilder txnAmount(Double txnAmount) {
            this.txnAmount = txnAmount;
            return this;
        }

        public UserTransactionDTOBuilder cardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public UserTransactionDTOBuilder txnDate(Date txnDate) {
            this.txnDate = txnDate;
            return this;
        }

        public UserTransactionDTOBuilder cardHolderName(String cardHolderName) {
            this.cardHolderName = cardHolderName;
            return this;
        }

        public UserTransactionDTO build() {
            return new UserTransactionDTO(this);
        }
    }
}
