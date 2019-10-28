package com.deshine.huishu.app.customerInvite.view;

import com.deshine.huishu.app.commonAffix.bean.CommonAffix;
import com.deshine.huishu.app.customerInvite.model.bean.FinanceBillDto;

import java.util.List;

public interface CustomerInviteView2 {
    /**
     * 更新页面
     */
    void setIdCardData(List<CommonAffix> idCardList);
    void loading();
    void stopLoading();
    void showErrorMsg(String errorMsg);
}
