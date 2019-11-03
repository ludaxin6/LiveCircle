package com.deshine.huishu.app.signOrderUpload.view;

import com.deshine.huishu.app.signOrderUpload.model.bean.dto.FreightOrderDto;

import java.util.List;

public interface SignOrderListView {

    void loading();
    void stopLoading();
    void showErrorMsg(String errorMsg);
    void selectDeliverySignOrderListBack(List<FreightOrderDto> list, int totalPageSize);
}
