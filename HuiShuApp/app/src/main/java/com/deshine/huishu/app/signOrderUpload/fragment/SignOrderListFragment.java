package com.deshine.huishu.app.signOrderUpload.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.deshine.huishu.app.R;;
import com.deshine.huishu.app.adapter.NewsListAdapter;
import com.deshine.huishu.app.base.BaseFragment;
import com.deshine.huishu.app.bean.NewsInfo;

import java.util.List;

import butterknife.BindView;

public class SignOrderListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout mRefreshLayout;

    //当前加载新闻页数
    private int cur_news_page = 0;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_sign_order_list;
    }

    @Override
    protected void initView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRefreshLayout.setOnRefreshListener(this);
        //设置下拉刷新的按钮的颜色
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {

    }

    public void setListInfo(List<NewsInfo> infos) {
//        mInfos = infos;
//        if (!isFirst) {
//            isFirst = true;
//            mNewsListAdapter = new NewsListAdapter(mContext, mInfos, mTables);
//            mNewsListAdapter.setNewsChannelListener(this);
//            mRecyclerView.setAdapter(mNewsListAdapter);
//        } else if (cur_news_page == 0) {
//            mRefreshLayout.setRefreshing(false);
//            List<NewsInfo> data = mNewsListAdapter.getAdapterData();
//            data.clear();
//            data.addAll(infos);
//            System.out.println("刷新后的数据：" + data.size());
//            mNewsListAdapter.notifyDataSetChanged();
//        } else if (cur_news_page > 0) {
//            infos.remove(0);
//            List<NewsInfo> data = mNewsListAdapter.getAdapterData();
//            data.addAll(infos);
//            System.out.println("加载后的数据：" + data.size());
//            mNewsListAdapter.notifyDataSetChanged();
//        }


    }
    private void setLoadMoreListener() {
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 获取最后一个完全显示的item position
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();
                    // 判断是否滚动到底部并且是向下滑动
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        cur_news_page += 20;
                        System.out.println("加载页数：" + cur_news_page);
                        //mPresenter.loadNewsListRequest(cur_news_type, cur_news_id, cur_news_page);
                    }
                }

                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isSlidingToLast = dy > 0;

            }
        });

    }
}
