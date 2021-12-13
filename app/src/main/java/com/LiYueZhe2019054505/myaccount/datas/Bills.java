package com.LiYueZhe2019054505.myaccount.datas;

public class Bills {
    private String billDirection;
    private String billType;
    private String billMethod;
    private String billNote;
    private double billAmount;
    private String billTime;

    public Bills(String billDirection, String billType, String billMethod, String billNote, double billAmount, String billTime) {
        this.billDirection = billDirection;
        this.billType = billType;
        this.billMethod = billMethod;
        this.billNote = billNote;
        this.billAmount = billAmount;
        this.billTime = billTime;
    }

    public String getBillDirection() {
        return billDirection;
    }

    public void setBillDirection(String billDirection) {
        this.billDirection = billDirection;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillMethod() {
        return billMethod;
    }

    public void setBillMethod(String billMethod) {
        this.billMethod = billMethod;
    }

    public String getBillNote() {
        return billNote;
    }

    public void setBillNote(String billNote) {
        this.billNote = billNote;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public String getBillTime() {
        return billTime;
    }

    public void setBillTime(String billTime) {
        this.billTime = billTime;
    }

}
