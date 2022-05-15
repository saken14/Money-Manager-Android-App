package com.example.myapp.model;

public class UserAcc {
    static double cash;

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        UserAcc.cash = cash;
    }

    public void addCash(double cash) {
        UserAcc.cash += cash;
    }

    public UserAcc(double cash) {
        UserAcc.cash = cash;
    }
}
