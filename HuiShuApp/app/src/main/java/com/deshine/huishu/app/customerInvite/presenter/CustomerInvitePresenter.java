package com.deshine.huishu.app.customerInvite.presenter;

import com.deshine.huishu.app.commonAffix.bean.CommonAffix;
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
}
