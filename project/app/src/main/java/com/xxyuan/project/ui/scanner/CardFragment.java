package com.xxyuan.project.ui.scanner;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xxyuan.project.R;
import com.xxyuan.project.ui.scanner.card.BitmapUtils;
import com.xxyuan.project.ui.scanner.card.CameraConfig;
import com.xxyuan.project.ui.scanner.card.CameraUtils;
import com.xxyuan.project.ui.scanner.card.CameraView;
import com.xxyuan.project.ui.scanner.card.RectView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CardFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.camera)
    FrameLayout framelayout;
    @BindView(R.id.rect)
    RectView rectView;
    @BindView(R.id.iv_take)
    ImageView ivTake;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_flash)
    ImageView ivFlash;
    @BindView(R.id.iv_show)
    ImageView iv_show;

    private String mParam1;
    private String mParam2;
    private Unbinder unbinder;
    private Camera camera;
    private int ratioWidth = 4;
    private int ratioHeight = 3;
    private float percentLarge = 0.8f;
    private int topOffset = 0;
    private boolean flashOpen = false;
    private int maskColor = 0x2f000000;
    private int rectCornerColor = 0xff00ff00;
    private String hint = "请将方框对准证件拍照";
    private String noSupportHint;
    private String imagePath ;
    private CameraView cameraView;

    public CardFragment() {
    }

    public static CardFragment newInstance() {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initView();
    }

    protected void initView() {
        rectView.setMaskColor(maskColor);
        rectView.setCornerColor(rectCornerColor);
        rectView.setHintTextAndTextSize((hint == null || hint.length() == 0 ? hint : CameraConfig.DEFAULT_HINT_TEXT), 30);
        rectView.setTopOffset(topOffset);
        rectView.setRatioAndPercentOfScreen(ratioWidth, ratioHeight, percentLarge);
        if (!CameraUtils.checkCameraHardware(getActivity())) {
            Toast.makeText(getActivity(), noSupportHint == null ? CameraConfig.DEFAULT_NO_CAMERA_SUPPORT_HINT : noSupportHint, Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
    }
    @OnClick({R.id.iv_take, R.id.iv_back, R.id.iv_flash, R.id.rect})
    public void onClicked(View v) {
        switch (v.getId()) {
            case R.id.iv_take:
                takePhoto();
                break;
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.iv_flash:
                if (flashOpen) {
                    ivFlash.setBackgroundResource(R.mipmap.flash_on);
                    offLight();
                } else {
                    ivFlash.setBackgroundResource(R.mipmap.flash_off);
                    openLight();
                }
                flashOpen = !flashOpen;
                break;
            case R.id.rect:
                if (camera != null) {
                    camera.autoFocus(new Camera.AutoFocusCallback() {
                        @Override
                        public void onAutoFocus(boolean success, Camera camera) {
                        }
                    });
                }
                break;
            default:
                break;
        }
    }

    private void takePhoto() {

        try {
            camera.takePicture(null, null, new Camera.PictureCallback() {
                @Override
                public void onPictureTaken(byte[] data, Camera camera) {
                    imagePath = CameraConfig.DEFAULT_IMAGE_PATH + System.currentTimeMillis() + ".jpg";
                    File file = new File(imagePath);
                    if (file.exists()) {
                        file.delete();
                    }
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    try {
                        BitmapUtils.saveBitMap(getActivity(), imagePath, data, rectView.getCropLeft(), rectView.getCropTop(), rectView.getCropWidth(), rectView.getCropHeight(), true);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        Glide.with(getActivity())
                                .load(imagePath)
                                .apply(new RequestOptions().placeholder(R.mipmap.test))
                                .into(iv_show);
                        //重制拍摄
                        resumeCamera();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT >= 23) {
            if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0x01);
            } else {
                resumeCamera();
            }
        } else {
            resumeCamera();
        }
    }

    private void resumeCamera() {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            if (cameraView != null) {
                cameraView.setReleased(true);
            }
            camera = null;
        }
        camera = CameraUtils.open();
        cameraView = new CameraView(getActivity(), camera);
        cameraView.setReleased(false);
        framelayout.removeAllViews();
        framelayout.addView(cameraView);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                resumeCamera();
            } else {
                Toast.makeText(getActivity(), "No Camera Permission.", Toast.LENGTH_SHORT).show();
            }
        } else {
            resumeCamera();
        }
    }


    /**
     * 打开闪光灯
     */
    public synchronized void openLight() {
        if (camera != null) {
            try {
                Camera.Parameters parameters = camera.getParameters();
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(parameters);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭闪光灯
     */
    public synchronized void offLight() {
        if (camera != null) {
            try {
                Camera.Parameters parameters = camera.getParameters();
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(parameters);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        releaseCamera();
    }

    private void releaseCamera() {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            if (cameraView != null) {
                cameraView.setReleased(true);
            }
            camera = null;
        }
    }

}