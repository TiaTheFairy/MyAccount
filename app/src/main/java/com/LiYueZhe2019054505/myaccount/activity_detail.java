package com.LiYueZhe2019054505.myaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class activity_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail);

        ImageView detail_iv_return = findViewById(R.id.detail_iv_return);
        TextView detail_tv_delete = findViewById(R.id.detail_tv_delete);
        TextView detail_tv_edit = findViewById(R.id.detail_tv_edit);
        TextView detail_tv_amount = findViewById(R.id.detail_tv_amount);
        TextView detail_tv_type = findViewById(R.id.detail_tv_type);
        TextView detail_tv_direction = findViewById(R.id.detail_tv_direction);
        TextView detail_tv_account = findViewById(R.id.detail_tv_account);
        TextView detail_tv_time = findViewById(R.id.detail_tv_time);
        TextView detail_tv_note = findViewById(R.id.detail_tv_note);

        //=====================================================显示详细信息=====================================================
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        detail_tv_amount.setText(intent.getDoubleExtra("amount", 0) + "");
        detail_tv_type.setText(intent.getStringExtra("type"));
        detail_tv_direction.setText(intent.getStringExtra("direction"));
        detail_tv_account.setText(intent.getStringExtra("account"));
        detail_tv_time.setText(intent.getStringExtra("time"));
        if("".equals(intent.getStringExtra("note"))){
            detail_tv_note.setText(R.string.detail_tv_empty);
        }
        else{
            detail_tv_note.setText(intent.getStringExtra("note"));
        }

        //=====================================================顶部菜单=====================================================
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

        detail_tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewIntent = new Intent();
                viewIntent.putExtra("position", position);
                viewIntent.putExtra("action", 1);
                setResult(activity_index.RESULT_CODE_TOUCH, viewIntent);
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
    }
}