package com.deshine.huishu.app.workbench.model.impl;

import com.deshine.huishu.app.app.AppApplication;
import com.deshine.huishu.app.app.AppConstant;
import com.deshine.huishu.app.client.RetrofitClient;
import com.deshine.huishu.app.news.model.bean.NewsChannelTable;
import com.deshine.huishu.app.utils.ACache;
import com.deshine.huishu.app.workbench.db.WorkbenchTableManager;
import com.deshine.huishu.app.workbench.model.WorkbenchModel;
import com.deshine.huishu.app.workbench.model.bean.Workbench;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class WorkbenchModelImpl implements WorkbenchModel {
    //加载所有工作台信息
    @Override
    public Observable<List<Workbench>> lodeAllWorkbenchItem(final boolean isReload) {
        return Observable.create(new ObservableOnSubscribe<List<Workbench>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Workbench>> e) throws Exception {
                if(isReload){
                    ACache.get(AppApplication.getAppContext()).remove(AppConstant.WORKBENCH_ALL);
                }
                ArrayList<Workbench> workbenchList = (ArrayList<Workbench>) ACache.get(AppApplication.getAppContext()).getAsObject(AppConstant.WORKBENCH_ALL);
                if(workbenchList==null){
                    workbenchList = (ArrayList<Workbench>) WorkbenchTableManager.loadAll();
                    ACache.get(AppApplication.getAppContext()).put(AppConstant.WORKBENCH_ALL,workbenchList);

                }
                e.onNext(workbenchList);
                e.onComplete();
            }

        }).compose(RetrofitClient.schedulersTransformer);
    }
}
