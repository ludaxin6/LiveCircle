package com.deshine.huishu.app.customerInvite.presenter;

import com.deshine.huishu.app.commonAffix.bean.CommonAffix;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInviteScanData;
import com.deshine.huishu.app.customerInvite.model.bean.FinanceBillDto;

import java.util.List;

public interface CustomerInvitePresenter2 {

    /**
     * 身份证上传
     */
    void uploadIdCardPhoto(String fileDir);
}
