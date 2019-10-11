package com.deshine.huishu.app.workbench.model;


import com.deshine.huishu.app.workbench.model.bean.Workbench;

import java.util.List;

import io.reactivex.Observable;

public interface WorkbenchModel {
    Observable<List<Workbench>> lodeAllWorkbenchItem(boolean isReload);
}
