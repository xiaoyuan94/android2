package com.xxyuan.project.ui.scanner;

import com.xxyuan.project.base.BasePresenter;

public class ScannerPresenter extends BasePresenter<ScannerContract.IScannerView>
        implements ScannerContract.IScannerPresenter {

    public ScannerPresenter(ScannerContract.IScannerView baseView) {
        super(baseView);
    }
}
