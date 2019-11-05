package com.xxyuan.project.ui.tablayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;
import com.xxyuan.project.R;
import com.xxyuan.project.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabActivity extends BaseActivity {

    @BindView(R.id.tb_layout)
    TabLayout tbLayout;

    private String[] tabs ={"水电费","嘘嘘","水电费水电费","是的发送到发送到"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tab;
    }

    @Override
    protected void initView() {
        super.initView();
        initTab();
    }

    /**
     * 初始化tab
     */
    private void initTab() {
        for (int i= 0;i<tabs.length;i++){
            View view = LayoutInflater.from(this).inflate(R.layout.item_tab_view,null,false);
            LinearLayout ll_item_bg = view.findViewById(R.id.ll_item_bg);
            TextView tv_tab_name = view.findViewById(R.id.tv_tab_name);
            tv_tab_name.setText(tabs[i]);

            if (i==0){
                ll_item_bg.setBackground(getResources().getDrawable(R.drawable.shape_oval));
                tv_tab_name.setTextColor(getResources().getColor(R.color.btn8));
            }else {
                ll_item_bg.setBackground(getResources().getDrawable(R.drawable.shape_oval2));
                tv_tab_name.setTextColor(getResources().getColor(R.color.btn2));
            }

            TabLayout.Tab tab = tbLayout.newTab();
            tab.setCustomView(view);

            tbLayout.addTab(tab);
        }
    }

    @Override
    protected void setListener() {
        super.setListener();
        tbLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                LinearLayout ll_item_bg = view.findViewById(R.id.ll_item_bg);
                TextView tv_tab_name = view.findViewById(R.id.tv_tab_name);
                ll_item_bg.setBackground(getResources().getDrawable(R.drawable.shape_oval));
                tv_tab_name.setTextColor(getResources().getColor(R.color.btn8));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                LinearLayout ll_item_bg = view.findViewById(R.id.ll_item_bg);
                TextView tv_tab_name = view.findViewById(R.id.tv_tab_name);
                ll_item_bg.setBackground(getResources().getDrawable(R.drawable.shape_oval2));
                tv_tab_name.setTextColor(getResources().getColor(R.color.btn2));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
