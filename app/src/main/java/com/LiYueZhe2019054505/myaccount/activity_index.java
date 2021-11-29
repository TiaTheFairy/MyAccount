package com.LiYueZhe2019054505.myaccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.LiYueZhe2019054505.myaccount.p_startup.activity_normalStart;

public class activity_index extends AppCompatActivity {

    private ImageView index_iv_menu;
    private ImageView index_iv_share;
    private PopupWindow popup_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(0x80000000, 0x80000000);
        setContentView(R.layout.layout_index);

        index_iv_menu = findViewById(R.id.index_iv_menu);
        index_iv_share = findViewById(R.id.index_iv_share);

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
    }

    protected void initLeftPopup(){
        final View popup_left_view = getLayoutInflater().inflate(R.layout.layout_leftmenu, null, false);
        popup_left = new PopupWindow(popup_left_view, 700, ActionBar.LayoutParams.MATCH_PARENT, true);

        popup_left_view.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if(popup_left_view != null && popup_left_view.isShown()){
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