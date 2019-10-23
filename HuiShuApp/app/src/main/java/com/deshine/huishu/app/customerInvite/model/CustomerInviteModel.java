package com.deshine.huishu.app.customerInvite.model;

import com.deshine.huishu.app.base.OnHttpCallBack;
import com.deshine.huishu.app.base.request.BaseRequest;
import com.deshine.huishu.app.base.response.BaseResponse;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInviteAffix;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInviteVo;
import com.deshine.huishu.app.customerInvite.model.bean.FinanceBillDto;
import com.deshine.huishu.app.customerInvite.model.bean.FinanceBillResponse;

import java.util.Map;

public interface CustomerInviteModel {

    /**
     * 获取客户自提数据
     * @param soNo
     * @param userId
     * @param callBack
     */
    void getCustomerInvite(String soNo, String userId, OnHttpCallBack<BaseResponse<CustomerInviteVo>> callBack);

    /**
     * 客户自提出库
     */
    void customerInviteSubmit(Map<String,Object> request, OnHttpCallBack<FinanceBillResponse> callBack);

    /**
     * 签收单附件添加索引
     * @param osId
     * @param request
     */
    void uploadCustomerInviteAffix(String osId, CustomerInviteAffix request,OnHttpCallBack<BaseResponse<Integer>> callBack);
}
