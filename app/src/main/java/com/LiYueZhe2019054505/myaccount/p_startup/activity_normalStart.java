package com.LiYueZhe2019054505.myaccount.p_startup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.LiYueZhe2019054505.myaccount.R;
import com.LiYueZhe2019054505.myaccount.activity_index;

public class activity_normalStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_normalstart);

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(activity_normalStart.this, activity_index.class);
        startActivity(intent);
    }
}