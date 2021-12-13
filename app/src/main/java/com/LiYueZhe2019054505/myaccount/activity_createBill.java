package com.LiYueZhe2019054505.myaccount;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

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
    private Button create_bt_account;
    private Button create_bt_time;
    private Button create_bt_note;
    private Button create_key_save;

    private EditText pop_create_note;
    private Button pop_create_noteClear;

    private String billNote;
    private Double billAmount;
    private int dotExist = 0;
    private int dotPosition = 0;

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
        create_bt_account = findViewById(R.id.create_bt_account);
        create_bt_time = findViewById(R.id.create_bt_time);
        create_bt_note = findViewById(R.id.create_bt_note);
        create_key_save = findViewById(R.id.create_key_save);

        //=====================================================初始化=====================================================
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) +1;
        if(month == 13){
            month = 1;
        }
        create_bt_time.setText(calendar.get(Calendar.YEAR) + "-" + month + "-" + calendar.get(Calendar.DAY_OF_MONTH));

        billNote = "";

        if((create_tv_amount.getText().toString()).contains(".")){
            dotExist = 1;
            dotPosition = (create_tv_amount.getText().toString()).indexOf(".");
        }

        //=====================================================返回=====================================================
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        String direction = intent.getStringExtra("direction");
        String type = intent.getStringExtra("type");
        String account = intent.getStringExtra("account");
        String note = intent.getStringExtra("note");
        Double amount = intent.getDoubleExtra("amount", 0);
        String time = intent.getStringExtra("time");

        if(null != time){
            create_bt_direction.setText(direction);
            create_tv_billType.setText(type);
            create_bt_account.setText(account);
            billNote = note;
            billAmount = amount;
            create_tv_amount.setText(amount+"");
            create_bt_time.setText(time);
        }
        create_key_save.setOnClickListener(View-> {
            if (Double.parseDouble(create_tv_amount.getText().toString()) <= 0) {
                Toast.makeText(activity_createBill.this, R.string.createbill_toast_empty, Toast.LENGTH_SHORT).show();
                return;
            }
            Intent createIntent = new Intent();

            billAmount = Double.parseDouble(create_tv_amount.getText().toString());
            if (getString(R.string.createbill_bt_expense).equals(create_bt_direction.getText().toString())) {
                billAmount *= -1;
            }

            createIntent.putExtra("position", position);
            createIntent.putExtra("direction", create_bt_direction.getText().toString());
            createIntent.putExtra("type", create_tv_billType.getText().toString());
            createIntent.putExtra("account", create_bt_account.getText().toString());
            createIntent.putExtra("note", billNote);
            createIntent.putExtra("amount", billAmount);
            createIntent.putExtra("time", create_bt_time.getText().toString());
            setResult(activity_index.RESULT_CODE_TOUCH, createIntent);
            activity_createBill.this.finish();
        });

        //=====================================================顶部菜单=====================================================
        create_iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity_createBill.this.finish();
            }
        });

        create_tv_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity_createBill.this, R.string.not_supported, Toast.LENGTH_SHORT).show();
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

        create_bt_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getString(R.string.accounttype_cash).equals(create_bt_account.getText().toString())){
                    create_bt_account.setText(getString(R.string.accounttype_wechat));
                }
                else if(getString(R.string.accounttype_wechat).equals(create_bt_account.getText().toString())){
                    create_bt_account.setText(getString(R.string.accounttype_alipay));
                }
                else{
                    create_bt_account.setText(getString(R.string.accounttype_cash));
                }
            }
        });

        //=====================================================选择时间=====================================================
        create_bt_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity_createBill.this);
                View v = (LinearLayout) getLayoutInflater().inflate(R.layout.pop_createtime, null);
                final DatePicker datePicker = (DatePicker) v.findViewById(R.id.pop_create_time);

                datePicker.setCalendarViewShown(false);

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null);

                builder.setView(v);
                builder.setTitle(getString(R.string.time_pop_title));

                builder.setPositiveButton(getString(R.string.time_pop_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int month = datePicker.getMonth()+1;
                        if(month == 13){
                            month = 1;
                        }
                        create_bt_time.setText(Integer.toString(datePicker.getYear()) + "-" + Integer.toString(month) + "-" +Integer.toString(datePicker.getDayOfMonth()));
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton(getString(R.string.time_pop_today), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Calendar calendar = Calendar.getInstance();
                        int month = calendar.get(Calendar.MONTH) +1;
                        if(month == 13){
                            month = 1;
                        }
                        create_bt_time.setText(calendar.get(Calendar.YEAR) + "-" + month + "-" + calendar.get(Calendar.DAY_OF_MONTH));
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }
        });

        //=====================================================编写备注=====================================================
        create_bt_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity_createBill.this);
                View v = (LinearLayout) getLayoutInflater().inflate(R.layout.pop_createnote, null);
                builder.setView(v);

                pop_create_note = v.findViewById(R.id.pop_create_note);
                pop_create_noteClear = v.findViewById(R.id.pop_create_noteClear);

                pop_create_note.setText(billNote);

                builder.setPositiveButton(getString(R.string.note_pop_save), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        billNote = pop_create_note.getText().toString();
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton(getString(R.string.note_pop_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();

                pop_create_noteClear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pop_create_note.setText("");
                    }
                });
            }
        });
    }

    //=====================================================数字键盘=====================================================

    public void keyboardDown(View view){
        String amountString = create_tv_amount.getText().toString();
        if(amountString.length() > 6 && view.getId() != R.id.create_key_delete){
            Toast.makeText(activity_createBill.this, R.string.createbill_toast_long, Toast.LENGTH_SHORT).show();
            return;
        }
        if("0.00".equals(amountString) && view.getId() != R.id.create_key_delete){
            amountString ="";
            if(view.getId() != R.id.create_key_dot){
                dotExist = 0;
                dotPosition = 0;
            }
        }
        if(dotExist == 1 && view.getId() != R.id.create_key_dot && view.getId() != R.id.create_key_delete){
            if(dotPosition == 2) return;
            else dotPosition++;
        }
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
        }
        create_tv_amount.setText(amountString);
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