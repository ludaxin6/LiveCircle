package com.deshine.huishu.app.signOrderUpload.view;

import com.deshine.huishu.app.signOrderUpload.model.bean.dto.FreightOrderDto;

public interface SignOrderUploadView {

    void fetchSignOrderByOsIdBack(FreightOrderDto freightOrderDto);
    void loading();
    void stopLoading();
    void showErrorMsg(String errorMsg);
    void toSuccessPage();
}
