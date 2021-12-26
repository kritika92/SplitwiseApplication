package com.example.demo.Splitwise.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Balance {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int balanceId;
    private int expenseId;
    private String payerId;
    private String oweeId;
    private double amount;

    public Balance(){};

    public Balance(int expenseId, String payerId, String oweeId, double amount) {
        this.expenseId = expenseId;
        this.payerId = payerId;
        this.oweeId = oweeId;
        this.amount = amount;
    }
}
