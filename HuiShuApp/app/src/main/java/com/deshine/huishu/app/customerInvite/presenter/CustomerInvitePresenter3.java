package com.deshine.huishu.app.customerInvite.presenter;

import com.deshine.huishu.app.commonAffix.bean.CommonAffix;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInviteScanData;
import com.deshine.huishu.app.customerInvite.model.bean.FinanceBillDto;

import java.util.List;

public interface CustomerInvitePresenter3 {

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
}
