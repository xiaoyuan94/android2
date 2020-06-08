package com.xxyuan.project.ui.scanner;

import android.Manifest;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.material.tabs.TabLayout;
import com.xxyuan.project.R;
import com.xxyuan.project.base.BaseActivity;
import com.xxyuan.project.ui.scanner.bar.CaptureFragment;
import com.xxyuan.project.ui.scanner.bar.CodeUtils;
import com.xxyuan.project.utils.CheckPermissionUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;


public class ScannerActivity extends BaseActivity<ScannerPresenter>
        implements ScannerContract.IScannerView, EasyPermissions.PermissionCallbacks {


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
        //初始化权限
        initPermission();

        CardFragment cardFragment = CardFragment.newInstance();
        BarFragment barFragment = BarFragment.newInstance();
        mFragmentList.add(cardFragment);
        mFragmentList.add(barFragment);
        switchFragment(0);
    }

    /**
     * 初始化权限事件
     */
    private void initPermission() {
        //检查权限
        String[] permissions = CheckPermissionUtils.checkPermission(this);
        if (permissions.length == 0) {
            //权限都申请了
            LogUtils.d("权限都申请了");
        } else {
            //申请权限
            ActivityCompat.requestPermissions(this, permissions, 100);
        }
    }

    /**
     * 请求CAMERA权限码
     */
    public static final int REQUEST_CAMERA_PERM = 101;


    /**
     * EsayPermissions接管权限处理逻辑
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @AfterPermissionGranted(REQUEST_CAMERA_PERM)
    public void cameraTask(int viewId) {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            LogUtils.d("拥有了权限");
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, "需要请求camera权限",
                    REQUEST_CAMERA_PERM, Manifest.permission.CAMERA);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Toast.makeText(this, "执行onPermissionsGranted()...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(this, "执行onPermissionsDenied()...", Toast.LENGTH_SHORT).show();
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this)
                    .setTitle("权限申请")
                    .setPositiveButton("确认")
                    .setNegativeButton("取消")
                    .setRequestCode(REQUEST_CAMERA_PERM)
                    .build()
                    .show();
        }
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

}