package com.deshine.huishu.app.customerInvite.presenter.impl;

import com.deshine.huishu.app.base.OnHttpCallBack;
import com.deshine.huishu.app.client.BaseResponse;
import com.deshine.huishu.app.customerInvite.model.CustomerInviteModel;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInvite;
import com.deshine.huishu.app.customerInvite.model.impl.CustomerInviteModelImpl;
import com.deshine.huishu.app.customerInvite.presenter.CustomerInvitePresenter;
import com.deshine.huishu.app.customerInvite.view.CustomerInviteView;

public class CustomerInvitePresenterImpl implements CustomerInvitePresenter {
    private CustomerInviteModel customerInviteModel;
    private CustomerInviteView customerInviteView;
    public CustomerInvitePresenterImpl(CustomerInviteView view){
        customerInviteView = view;
        customerInviteModel = new CustomerInviteModelImpl();
    }
    /**
     * 获取客户自提信息
     *
     * @param soNo
     * @param userId
     */
    @Override
    public void fetchCustomerInvite(String soNo, String userId) {
        CustomerInvite customerInvite = customerInviteModel.getCustomerInvite(soNo, userId);
        customerInviteView.updateInfo(customerInvite);
    }
}
