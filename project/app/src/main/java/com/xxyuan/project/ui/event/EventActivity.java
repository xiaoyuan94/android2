package com.xxyuan.project.ui.event;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.LogUtils;
import com.xxyuan.project.R;

public class EventActivity extends AppCompatActivity {

    private Button bt_event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        bt_event = findViewById(R.id.bt_event);
        bt_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("bt_event click");
//                Thread.dumpStack();
            }
        });

        bt_event.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                LogUtils.d("bt_event onLongClick");
                Thread.dumpStack();
                return false;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtils.d("MotionEvent  click"+ev.toString());
//        Thread.dumpStack();
        return super.dispatchTouchEvent(ev);
    }
}