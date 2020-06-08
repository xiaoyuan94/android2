package com.xxyuan.project.ui.scanner;

import android.net.Uri;

import com.xxyuan.project.base.BasePresenter;
import com.xxyuan.project.utils.camera.CameraUtil;
import com.xxyuan.project.utils.camera.CropBean;

import java.io.File;

public class ScannerPresenter extends BasePresenter<ScannerContract.IScannerView>
        implements ScannerContract.IScannerPresenter {


    public ScannerPresenter(ScannerContract.IScannerView baseView) {
        super(baseView);
    }


    @Override
    public void startCrop(ScannerActivity activity, Uri data, File imageFile) {
        CropBean albumCropBean = new CropBean();
        albumCropBean.inputUri = data;
        albumCropBean.outputX = 300;
        albumCropBean.outputY = 300;
        albumCropBean.caculateAspect();
        albumCropBean.isReturnData = false;
        albumCropBean.isReturnData = true;
        //裁剪后输出的图片文件
        albumCropBean.outputUri = Uri.fromFile(imageFile);
        //跳转裁剪
        CameraUtil.openCrop(activity, albumCropBean, activity.REQUEST_CODE_CROP);
    }
}
