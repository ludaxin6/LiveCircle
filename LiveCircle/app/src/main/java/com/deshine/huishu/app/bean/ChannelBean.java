package com.deshine.huishu.app.bean;

import com.deshine.huishu.app.news.model.bean.NewsChannelTable;

import java.util.List;

/**
 * Created by lvr on 2017/2/14.
 */

//通知频道变换的Event类
public class ChannelBean {
    public List<NewsChannelTable> mList;
    public int fromPosition;
    public int toPosition;
    public ChannelBean(List<NewsChannelTable> list) {
        mList = list;

    }

    public List<NewsChannelTable> getList() {
        return mList;
    }


    public void setList(List<NewsChannelTable> list) {
        mList = list;
    }
}
