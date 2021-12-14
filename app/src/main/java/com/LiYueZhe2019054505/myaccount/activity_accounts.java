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

public class activity_accounts extends AppCompatActivity {

    private ImageView accounts_iv_return;
    private TextView accounts_tv_save;
    private TextView accounts_tv_undo;
    private TextView accounts_tv_networth;
    private TextView accounts_tv_income;
    private TextView accounts_tv_expense;
    private TextView accounts_tv_cash;
    private TextView accounts_tv_wechat;
    private TextView accounts_tv_alipay;
    private TextView accounts_tv_editing;
    private RelativeLayout accounts_rl_editing;
    private LinearLayout accounts_ll_keyboard;
    private Button accounts_key_save;

    private String editingAccounts;
    private double editingAmount;

    private double networth;
    private double income;
    private double expense;
    private double cash;
    private double wechat;
    private double alipay;


    public void refreshAccounts(){
        accounts_tv_networth.setText(networth+"");
        accounts_tv_income.setText(income+"");
        accounts_tv_expense.setText(expense+"");
        accounts_tv_cash.setText(cash+"");
        accounts_tv_wechat.setText(wechat+"");
        accounts_tv_alipay.setText(alipay+"");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_accounts);

        accounts_iv_return = findViewById(R.id.accounts_iv_return);
        accounts_tv_save = findViewById(R.id.accounts_tv_save);
        accounts_tv_undo = findViewById(R.id.accounts_tv_undo);
        accounts_tv_networth = findViewById(R.id.accounts_tv_networth);
        accounts_tv_income = findViewById(R.id.accounts_tv_income);
        accounts_tv_expense = findViewById(R.id.accounts_tv_expense);
        accounts_tv_cash = findViewById(R.id.accounts_tv_cash);
        accounts_tv_wechat = findViewById(R.id.accounts_tv_wechat);
        accounts_tv_alipay = findViewById(R.id.accounts_tv_alipay);
        accounts_tv_editing = findViewById(R.id.accounts_tv_editing);
        accounts_rl_editing = findViewById(R.id.accounts_rl_editing);
        accounts_ll_keyboard = findViewById(R.id.accounts_ll_keyboard);
        accounts_key_save = findViewById(R.id.accounts_key_save);

        Intent intent = getIntent();
        networth = intent.getDoubleExtra("networth", 0);
        income = intent.getDoubleExtra("income", 0);
        expense = intent.getDoubleExtra("expense", 0);
        cash = intent.getDoubleExtra("cash", 0);
        wechat = intent.getDoubleExtra("wechat", 0);
        alipay = intent.getDoubleExtra("alipay", 0);

        double oNetworth = networth;
        double oIncome = income;
        double oExpense = expense;
        double oCash = cash;
        double oWechat = wechat;
        double oAlipay = alipay;

        refreshAccounts();

        accounts_iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accountsIntent = new Intent();
                accountsIntent.putExtra("networth", networth);
                accountsIntent.putExtra("income", income);
                accountsIntent.putExtra("expense", expense);
                accountsIntent.putExtra("cash", cash);
                accountsIntent.putExtra("wechat", wechat);
                accountsIntent.putExtra("alipay", alipay);
                accountsIntent.putExtra("action", 0);
                setResult(activity_index.RESULT_CODE_TOUCH, accountsIntent);
                activity_accounts.this.finish();
            }
        });

        accounts_tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accountsIntent = new Intent();
                accountsIntent.putExtra("networth", networth);
                accountsIntent.putExtra("income", income);
                accountsIntent.putExtra("expense", expense);
                accountsIntent.putExtra("cash", cash);
                accountsIntent.putExtra("wechat", wechat);
                accountsIntent.putExtra("alipay", alipay);
                accountsIntent.putExtra("action", 1);
                setResult(activity_index.RESULT_CODE_TOUCH, accountsIntent);
                activity_accounts.this.finish();
            }
        });

        accounts_tv_undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                networth = oNetworth;
                income = oIncome;
                expense = oExpense;
                cash = oCash;
                wechat = oWechat;
                alipay = oAlipay;
                accounts_rl_editing.setVisibility(View.INVISIBLE);
                accounts_ll_keyboard.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void editingAccounts(View view){
        accounts_rl_editing.setVisibility(View.VISIBLE);
        accounts_ll_keyboard.setVisibility(View.VISIBLE);
        switch (view.getId()){
            case R.id.accounts_rl_networth:
                editingAccounts = getString(R.string.accounts_tv_networth).toString();
                editingAmount = networth;
                break;
            case R.id.accounts_rl_income:
                break;
            case R.id.accounts_rl_expense:
                break;
            case R.id.accounts_rl_cash:
                break;
            case R.id.accounts_rl_wechat:
                break;
            case R.id.accounts_rl_alipay:
                break;
        }
    }


    /*
    public void keyboardDownAccounts(View view){

        switch (view.getId()){
            case R.id.create_key_0:
                if("".equals(amountString) == false){
                    amountString += '0';
                }
                else{
                    amountString = "0.00";
                }
                break;
            case R.id.create_key_1:
                amountString += '1';
                break;
            case R.id.create_key_2:
                amountString += '2';
                break;
            case R.id.create_key_3:
                amountString += '3';
                break;
            case R.id.create_key_4:
                amountString += '4';
                break;
            case R.id.create_key_5:
                amountString += '5';
                break;
            case R.id.create_key_6:
                amountString += '6';
                break;
            case R.id.create_key_7:
                amountString += '7';
                break;
            case R.id.create_key_8:
                amountString += '8';
                break;
            case R.id.create_key_9:
                amountString += '9';
                break;
            case R.id.create_key_dot:
                if(dotExist == 0) {
                    if("".equals(amountString)){
                        amountString = "0.";
                    }
                    else{
                        amountString += '.';
                    }
                    dotExist = 1;
                }
                break;
            case R.id.create_key_delete:
                if(dotExist == 1){
                    if(dotPosition == 0) dotExist = 0;
                    else dotPosition--;
                }
                if("0.00".equals(amountString) == false) {
                    if (amountString.length() == 1 || "0.".equals(amountString)) {
                        amountString = "0.00";
                    } else if (amountString.length() != 0) {
                        amountString = amountString.substring(0, amountString.length() - 1);
                    }
                }
                break;
        }*/
}