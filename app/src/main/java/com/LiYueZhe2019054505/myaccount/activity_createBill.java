package com.LiYueZhe2019054505.myaccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class activity_createBill extends AppCompatActivity {

    private ImageView create_iv_return;
    private TextView create_tv_ie;
    private TextView create_tv_transfer;
    private TextView create_tv_again;

    private TextView create_tv_billType;
    private TextView create_tv_amount;

    private LinearLayout create_type_default;
    private LinearLayout create_type_food;
    private LinearLayout create_type_traffic;
    private LinearLayout create_type_phone;
    private LinearLayout create_type_debt;
    private LinearLayout create_type_gift;
    private LinearLayout create_type_house;
    private LinearLayout create_type_medical;
    private LinearLayout create_type_redpacket;
    private LinearLayout create_type_shopping;
    private LinearLayout create_type_sport;
    private LinearLayout create_type_wage;
    private LinearLayout create_type_daily;
    private LinearLayout create_type_party;
    private LinearLayout create_type_stock;
    private LinearLayout create_type_travel;

    private Button create_bt_direction;
    private Button create_bt_method;
    private Button create_bt_time;
    private Button create_bt_note;
    private Button create_key_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_createbill);

        //=====================================================寻找控件=====================================================
        create_iv_return = findViewById(R.id.create_iv_return);
        create_tv_ie = findViewById(R.id.create_tv_ie);
        create_tv_transfer = findViewById(R.id.create_tv_transfer);
        create_tv_again = findViewById(R.id.create_tv_again);

        create_tv_billType = findViewById(R.id.create_tv_billtype);
        create_tv_amount = findViewById(R.id.create_tv_amount);

        create_type_default = findViewById(R.id.create_type_default);
        create_type_food = findViewById(R.id.create_type_food);
        create_type_traffic = findViewById(R.id.create_type_traffic);
        create_type_phone = findViewById(R.id.create_type_phone);
        create_type_debt = findViewById(R.id.create_type_debt);
        create_type_gift = findViewById(R.id.create_type_gift);
        create_type_house = findViewById(R.id.create_type_house);
        create_type_medical = findViewById(R.id.create_type_medical);
        create_type_redpacket = findViewById(R.id.create_type_redpacket);
        create_type_shopping = findViewById(R.id.create_type_shopping);
        create_type_sport = findViewById(R.id.create_type_sport);
        create_type_wage = findViewById(R.id.create_type_wage);
        create_type_daily = findViewById(R.id.create_type_daily);
        create_type_party = findViewById(R.id.create_type_party);
        create_type_stock = findViewById(R.id.create_type_stock);
        create_type_travel = findViewById(R.id.create_type_travel);

        create_bt_direction = findViewById(R.id.create_bt_direction);
        create_bt_method = findViewById(R.id.create_bt_method);
        create_bt_time = findViewById(R.id.create_bt_time);
        create_bt_note = findViewById(R.id.create_bt_note);
        create_key_save = findViewById(R.id.create_key_save);

        //=====================================================返回=====================================================
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        create_key_save.setOnClickListener(View-> {
            if(Double.parseDouble(create_tv_amount.getText().toString()) <=0){
                Toast.makeText(activity_createBill.this, R.string.createbill_toast_empty, Toast.LENGTH_LONG).show();
                return;
            }
            Intent createIntent = new Intent();
            String direction = create_bt_direction.getText().toString();
            String type = create_tv_billType.getText().toString();
            String method = create_bt_method.getText().toString();
            String note = "";
            Double amount = 1.23;
            int year = 2022;
            int month = 1;
            int day = 1;
            createIntent.putExtra("postion", position);
            createIntent.putExtra("direction",direction);
            createIntent.putExtra("type",type);
            createIntent.putExtra("method",method);
            createIntent.putExtra("note",note);
            createIntent.putExtra("amount",amount);
            createIntent.putExtra("year",year);
            createIntent.putExtra("month",month);
            createIntent.putExtra("day",day);
            setResult(activity_index.RESULT_CODE_CREATE_BILL, createIntent);
            activity_createBill.this.finish();
        });

        //=====================================================顶部菜单=====================================================
        create_iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_createBill.this, activity_index.class);
                startActivity(intent);
            }
        });


        //=====================================================底部菜单=====================================================
        create_bt_direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getString(R.string.createbill_bt_income).equals(create_bt_direction.getText().toString())){
                    create_bt_direction.setText(getString(R.string.createbill_bt_expense));
                }
                else{
                    create_bt_direction.setText(getString(R.string.createbill_bt_income));
                }
            }
        });

        create_bt_method.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getString(R.string.accounttype_cash).equals(create_bt_method.getText().toString())){
                    create_bt_method.setText(getString(R.string.accounttype_wechat));
                }
                else if(getString(R.string.accounttype_wechat).equals(create_bt_method.getText().toString())){
                    create_bt_method.setText(getString(R.string.accounttype_alipay));
                }
                else{
                    create_bt_method.setText(getString(R.string.accounttype_cash));
                }
            }
        });
    }
    //=====================================================数字键盘=====================================================
    public void keyboardDown(View view){
        double amountNum = Double.parseDouble(create_tv_amount.getText().toString());

        switch (view.getId()){
            case R.id.create_key_0:
                amountNum += '0';
                break;
            case R.id.create_key_1:
                amountNum += '1';
                break;
            case R.id.create_key_2:
                amountNum += '2';
                break;
            case R.id.create_key_3:
                amountNum += '3';
                break;
            case R.id.create_key_4:
                amountNum += '4';
                break;
            case R.id.create_key_5:
                amountNum += '5';
                break;
            case R.id.create_key_6:
                amountNum += '6';
                break;
            case R.id.create_key_7:
                amountNum += '7';
                break;
            case R.id.create_key_8:
                amountNum += '8';
                break;
            case R.id.create_key_9:
                amountNum += '9';
                break;
        }
        create_tv_amount.setText(""+amountNum);
    }

    //=====================================================选择账单类别=====================================================
    @SuppressLint("NonConstantResourceId")
    public void updateRadio(View view){
        String billtype = getString(R.string.billtype_default);
        switch (view.getId()){
            case R.id.create_type_default:
                billtype = getString(R.string.billtype_default);
                break;
            case R.id.create_type_food:
                billtype = getString(R.string.billtype_food);
                break;
            case R.id.create_type_traffic:
                billtype = getString(R.string.billtype_traffic);
                break;
            case R.id.create_type_phone:
                billtype = getString(R.string.billtype_phone);
                break;
            case R.id.create_type_debt:
                billtype = getString(R.string.billtype_debt);
                break;
            case R.id.create_type_gift:
                billtype = getString(R.string.billtype_gift);
                break;
            case R.id.create_type_house:
                billtype = getString(R.string.billtype_house);
                break;
            case R.id.create_type_medical:
                billtype = getString(R.string.billtype_medical);
                break;
            case R.id.create_type_redpacket:
                billtype = getString(R.string.billtype_redpacket);
                break;
            case R.id.create_type_shopping:
                billtype = getString(R.string.billtype_shopping);
                break;
            case R.id.create_type_sport:
                billtype = getString(R.string.billtype_sport);
                break;
            case R.id.create_type_wage:
                billtype = getString(R.string.billtype_wage);
                break;
            case R.id.create_type_daily:
                billtype = getString(R.string.billtype_daily);
                break;
            case R.id.create_type_party:
                billtype = getString(R.string.billtype_party);
                break;
            case R.id.create_type_stock:
                billtype = getString(R.string.billtype_stock);
                break;
            case R.id.create_type_travel:
                billtype = getString(R.string.billtype_travel);
                break;
            default:
                break;
        }
        create_tv_billType.setText(billtype);
    }
}