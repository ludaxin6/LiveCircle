package com.deshine.huishu.app.workbench.model;


import com.deshine.huishu.app.base.OnHttpCallBack;
import com.deshine.huishu.app.base.response.BaseResponse;
import com.deshine.huishu.app.workbench.model.bean.Menu;
import com.deshine.huishu.app.workbench.model.bean.Workbench;

import java.util.List;

import io.reactivex.Observable;

public interface WorkbenchModel {
    Observable<List<Workbench>> lodeAllWorkbenchItem(boolean isReload);

    /**
     * 查询工作台菜单
     * @param userId
     * @param callBack
     */
    void fetchMenuList(String userId,final OnHttpCallBack<BaseResponse<List<Menu>>> callBack);
}
