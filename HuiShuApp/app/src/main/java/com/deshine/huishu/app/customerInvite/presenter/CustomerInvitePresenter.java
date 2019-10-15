package com.deshine.huishu.app.customerInvite.presenter;

public interface CustomerInvitePresenter {
    /**
     * 获取客户自提信息
     * @param soNo
     * @param userId
     */
    void fetchCustomerInvite(String soNo, String userId);
}
