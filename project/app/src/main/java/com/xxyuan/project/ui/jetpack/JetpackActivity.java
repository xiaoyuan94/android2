package com.xxyuan.project.ui.jetpack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.xxyuan.project.R;

public class JetpackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jetpack_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, JetpackFragment.newInstance())
                    .commitNow();
        }
    }
}
