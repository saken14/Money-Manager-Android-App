package com.example.myapp.model;

public class UserAcc {
    static double cash;
    static String currency;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        UserAcc.currency = currency;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        UserAcc.cash = cash;
    }

    public void addCash(double cash) {
        UserAcc.cash += cash;
    }

    public UserAcc(double cash, String currency) {
        UserAcc.cash = cash;
        UserAcc.currency = currency;
    }
}
