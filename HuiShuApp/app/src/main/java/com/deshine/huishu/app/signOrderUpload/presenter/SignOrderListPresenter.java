package com.deshine.huishu.app.signOrderUpload.presenter;

/**
 * Created by lvr on 2017/2/7.
 */

public interface SignOrderListPresenter {
    /**
     * 查询未上传的签收单
     * @param pageNum
     * @param pageSize
     */
    void loadSignOrderList(final int pageNum,final int pageSize);
}
