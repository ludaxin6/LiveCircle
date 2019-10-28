package com.deshine.huishu.app.customerInvite.presenter;

import com.deshine.huishu.app.commonAffix.bean.CommonAffix;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInviteScanData;
import com.deshine.huishu.app.customerInvite.model.bean.FinanceBillDto;

import java.util.List;

public interface CustomerInvitePresenter1 {

    /**
     * 根据扫码数据查询客户自提数据
     * @param scanData
     */
    void fetchCustomerInvite(CustomerInviteScanData scanData);
    /**
     * 发送短信验证码
     * @param idCardNo
     * @param mobilePhone
     * @param expireSeconds
     */
    void sendSmsVerifyCode(String idCardNo, String mobilePhone, int expireSeconds);

    /**
     * 短信验证码校验
     * @param idCardNo
     * @param verifyCode
     */
    void checkSmsVerifyCode(String idCardNo, String verifyCode);
}
