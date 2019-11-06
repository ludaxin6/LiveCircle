package com.deshine.huishu.app.workbench.model.impl;

import com.deshine.huishu.app.api.CrmDgcsApiService;
import com.deshine.huishu.app.api.ResultCode;
import com.deshine.huishu.app.app.AppApplication;
import com.deshine.huishu.app.app.AppConstant;
import com.deshine.huishu.app.base.OnHttpCallBack;
import com.deshine.huishu.app.base.response.BaseResponse;
import com.deshine.huishu.app.cameralib.util.LogUtil;
import com.deshine.huishu.app.client.RetrofitClient;
import com.deshine.huishu.app.client.RxDisposeManager;
import com.deshine.huishu.app.http.CommonRetrofitClientUtil;
import com.deshine.huishu.app.news.model.bean.NewsChannelTable;
import com.deshine.huishu.app.utils.ACache;
import com.deshine.huishu.app.workbench.db.WorkbenchTableManager;
import com.deshine.huishu.app.workbench.model.WorkbenchModel;
import com.deshine.huishu.app.workbench.model.bean.Menu;
import com.deshine.huishu.app.workbench.model.bean.Workbench;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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

    /**
     * 查询工作台菜单
     *
     * @param userId
     * @param callBack
     */
    @Override
    public void fetchMenuList(String userId, final OnHttpCallBack<BaseResponse<List<Menu>>> callBack) {
        CommonRetrofitClientUtil.newInstence(CrmDgcsApiService.BASE_URL)//实例化Retrofit对象
                .create(CrmDgcsApiService.class)//创建Rxjava---->LoginService对象
                .fetchMenuList(userId)
                .subscribeOn(Schedulers.newThread())//在新线程中执行登录请求
                .observeOn(AndroidSchedulers.mainThread())//在主线程中执行
                .subscribe(new Observer<BaseResponse<List<Menu>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        RxDisposeManager.get().add("freightOrderList", d);
                    }

                    @Override
                    public void onNext(BaseResponse<List<Menu>> response) {
                        if (ResultCode.SUCCESS.equals(response.getResultCode())) {
                            callBack.onSuccessful(response);
                        } else {
                            callBack.onFaild(response.getResultDesc(),response.getResultCode());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(e.getMessage()+ "--");
                        e.printStackTrace();
                        //失败的时候调用-----一下可以忽略 直接 callBack.onFaild("请求失败");
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            //httpException.response().errorBody().string()
                            int code = httpException.code();
                            if (code == 500 || code == 404) {
                                callBack.onFaild(ResultCode.Base.System.SERVER_ERROR_DESC,ResultCode.Base.System.SERVER_ERROR);
                            }
                        } else if (e instanceof ConnectException) {
                            callBack.onFaild(ResultCode.Base.Network.NETWORK_DISCONNECTION_DESC,ResultCode.Base.Network.NETWORK_DISCONNECTION);
                        } else if (e instanceof SocketTimeoutException) {
                            callBack.onFaild(ResultCode.Base.Network.NETWORK_TIMEOUT_DESC,ResultCode.Base.Network.NETWORK_TIMEOUT);
                        } else {
                            LogUtil.e(e.getMessage());
                            callBack.onFaild("发生未知错误" + e.getMessage(),ResultCode.Base.System.AN_UNKNOWN_ERROR);
                        }
                    }
                    @Override
                    public void onComplete() {
                        //完成的时候调用
                    }
                });
    }
}
