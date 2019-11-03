package com.deshine.huishu.app.signOrderUpload.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.deshine.huishu.app.R;
import com.deshine.huishu.app.adapter.SignOrderListAdapter;
import com.deshine.huishu.app.app.AppConstant;
import com.deshine.huishu.app.base.BaseFragment;
import com.deshine.huishu.app.cameralib.util.LogUtil;
import com.deshine.huishu.app.client.RxDisposeManager;
import com.deshine.huishu.app.signOrderUpload.activity.SignOrderUpload;
import com.deshine.huishu.app.signOrderUpload.model.bean.dto.FreightOrderDto;
import com.deshine.huishu.app.signOrderUpload.presenter.SignOrderListPresenter;
import com.deshine.huishu.app.signOrderUpload.presenter.impl.SignOrderListPresenterImpl;
import com.deshine.huishu.app.signOrderUpload.view.SignOrderListView;
import com.deshine.huishu.app.utils.GesonUtil;
import com.deshine.huishu.app.utils.ToastUitl;

import java.util.List;

import butterknife.BindView;
public class SignOrderListFragment extends BaseFragment implements SignOrderListView,SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout mRefreshLayout;

    private SignOrderListPresenter mPresenter;
    private Context mContext;
    private SignOrderListAdapter mSignOrderListAdapter;

    private int pageNum=1;
    private int pageSize=25;
    private int total = 0;
    private List<FreightOrderDto> freightOrderDtos;
    public static int REQUEST_UPLOAD_CODE = 1101;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_sign_order_list;
    }

    @Override
    protected void initView() {
        mContext = getActivity();
        mPresenter = new SignOrderListPresenterImpl(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRefreshLayout.setOnRefreshListener(this);
        //设置下拉刷新的按钮的颜色
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        setLoadMoreListener();
    }
    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible) {
            mPresenter.loadSignOrderList(pageNum, pageSize);
        }else{
            RxDisposeManager.get().cancel("freightOrderList");
        }
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {
        pageNum = 1;
        pageSize = 25;
        total = 0;
        mRefreshLayout.setRefreshing(true);
        mPresenter.loadSignOrderList(pageNum, pageSize);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SignOrderListFragment.REQUEST_UPLOAD_CODE){
            onRefresh();
        }
    }

    @Override
    public void selectDeliverySignOrderListBack(List<FreightOrderDto> list, int totalPageSize) {
        freightOrderDtos = list;
        total = totalPageSize;
        if (!isFirst) {
            isFirst = true;
            mSignOrderListAdapter = new SignOrderListAdapter(mContext, freightOrderDtos, total);
            mRecyclerView.setAdapter(mSignOrderListAdapter);
            mSignOrderListAdapter.setOnItemClickListener(new SignOrderListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    //跳转到编辑页面
                    //SignOrderUpload.startAction(getActivity(),mSignOrderListAdapter.getAdapterData().get(position));
                    Bundle bundle = new Bundle();
                    bundle.putString(AppConstant.FREIGHT_ORDER_DTO, GesonUtil.getGson().toJson(mSignOrderListAdapter.getAdapterData().get(position)));
                    startActivityForResult(SignOrderUpload.class,bundle,REQUEST_UPLOAD_CODE);
                }
            });
        } else if (pageNum == 1) {
            mRefreshLayout.setRefreshing(false);
            List<FreightOrderDto> data = mSignOrderListAdapter.getAdapterData();
            data.clear();
            data.addAll(list);
            System.out.println("刷新后的数据：" + data.size());
            mSignOrderListAdapter.notifyDataSetChanged();
        } else if (pageNum > 1) {
            List<FreightOrderDto> data = mSignOrderListAdapter.getAdapterData();
            data.addAll(list);
            System.out.println("加载后的数据：" + data.size());
            mSignOrderListAdapter.notifyDataSetChanged();
        }
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
                    System.out.println("lastVisibleItem："+lastVisibleItem+" totalItemCount:"+totalItemCount+" len:"+mSignOrderListAdapter.getAdapterData().size());
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        pageNum ++;
                        if(mSignOrderListAdapter.getAdapterData().size() < total){
                            System.out.println("加载页数：" + pageNum);
                            mPresenter.loadSignOrderList(pageNum,pageSize);
                        }

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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxDisposeManager.get().cancel("newslist");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.i("onResume 被调用");
    }

    public void loading(){startProgressDialog("加载中");}
    public void stopLoading(){stopProgressDialog();}
    public void showErrorMsg(String errorMsg){
        ToastUitl.showShort(errorMsg);
    }
}
