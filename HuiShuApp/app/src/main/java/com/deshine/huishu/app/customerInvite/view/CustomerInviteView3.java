package com.deshine.huishu.app.customerInvite.view;

import com.deshine.huishu.app.commonAffix.bean.CommonAffix;
import com.deshine.huishu.app.customerInvite.model.bean.FinanceBillDto;

import java.util.List;

public interface CustomerInviteView3 {
    void setSignOrderPhoto(List<CommonAffix> affixList);
    void loading();
    void stopLoading();
    void showErrorMsg(String errorMsg);
    void toSuccessPage();
}
