package com.LiYueZhe2019054505.myaccount;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class activity_detail extends AppCompatActivity {

    private ImageView detail_iv_return;
    private TextView detail_tv_delete;
    private TextView detail_tv_edit;
    private TextView detail_tv_amount;
    private TextView detail_tv_type;
    private TextView detail_tv_direction;
    private TextView detail_tv_account;
    private TextView detail_tv_time;
    private TextView detail_tv_note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail);

        detail_iv_return = findViewById(R.id.detail_iv_return);
        detail_tv_delete = findViewById(R.id.detail_tv_delete);
        detail_tv_edit = findViewById(R.id.detail_tv_edit);
        detail_tv_amount = findViewById(R.id.detail_tv_amount);
        detail_tv_type = findViewById(R.id.detail_tv_type);
        detail_tv_direction = findViewById(R.id.detail_tv_direction);
        detail_tv_account = findViewById(R.id.detail_tv_account);
        detail_tv_time = findViewById(R.id.detail_tv_time);
        detail_tv_note = findViewById(R.id.detail_tv_note);

        //=====================================================显示详细信息=====================================================
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        String direction = intent.getStringExtra("direction");
        String type = intent.getStringExtra("type");
        String account = intent.getStringExtra("account");
        String note = intent.getStringExtra("note");
        Double amount = intent.getDoubleExtra("amount", 0);
        String time = intent.getStringExtra("time");

        detail_tv_amount.setText(amount+"");
        detail_tv_type.setText(type);
        detail_tv_direction.setText(direction);
        detail_tv_account.setText(account);
        detail_tv_time.setText(time);
        if("".equals(note)){
            detail_tv_note.setText(R.string.detail_tv_empty);
        }
        else{
            detail_tv_note.setText(note);
        }

        //=====================================================顶部菜单=====================================================
        detail_tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewIntet = new Intent();
                viewIntet.putExtra("position", position);
                viewIntet.putExtra("action", 1);
                setResult(activity_index.RESULT_CODE_TOUCH, viewIntet);
                activity_detail.this.finish();
            }
        });

        detail_tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewIntent = new Intent();
                viewIntent.putExtra("position", position);
                viewIntent.putExtra("action", 2);
                setResult(activity_index.RESULT_CODE_TOUCH, viewIntent);
                activity_detail.this.finish();
            }
        });


        detail_iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewIntent = new Intent();
                viewIntent.putExtra("position", position);
                viewIntent.putExtra("action", 0);
                setResult(activity_index.RESULT_CODE_TOUCH, viewIntent);
                activity_detail.this.finish();
            }
        });
    }
}