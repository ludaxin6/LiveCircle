package com.deshine.huishu.app.news.model;

import com.deshine.huishu.app.bean.NewsInfo;
import com.deshine.huishu.app.news.model.bean.NewsChannelTable;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by lvr on 2017/2/7.
 */

public interface NewsModel {
    Observable<List<NewsChannelTable>> requestNewsChannels();
    Observable<NewsInfo> requestNewsList(String newsType, final String id, int startPage);
}
