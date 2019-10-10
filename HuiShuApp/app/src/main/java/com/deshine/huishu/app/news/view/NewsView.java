package com.deshine.huishu.app.news.view;

import com.deshine.huishu.app.bean.NewsInfo;
import com.deshine.huishu.app.news.model.bean.NewsChannelTable;

import java.util.List;

/**
 * Created by lvr on 2017/2/7.
 */

public interface NewsView {
    void returnNewsChannel(List<NewsChannelTable> tables);
    void returnNewsList(List<NewsInfo> info);
}
