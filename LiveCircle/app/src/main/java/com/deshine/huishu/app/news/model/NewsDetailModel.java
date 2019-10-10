package com.deshine.huishu.app.news.model;

import com.deshine.huishu.app.news.model.bean.NewsDetail;

import io.reactivex.Observable;

/**
 * Created by lvr on 2017/2/11.
 */

public interface NewsDetailModel {
    Observable<NewsDetail> getDetailNews(String postId);
}
