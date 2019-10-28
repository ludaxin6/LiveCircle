package com.deshine.huishu.app.customerInvite.view;

import com.deshine.huishu.app.commonAffix.bean.CommonAffix;
import com.deshine.huishu.app.customerInvite.model.bean.FinanceBillDto;

import java.util.List;

public interface CustomerInviteView1 {
    /**
     * 更新页面
     */
    void updateInfo(FinanceBillDto dto);
    void sendSmsVerifyCodeBack();
    void checkSmsVerifyCodeBack(boolean flage);
    void loading();
    void stopLoading();
    void showErrorMsg(String errorMsg);

}
