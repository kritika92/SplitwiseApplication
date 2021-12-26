package com.example.demo.Splitwise.controller;

import com.example.demo.Splitwise.ExpenseType;
import com.example.demo.Splitwise.Repository.BalanceRepository;
import com.example.demo.Splitwise.Repository.ExpenseRepository;
import com.example.demo.Splitwise.entity.Balance;
import com.example.demo.Splitwise.entity.Expense;
import com.example.demo.Splitwise.model.ExpenseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ExpenseController {
    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    BalanceRepository balanceRepository;

    @PostMapping("/add/expense/")
    @ResponseBody
    public String addExpense(@RequestBody ExpenseModel expense) throws JsonProcessingException {
        Expense e = expenseModelToEntity(expense);
        double value = 0;
        ExpenseType expenseType = e.getExpenseType();
        Map<String, Double> paymentMap = e.getPaymentMap();
        System.out.println(paymentMap);
        double amount = e.getAmount();
        switch (expenseType) {
            case EXACT:
                value = 0;
                for (Map.Entry<String, Double> entry : paymentMap.entrySet()) {
                    value += entry.getValue();
                }
                if (value != amount) {
                    return "Invalid Split among users";
                }
                break;
            case PERCENT:
                value = 100;
                for (Map.Entry<String, Double> entry : paymentMap.entrySet()) {
                    value -= entry.getValue();
                    entry.setValue(amount * entry.getValue() / 100);
                }
                if (value != 0) {
                    return "Invalid Split among users";
                }
                break;
            case EQUAL:
                int size = paymentMap.size();
                for (Map.Entry<String, Double> entry : paymentMap.entrySet()) {
                    entry.setValue(amount / size);
                }
                break;
        }


        expenseRepository.save(e);
        int id = e.getId();
        for (Map.Entry<String, Double> entry : paymentMap.entrySet()) {
            Balance balance = new Balance(id, e.getPaidByUserId(), entry.getKey(), entry.getValue());
            balanceRepository.save(balance);
        }

        return e.toString();

    }

    @GetMapping("/show/balance/")
    @ResponseBody
    public String show(@RequestParam String userId) {
        List<String> retString = new ArrayList<>();
        double amountUserWillGet = 0;
        Map<String, Double> amountUSerWillGetFromTheseUser = new HashMap<>();
        List<Balance> getPaid = balanceRepository.findByPayerId(userId);
        List<Balance> toPay = balanceRepository.findByOweeId(userId);
        for (Balance balance : getPaid) {
            amountUserWillGet += balance.getAmount();
            amountUSerWillGetFromTheseUser.put(balance.getOweeId(), amountUSerWillGetFromTheseUser.getOrDefault(balance.getOweeId(), 0.00) + balance.getAmount());
        }
        for (Balance balance : toPay) {
            amountUserWillGet -= balance.getAmount();
            amountUSerWillGetFromTheseUser.put(balance.getPayerId(), amountUSerWillGetFromTheseUser.getOrDefault(balance.getPayerId(), 0.00) - balance.getAmount());
        }
        if (amountUserWillGet > 0) {
            retString.add("Amount userId " + userId + " will get is " + amountUserWillGet);
        } else if (amountUserWillGet < 0) {
            retString.add("Amount userId " + userId + "has to give others is " + -amountUserWillGet);
        } else {
            retString.add("Everything is settled");
        }
        for (Map.Entry<String, Double> entry : amountUSerWillGetFromTheseUser.entrySet()) {
            if (entry.getValue() < 0) {
                retString.add("userId" + userId + " owes " + -entry.getValue() + " amount to userId " + entry.getKey());
            } else if (entry.getValue() > 0) {
                retString.add("userId " + entry.getKey() + " owes " + entry.getValue() + " amount to userId " + userId);
            }
        }
        return retString.toString();

    }

//    public Map<String, Double> getPaymentMap(String paymentMap){
//        Type type = new TypeToken<Map<String, Double>>() {}.getType();
//        Map<String, Double> map = new HashMap<>();
//        try{
//            map = new Gson().fromJson(paymentMap, type);
//        }
//        catch (Exception e){
//            return new HashMap<>();
//        }
//        return map;
//    }

    private Expense expenseModelToEntity(ExpenseModel expenseModel) {
        String expenseType = expenseModel.getExpenseType();
        String name = expenseModel.getNameExpense();
        String amount = expenseModel.getAmount();
        String inageUrl = expenseModel.getImgUrl();
        String paidByUserID = expenseModel.getPaidByUserId();
        Map<String, Double> paymentMap = expenseModel.getPaymentMapModel();
        String date = expenseModel.getDate();
//        GsonBuilder builder = new GsonBuilder();
//        builder.setPrettyPrinting();
//        Gson gson = builder.create();
//        Map<String, Double> map = gson.fromJson(paymentMap, Map.class);
//        System.out.println(map);

        Expense expense = new Expense(paidByUserID, Double.valueOf(amount), paymentMap,
                name, date, inageUrl, ExpenseType.valueOf(expenseType));
        return expense;
    }


}
