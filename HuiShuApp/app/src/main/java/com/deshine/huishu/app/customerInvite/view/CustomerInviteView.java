package com.deshine.huishu.app.customerInvite.view;

import com.deshine.huishu.app.commonAffix.bean.CommonAffix;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInvite;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInviteVo;
import com.deshine.huishu.app.customerInvite.model.bean.FinanceBillDto;
import com.deshine.huishu.app.customerInvite.model.bean.ResultFile;

import java.util.List;

public interface CustomerInviteView {
    /**
     * 更新页面
     */
    void updateInfo(FinanceBillDto dto);
    void setIdCardData(List<CommonAffix> idCardList);
    void setSignOrderPhoto(List<CommonAffix> affixList);
    void loading();
    void stopLoading();
    void showErrorMsg(String errorMsg);
    void toSuccessPage();
}
