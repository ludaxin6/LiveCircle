package com.deshine.huishu.app.news.view;

import com.deshine.huishu.app.news.model.bean.NewsChannelTable;

import java.util.List;

/**
 * Created by lvr on 2017/2/14.
 */

public interface NewsChannelView {
    void returnMineNewsChannels(List<NewsChannelTable> newsChannelsMine);
    void returnMoreNewsChannels(List<NewsChannelTable> newsChannelsMore);
}
