package com.deshine.huishu.app.signOrderUpload.presenter.impl;

import android.content.Context;

import com.deshine.huishu.app.app.AppApplication;
import com.deshine.huishu.app.app.AppConstant;
import com.deshine.huishu.app.base.OnHttpCallBack;
import com.deshine.huishu.app.base.request.BaseRequest;
import com.deshine.huishu.app.base.response.BaseResponse;
import com.deshine.huishu.app.bean.NewsInfo;
import com.deshine.huishu.app.client.RxDisposeManager;
import com.deshine.huishu.app.common.util.CommonCallBackFaild;
import com.deshine.huishu.app.news.fragment.NewsFragment;
import com.deshine.huishu.app.news.model.bean.NewsChannelTable;
import com.deshine.huishu.app.news.model.impl.NewsModelImpl;
import com.deshine.huishu.app.news.presenter.NewPresenter;
import com.deshine.huishu.app.signOrderUpload.fragment.SignOrderListFragment;
import com.deshine.huishu.app.signOrderUpload.model.SignOrderUploadModel;
import com.deshine.huishu.app.signOrderUpload.model.bean.dto.FreightOrderDto;
import com.deshine.huishu.app.signOrderUpload.model.impl.SignOrderUploadModelImpl;
import com.deshine.huishu.app.signOrderUpload.presenter.SignOrderListPresenter;
import com.deshine.huishu.app.signOrderUpload.view.SignOrderListView;
import com.deshine.huishu.app.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by lvr on 2017/2/11.
 */

public class SignOrderListPresenterImpl implements SignOrderListPresenter {
    private SignOrderUploadModel signOrderUploadModel;
    private SignOrderListView signOrderListView;

    public SignOrderListPresenterImpl(SignOrderListView view) {
        this.signOrderListView = view;
        this.signOrderUploadModel = new SignOrderUploadModelImpl();
    }

    /**
     * 查询未上传的签收单
     *
     * @param pageNum
     * @param pageSize
     */
    @Override
    public void loadSignOrderList(int pageNum, int pageSize) {
        signOrderListView.loading();
        String userId = AppApplication.getAppContext()
                .getSharedPreferences(AppConstant.CACHE_DATA, Context.MODE_PRIVATE).getString(AppConstant.USER_ID, null);
        FreightOrderDto freightOrderDto = new FreightOrderDto();
        freightOrderDto.setDeliveryUserId(userId);
        freightOrderDto.setSign(0);
        BaseRequest<FreightOrderDto> request = new BaseRequest<>();
        request.setEntity(freightOrderDto);
        request.setPageNum(pageNum);
        request.setPageSize(pageSize);
        signOrderUploadModel.selectDeliverySignOrderList(request, new OnHttpCallBack<BaseResponse<List<FreightOrderDto>>>() {
            @Override
            public void onSuccessful(BaseResponse<List<FreightOrderDto>> listBaseResponse) {
                List<FreightOrderDto> freightOrderDtos = listBaseResponse.getData();
                int totalPageSize = listBaseResponse.getTotal();
                signOrderListView.stopLoading();//隐藏进度条
                signOrderListView.selectDeliverySignOrderListBack(freightOrderDtos,totalPageSize);
            }

            @Override
            public void onFaild(String errorMsg, String errorCode) {
                signOrderListView.stopLoading();//隐藏进度条
                signOrderListView.showErrorMsg(errorMsg);//显示错误信息
                CommonCallBackFaild.onFaild(errorCode);
            }
        });
    }

}
