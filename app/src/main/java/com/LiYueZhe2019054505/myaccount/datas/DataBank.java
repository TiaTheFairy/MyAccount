package com.LiYueZhe2019054505.myaccount.datas;

import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataBank {
    public static final String DATA_BILLS = "databills";
    private final Context context;
    List<Bills> billsList;

    public DataBank(Context context){
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    public List<Bills> loadBills() {
        billsList = new ArrayList<>();
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(context.openFileInput(DATA_BILLS));
            billsList = (ArrayList<Bills>) objectInputStream.readObject();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return billsList;
    }

    public void saveBills() {
        ObjectOutputStream objectOutputStream = null;
        try{
            objectOutputStream = new ObjectOutputStream(context.openFileOutput(DATA_BILLS, Context.MODE_PRIVATE));
            objectOutputStream.writeObject(billsList);
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
