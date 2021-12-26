package com.example.demo.Splitwise.model;

import com.example.demo.Splitwise.ExpenseType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ExpenseModel {
    private String paidByUserId;
    private String amount;
    private Map<String, Double> paymentMapModel;
    private String nameExpense;
    private String Date;
    private String imgUrl;
    private String expenseType;

    @Override
    public String toString() {
        return "ExpenseModel{" +
                "paidByUserId='" + paidByUserId + '\'' +
                ", amount='" + amount + '\'' +
                ", paymentMapModel=" + paymentMapModel +
                ", nameExpense='" + nameExpense + '\'' +
                ", Date='" + Date + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", expenseType='" + expenseType + '\'' +
                '}';
    }
}
