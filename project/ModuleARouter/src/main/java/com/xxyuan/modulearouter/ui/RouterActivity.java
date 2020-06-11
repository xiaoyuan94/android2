package com.xxyuan.modulearouter.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xxyuan.modulearouter.R;

@Route(path = "/ModuleARouter/RouterActivity")
public class RouterActivity extends AppCompatActivity {

    @Autowired(name = "key3")
    String key3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);//路由跳转携带数据，必须注册
        setContentView(R.layout.activity_router);
        initView();
    }

    private void initView() {
        TextView tv_key3 = findViewById(R.id.tv_key3);
        tv_key3.setText(key3);
    }
}