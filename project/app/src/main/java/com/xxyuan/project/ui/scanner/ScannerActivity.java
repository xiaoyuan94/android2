package com.xxyuan.project.ui.scanner;

import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.material.tabs.TabLayout;
import com.xxyuan.project.R;
import com.xxyuan.project.base.BaseActivity;
import com.xxyuan.project.ui.scanner.bar.CaptureFragment;
import com.xxyuan.project.ui.scanner.bar.CodeUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ScannerActivity extends BaseActivity<ScannerPresenter> implements ScannerContract.IScannerView {


    @BindView(R.id.content)
    FrameLayout content;
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_scanner;
    }

    @Override
    protected ScannerPresenter createPresenter() {
        return new ScannerPresenter(this);
    }

    @Override
    protected void initView() {
        CaptureFragment captureFragment = new CaptureFragment();
//        CardFragment cardFragment = CardFragment.newInstance();
//        BarFragment barFragment = BarFragment.newInstance();
        captureFragment.setAnalyzeCallback(analyzeCallback);
        mFragmentList.add(captureFragment);
//        mFragmentList.add(cardFragment);
        switchFragment(0);
    }

    @OnClick({R.id.bt_card, R.id.bt_bar})
    public void onClicked(View v) {
        switch (v.getId()) {
            case R.id.bt_card:
                switchFragment(0);
                break;
            case R.id.bt_bar:
                switchFragment(1);
                break;
            default:
                break;
        }
    }

    public void switchFragment(int idx) {
        for (int i = 0; i < mFragmentList.size(); i++) {
            Fragment fragment = mFragmentList.get(i);
            FragmentTransaction ft = obtainFragmentTransaction(idx);
            if (idx == i) {
                getCurrentFragment(idx).onPause(); // 暂停当前tab
                if (fragment.isAdded()) {
                    fragment.onResume(); // 启动目标tab的onResume()
                } else {
                    try {
                        ft.add(R.id.content, fragment);
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }
                }
                ft.show(fragment);//显示Tab
            } else {
                ft.hide(fragment);//隐藏其它Tab
            }
            ft.commit();
        }
    }

    public Fragment getCurrentFragment(int idx) {
        return mFragmentList.get(idx);
    }
    private FragmentTransaction obtainFragmentTransaction(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        return ft;
    }


    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            LogUtils.d("扫描二维码"+result);
        }

        @Override
        public void onAnalyzeFailed() {

        }
    };
}