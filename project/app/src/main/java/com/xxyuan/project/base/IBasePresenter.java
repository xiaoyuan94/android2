package com.xxyuan.project.base;

import io.reactivex.Observable;

public interface IBasePresenter {
    /**
     * 解除绑定
     */
    void detachView();

    void addDisposable(Observable<?> flowable, BaseObserver observer);

    void removeDisposable();
}
