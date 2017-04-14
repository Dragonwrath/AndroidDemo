package com.example.tutorials.step_1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by JunWei on 2017/2/14.
 */

public class course_2 {
    public static void main(String[] args) {
        String dollarJson = "{ '1$': { 'amount': 1, 'currency': 'Dollar'}, '2$': { 'amount': 2, 'currency': 'Dollar'}, '3€': { 'amount': 3, 'currency': 'Euro'} }";
        dollarJson = String.format(dollarJson,"utf-8");
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, AmountWithCurrency>>() {
        }.getType();
        HashMap<String, AmountWithCurrency> amount = gson.fromJson(dollarJson, type);
        System.out.println();
    }
}
class AmountWithCurrency {
    String currency;
    int amount;
}
