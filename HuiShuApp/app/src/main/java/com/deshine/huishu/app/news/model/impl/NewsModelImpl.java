package com.deshine.huishu.app.news.model.impl;

import com.deshine.huishu.app.api.ApiConstants;
import com.deshine.huishu.app.api.ApiService;
import com.deshine.huishu.app.app.AppApplication;
import com.deshine.huishu.app.app.AppConstant;
import com.deshine.huishu.app.bean.NewsInfo;
import com.deshine.huishu.app.client.RetrofitClient;
import com.deshine.huishu.app.news.db.NewsChannelTableManager;
import com.deshine.huishu.app.news.model.NewsModel;
import com.deshine.huishu.app.news.model.bean.NewsChannelTable;
import com.deshine.huishu.app.utils.ACache;
import com.deshine.huishu.app.utils.TimeUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lvr on 2017/2/7.
 */

public class NewsModelImpl implements NewsModel {
    @Override
    public Observable<List<NewsChannelTable>> requestNewsChannels() {
        return Observable.create(new ObservableOnSubscribe<List<NewsChannelTable>>() {
            @Override
            public void subscribe(ObservableEmitter<List<NewsChannelTable>> e) throws Exception {
                ArrayList<NewsChannelTable> newsChannelTableList = (ArrayList<NewsChannelTable>) ACache.get(AppApplication.getAppContext()).getAsObject(AppConstant.CHANNEL_MINE);
                if(newsChannelTableList==null){
                    newsChannelTableList= (ArrayList<NewsChannelTable>) NewsChannelTableManager.loadNewsChannelsStatic();
                    ACache.get(AppApplication.getAppContext()).put(AppConstant.CHANNEL_MINE,newsChannelTableList);
                }
                e.onNext(newsChannelTableList);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<NewsInfo> requestNewsList(String newsType, final String id, int startPage) {
        RetrofitClient retrofitClient = RetrofitClient.getInstance(AppApplication.getAppContext(), ApiService.NEWS_BASE_URL);
        ApiService api = retrofitClient.create(ApiService.class);
        return api.getNewsList(newsType,id,startPage).flatMap(new Function<Map<String, List<NewsInfo>>, ObservableSource<NewsInfo>>() {
            @Override
            public ObservableSource<NewsInfo> apply(Map<String, List<NewsInfo>> map) throws Exception {
                //房产类型的信息
                if (id.endsWith(ApiConstants.HOUSE_ID)) {
                    return Observable.fromIterable(map.get("北京"));
                }else{
                    return Observable.fromIterable(map.get(id));
                }
            }
        }).map(new Function<NewsInfo, NewsInfo>() {//转换时间格式
            @Override
            public NewsInfo apply(NewsInfo info) throws Exception {
                String ptime = TimeUtil.formatDate(info.getPtime());
                info.setPtime(ptime);
                return info;
            }
        }).distinct().sorted(new Comparator<NewsInfo>() {
            @Override
            public int compare(NewsInfo info, NewsInfo t1) {
                return t1.getPtime().compareTo(info.getPtime());
            }
        }).compose(RetrofitClient.schedulersTransformer);//线程调度
    }
}
