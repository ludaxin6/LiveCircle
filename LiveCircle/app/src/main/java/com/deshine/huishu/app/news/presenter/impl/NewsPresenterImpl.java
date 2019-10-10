package com.deshine.huishu.app.news.presenter.impl;

import com.deshine.huishu.app.bean.NewsInfo;
import com.deshine.huishu.app.client.RxDisposeManager;
import com.deshine.huishu.app.news.fragment.NewsFragment;
import com.deshine.huishu.app.news.model.bean.NewsChannelTable;
import com.deshine.huishu.app.news.model.impl.NewsModelImpl;
import com.deshine.huishu.app.news.presenter.NewPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by lvr on 2017/2/11.
 */

public class NewsPresenterImpl implements NewPresenter {
    private NewsFragment mNewsView;
    private NewsModelImpl mNewsModel;
    private List<NewsInfo> mInfos;


    public NewsPresenterImpl(NewsFragment view) {
        this.mNewsView = view;
        this.mNewsModel = new NewsModelImpl();
    }

    @Override
    public void lodeMineChannelsRequest() {
        Observable<List<NewsChannelTable>> observable = mNewsModel.requestNewsChannels();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NewsChannelTable>>() {
            @Override
            public void onSubscribe(Disposable d) {
                RxDisposeManager.get().add("newschannel", d);

            }

            @Override
            public void onNext(List<NewsChannelTable> tables) {
                mNewsView.returnNewsChannel(tables);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void loadNewsListRequest(String newsType, final String id, int startPage) {
        mInfos = new ArrayList<>();
        NewsInfo newsInfo = new NewsInfo();
        newsInfo.setTitle("头布局");
        mInfos.add(newsInfo);
        Observable<NewsInfo> observable = mNewsModel.requestNewsList(newsType, id, startPage);
        observable.subscribe(new Observer<NewsInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
                RxDisposeManager.get().add("newslist", d);
                mNewsView.showLoading();


            }

            @Override
            public void onNext(NewsInfo info) {
                mInfos.add(info);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
                mNewsView.showNetErrorTip();


            }

            @Override
            public void onComplete() {
                mNewsView.stopLoading();
                mNewsView.returnNewsList(mInfos);
            }
        });
    }

}
