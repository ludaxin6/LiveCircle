package com.deshine.huishu.app.customerInvite.model.impl;

import com.deshine.huishu.app.base.OnHttpCallBack;
import com.deshine.huishu.app.client.BaseResponse;
import com.deshine.huishu.app.customerInvite.model.CustomerInviteModel;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInvite;

public class CustomerInviteModelImpl implements CustomerInviteModel {
    /**
     * 获取客户自提数据
     *
     * @param soNo
     * @param userId
     * @param callBack
     */
    @Override
    public void getCustomerInvite(String soNo, String userId, OnHttpCallBack<BaseResponse> callBack) {

    }

    /**
     * demo 测试用
     *
     * @param soNo
     * @param userId
     * @return
     */
    @Override
    public CustomerInvite getCustomerInvite(String soNo, String userId) {
        CustomerInvite customerInvite = new CustomerInvite();
        customerInvite.setConsigneeName("张震");
        customerInvite.setConsigneeMobilePhone("15000447608");
        customerInvite.setConsigneeAddress("客户自提 513436200010144065 张震 15000447608");
        customerInvite.setIdCardNo("513436200010144065");
        customerInvite.setSignOrderCount(5);
        return customerInvite;
    }
}
