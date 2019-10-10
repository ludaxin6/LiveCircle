package com.deshine.huishu.app.news.presenter.impl;

import com.deshine.huishu.app.client.RxDisposeManager;
import com.deshine.huishu.app.news.activity.NewsDetailActivity;
import com.deshine.huishu.app.news.model.bean.NewsDetail;
import com.deshine.huishu.app.news.model.impl.NewsDetailModelImpl;
import com.deshine.huishu.app.widget.LoadingDialog;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lvr on 2017/2/11.
 */

public class NewsDetailPresenterImpl implements com.deshine.huishu.app.news.presenter.NewsDetailPresenter {
    private NewsDetailModelImpl mModel;
    private NewsDetailActivity mView;
    //是否开启了dialog
    private boolean showDialog;

    public NewsDetailPresenterImpl(NewsDetailActivity view) {
        mView = view;
        mModel = new NewsDetailModelImpl();
    }

    @Override
    public void requestDetailNews(String postId) {
        Observable<NewsDetail> observable = mModel.getDetailNews(postId);
        observable.subscribe(new Observer<NewsDetail>() {
            @Override
            public void onSubscribe(Disposable d) {
                RxDisposeManager.get().add("newsDetail", d);
                if(!showDialog){
                    showDialog = true;
                    LoadingDialog.showDialogForLoading(mView);
                }
            }

            @Override
            public void onNext(NewsDetail detail) {
                mView.returnNewsDetail(detail);
            }

            @Override
            public void onError(Throwable e) {
                if(showDialog){
                    showDialog = false;
                    LoadingDialog.cancelDialogForLoading();
                }
                mView.showNetErrorTip();
            }

            @Override
            public void onComplete() {
                if(showDialog){
                    showDialog = false;
                    LoadingDialog.cancelDialogForLoading();
                }
            }
        });
    }
}
