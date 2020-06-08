package com.xxyuan.project.base;


import com.xxyuan.project.http.model3.ApiRetrofit;
import com.xxyuan.project.http.model3.api.ApiServer;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者： ch
 * 时间： 2016/12/27.13:56
 * 描述：
 * 来源：
 */

public abstract class BasePresenter<V extends BaseView> implements IBasePresenter {

    public CompositeDisposable compositeDisposable;


    public V baseView;

    protected ApiServer apiServer = ApiRetrofit.getInstance().getApiService();

    public BasePresenter(V baseView) {
        this.baseView = baseView;
    }

    /**
     * 解除绑定
     */
    @Override
    public void detachView() {
        baseView = null;
        removeDisposable();
    }

    @Override
    public void addDisposable(Observable<?> flowable, BaseObserver observer) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(observer));

    }

//    public void addDisposable(Flowable<?> flowable, BaseSubscriber subscriber) {
//        if (compositeDisposable == null) {
//            compositeDisposable = new CompositeDisposable();
//        }
//        compositeDisposable.add(flowable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(subscriber));
//
//    }

    @Override
    public void removeDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}
