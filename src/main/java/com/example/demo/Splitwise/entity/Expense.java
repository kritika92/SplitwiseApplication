package com.example.demo.Splitwise.entity;

import com.example.demo.Splitwise.ExpenseType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.minidev.json.JSONObject;
import org.hibernate.annotations.Type;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;


@Entity
@EnableAutoConfiguration
public class Expense {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String paidByUserId;
    private double amount;
    private  String paymentMap;
    private String nameExpense;
    private String Date;
    private String imgUrl;
    private String expenseType;

    public Expense(String paidByUserId, double amount, Map<String,Double> paymentMap, String nameExpense,
                   String date, String imgUrl, ExpenseType expenseType) {
        this.paidByUserId = paidByUserId;
        this.amount = amount;
        setPaymentMap(paymentMap);
        this.nameExpense = nameExpense;
        Date = date;
        this.imgUrl = imgUrl;
        this.expenseType = expenseType.toString();

    }
    public Expense(){}

    public ExpenseType getExpenseType() {
        return ExpenseType.valueOf(this.expenseType);
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType.toString();
    }
    //    public Map<Integer, Double> getMetadataAsMap() {
//        Type type = new TypeToken<Map<String, String>>() {}.getType();
//        Map<Integer, Double> newMap =new HashMap<>();
//        try {
//            newMap = new Gson().fromJson(metadata, type);
//        }
//        catch (Exception e){
//            return new HashMap<>();
//        }
//        return newMap;
//    }

    public Map<String, Double> getPaymentMap() throws JsonProcessingException {
        Map<String, Double> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(paymentMap);
        map = mapper.readValue(this.paymentMap, Map.class);
        System.out.println(map);


        return map;
    }

    public void setPaymentMap(Map<String, Double> paymentMap) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            this.paymentMap = mapper.writeValueAsString(paymentMap);
        } catch (JsonProcessingException e) {
            this.paymentMap = "";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaidByUserId() {
        return paidByUserId;
    }

    public void setPaidByUserId(String paidByUserId) {
        this.paidByUserId = paidByUserId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public String getNameExpense() {
        return nameExpense;
    }

    public void setNameExpense(String nameExpense) {
        this.nameExpense = nameExpense;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", paidByUserId='" + paidByUserId + '\'' +
                ", amount=" + amount +
                ", paymentMap='" + paymentMap + '\'' +
                ", nameExpense='" + nameExpense + '\'' +
                ", Date='" + Date + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", expenseType=" + expenseType +
                '}';
    }
}
