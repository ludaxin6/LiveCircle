package com.deshine.huishu.app.signOrderUpload.presenter;

import com.deshine.huishu.app.commonAffix.bean.CommonAffix;

import java.util.List;

public interface SignOrderUploadPresenter {

    /**
     * 根据出库单id 查询签收单
     * @param osId
     */
    void fetchSignOrderByOsId(String osId);

    /**
     * 签收单上传
     * @param sfBillId
     * @param sfBillNo
     * @param affixList
     */
    void signOrderUpload(String sfBillId, String sfBillNo, List<CommonAffix> affixList);

    /**
     * 签收单上传提交
     * @param sfBillId
     * @param affixList
     */
    void signOrderUploadSubmit(String sfBillId, List<CommonAffix> affixList);
}
