package com.LiYueZhe2019054505.myaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class activity_accounts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_accounts);
        DecimalFormat df = new DecimalFormat( "#####0.00");

        ImageView accounts_iv_return = findViewById(R.id.accounts_iv_return);
        TextView accounts_tv_networth = findViewById(R.id.accounts_tv_networth);
        TextView accounts_tv_income = findViewById(R.id.accounts_tv_income);
        TextView accounts_tv_expense = findViewById(R.id.accounts_tv_expense);
        TextView accounts_tv_cash = findViewById(R.id.accounts_tv_cash);
        TextView accounts_tv_wechat = findViewById(R.id.accounts_tv_wechat);
        TextView accounts_tv_alipay = findViewById(R.id.accounts_tv_alipay);

        Intent intent = getIntent();
        accounts_tv_networth.setText(df.format(intent.getDoubleExtra("networth", 0)));
        accounts_tv_income.setText(df.format(intent.getDoubleExtra("income", 0)));
        accounts_tv_expense.setText(df.format(intent.getDoubleExtra("expense", 0)));
        accounts_tv_cash.setText(df.format(intent.getDoubleExtra("cash", 0)));
        accounts_tv_wechat.setText(df.format(intent.getDoubleExtra("wechat", 0)));
        accounts_tv_alipay.setText(df.format(intent.getDoubleExtra("alipay", 0)));

        accounts_iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity_accounts.this.finish();
            }
        });
    }
}