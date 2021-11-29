package com.LiYueZhe2019054505.myaccount.p_startup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.LiYueZhe2019054505.myaccount.R;
import com.LiYueZhe2019054505.myaccount.activity_index;

public class activity_firstStart extends AppCompatActivity {

    private Button firstStart_bt_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_firststart);

        firstStart_bt_ok = findViewById(R.id.firstStart_bt_ok);

        firstStart_bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_firstStart.this, activity_index.class);
                startActivity(intent);
            }
        });
    }
}