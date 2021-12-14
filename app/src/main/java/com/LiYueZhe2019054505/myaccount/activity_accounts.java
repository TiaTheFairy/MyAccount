package com.LiYueZhe2019054505.myaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class activity_accounts extends AppCompatActivity {

    private ImageView accounts_iv_return;
    private TextView accounts_tv_networth;
    private TextView accounts_tv_income;
    private TextView accounts_tv_expense;
    private TextView accounts_tv_cash;
    private TextView accounts_tv_wechat;
    private TextView accounts_tv_alipay;
    private DecimalFormat df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_accounts);
        df = new DecimalFormat( "#####0.00");

        accounts_iv_return = findViewById(R.id.accounts_iv_return);
        accounts_tv_networth = findViewById(R.id.accounts_tv_networth);
        accounts_tv_income = findViewById(R.id.accounts_tv_income);
        accounts_tv_expense = findViewById(R.id.accounts_tv_expense);
        accounts_tv_cash = findViewById(R.id.accounts_tv_cash);
        accounts_tv_wechat = findViewById(R.id.accounts_tv_wechat);
        accounts_tv_alipay = findViewById(R.id.accounts_tv_alipay);

        Intent intent = getIntent();
        double networth = intent.getDoubleExtra("networth", 0);
        double income =  intent.getDoubleExtra("income", 0);
        double expense = intent.getDoubleExtra("expense", 0);
        double cash = intent.getDoubleExtra("cash", 0);
        double wechat = intent.getDoubleExtra("wechat", 0);
        double alipay = intent.getDoubleExtra("alipay", 0);

        accounts_tv_networth.setText(df.format(networth));
        accounts_tv_income.setText(df.format(income));
        accounts_tv_expense.setText(df.format(expense));
        accounts_tv_cash.setText(df.format(cash));
        accounts_tv_wechat.setText(df.format(wechat));
        accounts_tv_alipay.setText(df.format(alipay));


        accounts_iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity_accounts.this.finish();
            }
        });
    }
}