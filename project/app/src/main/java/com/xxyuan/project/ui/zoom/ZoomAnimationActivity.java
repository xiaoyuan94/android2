package com.xxyuan.project.ui.zoom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.xxyuan.project.R;

public class ZoomAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_animation);
        ImageView iv_zoom = findViewById(R.id.iv_zoom);
        Button bt_fangda= findViewById(R.id.bt_fangda);

        bt_fangda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_zoom.setAnimation(AnimationUtils.loadAnimation(ZoomAnimationActivity.this,R.anim.fang_da));
            }
        });
    }
}