package com.LiYueZhe2019054505.myaccount.datas;

import android.content.Context;

import java.util.List;

public class DataBank {
    public static final String DATA_ACCOUNTS = "dataaccounts";
    public static final String DATA_BILLS = "databills";
    private final Context context;
    List<Bills> billsList;

    public DataBank(Context context){
        this.context = context;
    }
}
