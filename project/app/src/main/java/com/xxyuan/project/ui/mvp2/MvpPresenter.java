package com.xxyuan.project.ui.mvp2;


import com.blankj.utilcode.util.LogUtils;
import com.xxyuan.project.base.BaseObserver;
import com.xxyuan.project.base.BasePresenter;
import com.xxyuan.project.ui.mvp2.bean.DouBanResBean;

import java.util.List;

/**
 * 作者： ch
 * 时间： 2019/11/19 17:29
 * 描述：
 * 来源：
 */
public class MvpPresenter extends BasePresenter<MvpView> {
    public MvpPresenter(MvpView baseView) {
        super(baseView);
    }


    public void getDouban() {
        addDisposable(apiServer.getDouban(), new BaseObserver<DouBanResBean>(baseView) {
            @Override
            public void onSuccess(DouBanResBean douBanResBean) {
                LogUtils.d(douBanResBean.getTitle());
            }

            @Override
            public void onError(String msg) {
                LogUtils.d(msg);
            }
        });
    }
}
