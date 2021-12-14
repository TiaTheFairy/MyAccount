package com.LiYueZhe2019054505.myaccount;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.LiYueZhe2019054505.myaccount.datas.Accounts;
import com.LiYueZhe2019054505.myaccount.datas.Bills;
import com.LiYueZhe2019054505.myaccount.datas.DataBank;
import com.LiYueZhe2019054505.myaccount.p_startup.activity_normalStart;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class activity_index extends AppCompatActivity {

    public static final int RESULT_CODE_TOUCH = 500;

    private List<Bills> billsList;
    private Accounts accounts;
    private DataBank dataBank;
    private MyRecyclerViewAdapter recyclerViewAdapter;
    private PopupWindow popup_left;

    private ImageView index_iv_menu;
    private ImageView index_iv_share;
    private ImageView index_iv_create;

    private TextView index_tv_networth;
    private TextView index_tv_income;
    private TextView index_tv_expense;

    //=====================================================增删改查=====================================================
    ActivityResultLauncher<Intent> launcherAdd = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            Intent data = result.getData();
            int resultCode = result.getResultCode();
            if(resultCode == RESULT_CODE_TOUCH) {
                if (null == data) return;
                String direction = data.getStringExtra("direction");
                String type = data.getStringExtra("type");
                String account = data.getStringExtra("account");
                String note = data.getStringExtra("note");
                double amount = data.getDoubleExtra("amount", 0);
                String time = data.getStringExtra("time");
                int position = data.getIntExtra("position", billsList.size());

                billsList.add(position, new Bills(direction, type, account, note, amount, time));

                if(getString(R.string.createbill_bt_income).equals(direction)){
                    accounts.income += amount;
                }
                else{
                    accounts.expense -= amount;
                }
                accounts.networth += amount;

                if(getString(R.string.accounttype_cash).equals(account)){
                    accounts.cash += amount;
                }
                else if(getString(R.string.accounttype_wechat).equals(account)){
                    accounts.wechat += amount;
                }
                else{
                    accounts.alipay += amount;
                }

                dataBank.saveBills();
                dataBank.saveAccounts();
                recyclerViewAdapter.notifyItemInserted(position);
                refreshDisplay();
            }
        }
    });

    ActivityResultLauncher<Intent> launcherEdit = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            Intent data = result.getData();
            int resultCode = result.getResultCode();
            if(resultCode == RESULT_CODE_TOUCH) {
                if (null == data) return;
                int position = data.getIntExtra("position", billsList.size());

                String type = data.getStringExtra("type");
                String note = data.getStringExtra("note");
                String time = data.getStringExtra("time");

                String oDirection = billsList.get(position).getBillDirection();
                String direction = data.getStringExtra("direction");
                String oAccount = billsList.get(position).getBillAccount();
                String account = data.getStringExtra("account");
                double oAmount = billsList.get(position).getBillAmount();
                double amount = data.getDoubleExtra("amount", 0);

                if((oDirection.equals(direction)) == false){
                    if((getString(R.string.createbill_bt_income)).equals(direction)){
                        accounts.income += amount;
                        accounts.expense += oAmount;
                        accounts.networth += amount;
                        accounts.networth -= oAmount;
                    }
                    else{
                        accounts.income -= oAmount;
                        accounts.expense -= amount;
                        accounts.networth -= oAmount;
                        accounts.networth += amount;
                    }
                }
                if((oAccount.equals(account)) == false){
                    if((getString(R.string.accounttype_cash)).equals(oAccount)){
                        accounts.cash -= oAmount;
                        if((getString(R.string.accounttype_wechat)).equals(account)){
                            accounts.wechat += amount;
                        }
                        else{
                            accounts.alipay += amount;
                        }
                    }
                    else if((getString(R.string.accounttype_wechat)).equals(oAccount)){
                        accounts.wechat -= oAmount;
                        if((getString(R.string.accounttype_cash)).equals(account)){
                            accounts.cash += amount;
                        }
                        else{
                            accounts.alipay += amount;
                        }
                    }
                    else{
                        accounts.alipay -= oAmount;
                        if((getString(R.string.accounttype_cash)).equals(account)){
                            accounts.cash += amount;
                        }
                        else{
                            accounts.wechat += amount;
                        }
                    }
                }

                billsList.get(position).setBillDirection(direction);
                billsList.get(position).setBillType(type);
                billsList.get(position).setBillAccount(account);
                billsList.get(position).setBillNote(note);
                billsList.get(position).setBillAmount(amount);
                billsList.get(position).setBillTime(time);
                dataBank.saveBills();
                dataBank.saveAccounts();
                recyclerViewAdapter.notifyItemChanged(position);
                refreshDisplay();
            }
        }
    });

    ActivityResultLauncher<Intent> launcherView = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            Intent data = result.getData();
            int resultCode = result.getResultCode();
            if(resultCode == RESULT_CODE_TOUCH) {
                if (null == data) return;
                String direction = data.getStringExtra("direction");
                String type = data.getStringExtra("type");
                String account = data.getStringExtra("account");
                String note = data.getStringExtra("note");
                double amount = data.getDoubleExtra("amount", 0);
                String time = data.getStringExtra("time");
                int position = data.getIntExtra("position", billsList.size());
            }
        }
    });

    void removeItem(int position){
        String account = billsList.get(position).getBillAccount();
        double amount = billsList.get(position).getBillAmount();

        if(amount > 0){
            accounts.income -= amount;
        }
        else{
            accounts.expense += amount;
        }
        accounts.networth -= amount;

        if((getString(R.string.accounttype_cash)).equals(account)){
            accounts.cash -= amount;
        }
        else if((getString(R.string.accounttype_wechat)).equals(account)){
            accounts.wechat -= amount;
        }
        else{
            accounts.alipay -= amount;
        }

        billsList.remove(position);
        dataBank.saveBills();
        dataBank.saveAccounts();
        recyclerViewAdapter.notifyItemRemoved(position);
        refreshDisplay();
    }

    //=====================================================初始化和onCreate=====================================================
    public void initData(){
        dataBank = new DataBank(activity_index.this);
        accounts = new Accounts(0,0,0,0,0,0);
        billsList = dataBank.loadBills();
        //accounts = dataBank.loadAccounts();
    }

    public void refreshDisplay(){
        index_tv_networth.setText(accounts.networth+"");
        index_tv_income.setText(accounts.income+"");
        index_tv_expense.setText(accounts.expense+"");
        dataBank.saveAccounts();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(0x80000000, 0x80000000);
        setContentView(R.layout.layout_index);

        initData();

        index_iv_menu = findViewById(R.id.index_iv_menu);
        index_iv_share = findViewById(R.id.index_iv_share);
        index_iv_create = findViewById(R.id.index_iv_create);
        index_tv_networth = findViewById(R.id.index_tv_networth);
        index_tv_income = findViewById(R.id.index_tv_income);
        index_tv_expense = findViewById(R.id.index_tv_expense);

        refreshDisplay();

        RecyclerView mainRecyclerView = findViewById(R.id.index_rv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new activity_index.MyRecyclerViewAdapter(billsList);
        mainRecyclerView.setAdapter(recyclerViewAdapter);

        index_iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLeftPopup();
                popup_left.showAtLocation(view, Gravity.LEFT, 0, 0);
            }
        });

        index_iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity_index.this, getResources().getString(R.string.not_supported), Toast.LENGTH_LONG).show();
            }
        });

        index_iv_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_index.this, activity_createBill.class);
                intent.putExtra("position", billsList.size());
                launcherAdd.launch(intent);
            }
        });
    }

    //=====================================================RecyclerView=====================================================

    private class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
        private List<Bills> billsList;

        public MyRecyclerViewAdapter(List<Bills> billsList){
            this.billsList = billsList;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_bills, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.MyViewHolder holder, int position){
            if(getString(R.string.billtype_default).equals(billsList.get(position).getBillType())){
                holder.getBillImage().setImageResource(R.drawable.bill_default);
            }
            else if(getString(R.string.billtype_food).equals(billsList.get(position).getBillType())){
                holder.getBillImage().setImageResource(R.drawable.bill_food);
            }
            if(getString(R.string.billtype_traffic).equals(billsList.get(position).getBillType())){
                holder.getBillImage().setImageResource(R.drawable.bill_traffic);
            }
            else if(getString(R.string.billtype_phone).equals(billsList.get(position).getBillType())){
                holder.getBillImage().setImageResource(R.drawable.bill_phone);
            }
            else if(getString(R.string.billtype_debt).equals(billsList.get(position).getBillType())){
                holder.getBillImage().setImageResource(R.drawable.bill_debt);
            }
            else if(getString(R.string.billtype_gift).equals(billsList.get(position).getBillType())){
                holder.getBillImage().setImageResource(R.drawable.bill_gift);
            }
            else if(getString(R.string.billtype_house).equals(billsList.get(position).getBillType())){
                holder.getBillImage().setImageResource(R.drawable.bill_house);
            }
            else if(getString(R.string.billtype_medical).equals(billsList.get(position).getBillType())){
                holder.getBillImage().setImageResource(R.drawable.bill_medical);
            }
            else if(getString(R.string.billtype_redpacket).equals(billsList.get(position).getBillType())){
                holder.getBillImage().setImageResource(R.drawable.bill_redpacket);
            }
            else if(getString(R.string.billtype_shopping).equals(billsList.get(position).getBillType())){
                holder.getBillImage().setImageResource(R.drawable.bill_shopping);
            }
            else if(getString(R.string.billtype_sport).equals(billsList.get(position).getBillType())){
                holder.getBillImage().setImageResource(R.drawable.bill_sport);
            }
            else if(getString(R.string.billtype_wage).equals(billsList.get(position).getBillType())){
                holder.getBillImage().setImageResource(R.drawable.bill_wage);
            }
            else if(getString(R.string.billtype_daily).equals(billsList.get(position).getBillType())){
                holder.getBillImage().setImageResource(R.drawable.bill_daily);
            }
            else if(getString(R.string.billtype_party).equals(billsList.get(position).getBillType())){
                holder.getBillImage().setImageResource(R.drawable.bill_party);
            }
            else if(getString(R.string.billtype_stock).equals(billsList.get(position).getBillType())){
                holder.getBillImage().setImageResource(R.drawable.bill_stock);
            }
            else if(getString(R.string.billtype_travel).equals(billsList.get(position).getBillType())){
                holder.getBillImage().setImageResource(R.drawable.bill_travel);
            }

            holder.getBillType().setText(billsList.get(position).getBillType());
            holder.getBillAccount().setText(billsList.get(position).getBillAccount());
            String billAmount = billsList.get(position).getBillAmount() + "";
            if(billAmount.length() - billAmount.indexOf(".") == 2){
                billAmount += "0";
            }
            holder.getBillAmount().setText(billAmount);
        }

        @Override
        public int getItemCount(){
            return billsList.size();
        }

        private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
            public static final int LIST_REMOVE = 1;
            public static final int LIST_DETAIL = 2;
            public static final int LIST_EDIT = 3;
            public static final int LIST_REFRESH = 4;

            private final ImageView billImage;
            private final TextView billType;
            private final TextView billAccount;
            private final TextView billAmount;

            public MyViewHolder(View itemView){
                super(itemView);

                this.billImage = itemView.findViewById(R.id.bill_iv_type);
                this.billType = itemView.findViewById(R.id.bill_tv_type);
                this.billAccount = itemView.findViewById(R.id.bill_tv_account);
                this.billAmount = itemView.findViewById(R.id.bill_tv_amount);

                itemView.setOnCreateContextMenuListener(this);
            }

            public ImageView getBillImage(){
                return billImage;
            }

            public TextView getBillType() {
                return billType;
            }

            public TextView getBillAccount() {
                return billAccount;
            }

            public TextView getBillAmount() {
                return billAmount;
            }

            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                MenuItem menuItemRemove = contextMenu.add(Menu.NONE, LIST_REMOVE, LIST_REMOVE, getString(R.string.longclick_tv_remove));
                MenuItem menuItemDetail = contextMenu.add(Menu.NONE, LIST_DETAIL, LIST_DETAIL, getString(R.string.longclick_tv_detail));
                MenuItem menuItemEdit = contextMenu.add(Menu.NONE, LIST_EDIT, LIST_EDIT, getString(R.string.longclick_tv_edit));
                MenuItem menuItemRefresh = contextMenu.add(Menu.NONE, LIST_REFRESH, LIST_REFRESH, getString(R.string.longclick_tv_refresh));

                menuItemRemove.setOnMenuItemClickListener(this);
                menuItemDetail.setOnMenuItemClickListener(this);
                menuItemEdit.setOnMenuItemClickListener(this);
                menuItemRefresh.setOnMenuItemClickListener(this);
            }

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int position = getAdapterPosition();
                Intent intent;
                switch (menuItem.getItemId()){
                    case LIST_REMOVE:
                        removeItem(position);
                        break;
                    case LIST_DETAIL:
                        intent = new Intent(activity_index.this, activity_detail.class);
                        intent.putExtra("position", position);
                        intent.putExtra("direction", billsList.get(position).getBillDirection());
                        intent.putExtra("type", billsList.get(position).getBillType());
                        intent.putExtra("account", billsList.get(position).getBillAccount());
                        intent.putExtra("note", billsList.get(position).getBillNote());
                        intent.putExtra("amount", billsList.get(position).getBillAmount());
                        intent.putExtra("time", billsList.get(position).getBillTime());
                        launcherView.launch(intent);
                        break;
                    case LIST_EDIT:
                        intent = new Intent(activity_index.this, activity_createBill.class);
                        intent.putExtra("position", position);
                        intent.putExtra("direction", billsList.get(position).getBillDirection());
                        intent.putExtra("type", billsList.get(position).getBillType());
                        intent.putExtra("account", billsList.get(position).getBillAccount());
                        intent.putExtra("note", billsList.get(position).getBillNote());
                        intent.putExtra("amount", billsList.get(position).getBillAmount());
                        intent.putExtra("time", billsList.get(position).getBillTime());
                        launcherEdit.launch(intent);
                        break;
                    case LIST_REFRESH:
                        break;
                }
                return false;
            }

        }
    }
    //=====================================================左侧弹出菜单=====================================================
    protected void initLeftPopup(){
        final View popup_left_view = getLayoutInflater().inflate(R.layout.pop_leftmenu, null, false);
        popup_left = new PopupWindow(popup_left_view, 700, ActionBar.LayoutParams.MATCH_PARENT, true);

        popup_left_view.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if(popup_left_view.isShown()){
                    popup_left.dismiss();
                    popup_left = null;
                }
                return false;
            }
        });
        popup_left_view.setBackgroundDrawable(new ColorDrawable(0));

        LinearLayout leftmenu_lo_index = popup_left_view.findViewById(R.id.leftmenu_lo_index);

        leftmenu_lo_index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_index.this, activity_normalStart.class);
                startActivity(intent);
            }
        });
    }

    private void getLeftPopup(){
        if(null != popup_left){
            popup_left.dismiss();
            return;
        }
        else{
            initLeftPopup();
        }
    }
}