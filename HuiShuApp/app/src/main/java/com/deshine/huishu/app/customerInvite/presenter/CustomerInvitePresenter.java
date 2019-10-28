package com.deshine.huishu.app.customerInvite.presenter;

import com.deshine.huishu.app.commonAffix.bean.CommonAffix;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInviteAffix;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInviteScanData;
import com.deshine.huishu.app.customerInvite.model.bean.FinanceBillDto;

import java.util.List;

public interface CustomerInvitePresenter {

    /**
     * 根据扫码数据查询客户自提数据
     * @param scanData
     */
    void fetchCustomerInvite(CustomerInviteScanData scanData);

    /**
     * 身份证上传
     */
    void uploadIdCardPhoto(String fileDir);

    /**
     * 上传签收单到文件系统
     */
    void uploadSignOrderPhoto(List<CommonAffix> commonAffixes);

    /**
     * 客户自提出库
     */
    void customerInviteSubmit(FinanceBillDto dto);

    /**
     * 根据出库id,客户自提附件信息，条件附件索引记录
     */
    void uploadCustomerInviteAffix(String osId, FinanceBillDto dto);

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
