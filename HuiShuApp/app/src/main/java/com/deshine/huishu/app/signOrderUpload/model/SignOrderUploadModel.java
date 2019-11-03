package com.deshine.huishu.app.signOrderUpload.model;

import com.deshine.huishu.app.base.OnHttpCallBack;
import com.deshine.huishu.app.base.request.BaseRequest;
import com.deshine.huishu.app.base.response.BaseResponse;
import com.deshine.huishu.app.commonAffix.bean.CommonAffix;
import com.deshine.huishu.app.signOrderUpload.model.bean.dto.FreightOrderDto;

import java.util.List;

public interface SignOrderUploadModel {
    /**
     * 根据出库单id 查询签收单信息
     * @param osId
     * @param driverUserId
     * @param callBack
     */
    void selectDeliverySignOrder(String osId, String driverUserId, OnHttpCallBack<BaseResponse<FreightOrderDto>> callBack);

    /**
     * 签收单图片上传
     * @param sfBillId
     * @param affixList
     * @param callBack
     */
    void addSignOrderPic(String sfBillId, List<CommonAffix> affixList, OnHttpCallBack<BaseResponse<Integer>> callBack);

    /**
     * 未上传签收单列表查询
     * @param request
     * @param callBack
     */
    void selectDeliverySignOrderList(BaseRequest<FreightOrderDto> request,OnHttpCallBack<BaseResponse<List<FreightOrderDto>>> callBack);
}
