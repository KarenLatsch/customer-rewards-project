package com.example.demo.domain;

import java.time.LocalDate;

public class Transaction {

    private Long customerId;
    private LocalDate transDate;
    private Double transAmount;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDate getTransDate() {
        return transDate;
    }

    public void setTransDate(LocalDate transDate) {
        this.transDate = transDate;
    }

    public Double getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Double transAmount) {
        this.transAmount = transAmount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "customerId=" + customerId +
                ", transDate=" + transDate +
                ", transAmount=" + transAmount +
                '}';
    }
}


