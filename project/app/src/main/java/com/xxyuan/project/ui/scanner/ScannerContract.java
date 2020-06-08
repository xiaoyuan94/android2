package com.xxyuan.project.ui.scanner;

import android.net.Uri;

import com.xxyuan.project.base.BaseView;
import com.xxyuan.project.base.IBasePresenter;

import java.io.File;

public interface ScannerContract {
    interface IScannerView extends BaseView {

    }

    interface IScannerPresenter extends IBasePresenter {

        void startCrop(ScannerActivity scannerActivity, Uri data, File imageFile);
    }
}
