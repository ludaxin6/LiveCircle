package com.deshine.huishu.app.customerInvite.model;

import com.deshine.huishu.app.base.OnHttpCallBack;
import com.deshine.huishu.app.client.BaseResponse;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInvite;

public interface CustomerInviteModel {

    /**
     * 获取客户自提数据
     * @param soNo
     * @param userId
     * @param callBack
     */
    void getCustomerInvite(String soNo, String userId, OnHttpCallBack<BaseResponse> callBack);

    /**
     * demo 测试用
     * @param soNo
     * @param userId
     * @return
     */
    CustomerInvite getCustomerInvite(String soNo, String userId);
}
