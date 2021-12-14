package com.LiYueZhe2019054505.myaccount.datas;

import java.io.Serializable;

public class Accounts {
    public double networth;
    public double income;
    public double expense;
    public double cash;
    public double wechat;
    public double alipay;

    public Accounts(double networth, double income, double expense, double cash, double wechat, double alipay) {
        this.networth = networth;
        this.income = income;
        this.expense = expense;
        this.cash = cash;
        this.wechat = wechat;
        this.alipay = alipay;
    }

    public double getNetworth() {
        return networth;
    }

    public void setNetworth(double networth) {
        this.networth = networth;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getWechat() {
        return wechat;
    }

    public void setWechat(double wechat) {
        this.wechat = wechat;
    }

    public double getAlipay() {
        return alipay;
    }

    public void setAlipay(double alipay) {
        this.alipay = alipay;
    }
}
