package com.deshine.huishu.app.news.model.impl;

import com.deshine.huishu.app.app.AppApplication;
import com.deshine.huishu.app.app.AppConstant;
import com.deshine.huishu.app.client.RetrofitClient;
import com.deshine.huishu.app.news.db.NewsChannelTableManager;
import com.deshine.huishu.app.news.model.NewsChannelModel;
import com.deshine.huishu.app.news.model.bean.NewsChannelTable;
import com.deshine.huishu.app.utils.ACache;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by lvr on 2017/2/14.
 */

public class NewsChannelModelImpl implements NewsChannelModel {


    @Override
    public Observable<List<NewsChannelTable>> lodeMoreNewsChannels() {
        return Observable.create(new ObservableOnSubscribe<List<NewsChannelTable>>() {
            @Override
            public void subscribe(ObservableEmitter<List<NewsChannelTable>> e) throws Exception {
                ArrayList<NewsChannelTable> newsChannelTableList = (ArrayList<NewsChannelTable>) ACache.get(AppApplication.getAppContext()).getAsObject(AppConstant.CHANNEL_MORE);
                if(newsChannelTableList==null){
                    newsChannelTableList = (ArrayList<NewsChannelTable>) NewsChannelTableManager.loadNewsChannelsMore();
                    ACache.get(AppApplication.getAppContext()).put(AppConstant.CHANNEL_MORE,newsChannelTableList);

                }
                e.onNext(newsChannelTableList);
                e.onComplete();
            }

        }).compose(RetrofitClient.schedulersTransformer);
    }

    @Override
    public Observable<String> swapDb(final ArrayList<NewsChannelTable> newsChannelTableList) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                //交换过的列表进行缓存
                ACache.get(AppApplication.getAppContext()).put(AppConstant.CHANNEL_MINE,newsChannelTableList);
                e.onNext("");
                e.onComplete();
            }
        }).compose(RetrofitClient.schedulersTransformer);
    }

    @Override
    public Observable<String> updateDb(final ArrayList<NewsChannelTable> mineChannelTableList, final ArrayList<NewsChannelTable> moreChannelTableList) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                ACache.get(AppApplication.getAppContext()).put(AppConstant.CHANNEL_MINE,mineChannelTableList);
                ACache.get(AppApplication.getAppContext()).put(AppConstant.CHANNEL_MORE,moreChannelTableList);
                e.onNext("");
                e.onComplete();
            }
        }).compose(RetrofitClient.schedulersTransformer);
    }
}
