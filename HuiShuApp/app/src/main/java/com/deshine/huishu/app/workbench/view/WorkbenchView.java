package com.deshine.huishu.app.workbench.view;

import com.deshine.huishu.app.workbench.model.bean.Workbench;

import java.util.List;

public interface WorkbenchView {
    /**
     * 设置显示菜单
     * @param workbenchList
     */
    void loadMenuBack(List<Workbench> workbenchList);
    void startProgressDialog();
    void stopProgressDialog();
    void showErrorMsg(String errorMsg);
    void toExit();
}
