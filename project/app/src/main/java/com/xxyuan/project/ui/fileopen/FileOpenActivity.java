package com.xxyuan.project.ui.fileopen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xxyuan.project.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FileOpenActivity extends AppCompatActivity {

    @BindView(R.id.superFileView)
    SuperFileView2 superFileView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_open);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        //展示的方法
        superFileView.setOnGetFilePathListener(new SuperFileView2.OnGetFilePathListener() {
            @Override
            public void onGetFilePath(SuperFileView2 mSuperFileView2) {
                superFileView.displayFile(new File("/storage/emulated/0/down/test.ppt"));
            }
        });
        superFileView.show();
    }

    //  销毁时一定要停止展示，不然不会释放，无法第二次打开
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != superFileView) {
            superFileView.onStopDisplay();
        }
    }
}