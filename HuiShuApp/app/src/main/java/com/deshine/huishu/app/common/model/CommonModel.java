package com.deshine.huishu.app.common.model;

import com.deshine.huishu.app.base.OnHttpCallBack;
import com.deshine.huishu.app.customerInvite.model.bean.ResultFile;

import java.io.File;
import java.util.List;

public interface CommonModel {
    /**
     * 文件上传
     * @param files
     * @param callBack
     */
    void uploadFile(List<File> files, OnHttpCallBack<List<ResultFile>> callBack);
}
