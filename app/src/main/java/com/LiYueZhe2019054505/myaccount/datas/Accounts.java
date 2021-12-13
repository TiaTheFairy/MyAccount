package com.LiYueZhe2019054505.myaccount.datas;

import android.app.Application;

public class Accounts extends Application {
    public double networth;
    public double income;
    public double expense;
    public double cash;
    public double wechat;
    public double alipay;

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

    public void onCreate(){
        super.onCreate();
        setNetworth(0);
        setIncome(0);
        setExpense(0);
        setCash(0);
        setWechat(0);
        setAlipay(0);
    }
}
