package com.LiYueZhe2019054505.myaccount;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
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
import com.LiYueZhe2019054505.myaccount.p_startup.activity_firstStart;
import com.LiYueZhe2019054505.myaccount.p_startup.activity_normalStart;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class activity_index extends AppCompatActivity {

    public static final int RESULT_CODE_CREATE_BILL = 500;
    private List<Bills> billsList;
    private DataBank dataBank;
    private Accounts accounts;
    private MyRecyclerViewAdapter recyclerViewAdapter;

    private ImageView index_iv_menu;
    private ImageView index_iv_share;
    private FloatingActionButton index_bt_create;
    private PopupWindow popup_left;

    //=====================================================新增和修改账单=====================================================
    ActivityResultLauncher<Intent> launcherAdd = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            Intent data = result.getData();
            int resultCode = result.getResultCode();
            if(resultCode == RESULT_CODE_CREATE_BILL) {
                if (null == data) return;
                String direction = data.getStringExtra("direction");
                String type = data.getStringExtra("type");
                String method = data.getStringExtra("method");
                String note = data.getStringExtra("note");
                double amount = data.getDoubleExtra("amount", 0);
                int year = data.getIntExtra("year", 2021);
                int month = data.getIntExtra("month", 1);
                int day = data.getIntExtra("day", 1);
                int position = data.getIntExtra("position", billsList.size());

                billsList.add(position, new Bills(direction, type, method, note, amount, year, month, day));
                dataBank.saveData();
                recyclerViewAdapter.notifyItemInserted(position);
            }
        }
    });

    //=====================================================初始化和onCreate=====================================================
    public void initData(){
        dataBank = new DataBank(activity_index.this);
        billsList = dataBank.loadBills();
        accounts = dataBank.loadAccounts();
        /*
        billsList = new ArrayList<Bills>();
        billsList.add(new Bills("e","e","e","e",1.23,2022,1,1));
        billsList.add(new Bills("e","e","e","e",1.23,2022,1,1));
        billsList.add(new Bills("e","e","e","e",1.23,2022,1,1));
         */
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(0x80000000, 0x80000000);
        setContentView(R.layout.layout_index);

        initData();

        index_iv_menu = findViewById(R.id.index_iv_menu);
        index_iv_share = findViewById(R.id.index_iv_share);
        index_bt_create = findViewById(R.id.index_bt_create);

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

        index_bt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_index.this, activity_createBill.class);
                intent.putExtra("position", 10);
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

            holder.getBillType().setText(billsList.get(position).getBillType());
            holder.getBillAccount().setText(billsList.get(position).getBillMethod());
            //holder.getBillAmount().setText(toString(billsList.get(position).getBillAmount()));



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
                        break;
                    case LIST_DETAIL:
                        break;
                    case LIST_EDIT:
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
        final View popup_left_view = getLayoutInflater().inflate(R.layout.layout_leftmenu, null, false);
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