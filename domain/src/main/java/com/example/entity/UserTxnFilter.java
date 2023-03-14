package com.example.entity;

import java.util.Date;

public class UserTxnFilter {
    private String txnCurr;
    private Double txnAmount;
    private CriteriaEnum txnAmountCriteria;
    private Date txnDate;
    private CriteriaEnum txnDateCriteria;

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

    public CriteriaEnum getTxnAmountCriteria() {
        return txnAmountCriteria;
    }

    public void setTxnAmountCriteria(CriteriaEnum txnAmountCriteria) {
        this.txnAmountCriteria = txnAmountCriteria;
    }

    public Date getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(Date txnDate) {
        this.txnDate = txnDate;
    }

    public CriteriaEnum getTxnDateCriteria() {
        return txnDateCriteria;
    }

    public void setTxnDateCriteria(CriteriaEnum txnDateCriteria) {
        this.txnDateCriteria = txnDateCriteria;
    }

    @Override
    public String toString() {
        return "UserTxnFilter{" +
                "txnCurr='" + txnCurr + '\'' +
                ", txnAmountCriteria=" + txnAmountCriteria +
                ", txnDate=" + txnDate +
                ", txnDateCriteria=" + txnDateCriteria +
                '}';
    }
}
