package com.deshine.huishu.app.signOrderUpload.model.impl;

import android.content.Context;

import com.deshine.huishu.app.api.CrmDgcsApiService;
import com.deshine.huishu.app.api.ResultCode;
import com.deshine.huishu.app.app.AppApplication;
import com.deshine.huishu.app.base.OnHttpCallBack;
import com.deshine.huishu.app.base.request.BaseRequest;
import com.deshine.huishu.app.base.response.BaseResponse;
import com.deshine.huishu.app.cameralib.util.LogUtil;
import com.deshine.huishu.app.client.RxDisposeManager;
import com.deshine.huishu.app.commonAffix.bean.CommonAffix;
import com.deshine.huishu.app.http.CommonRetrofitClientUtil;
import com.deshine.huishu.app.signOrderUpload.model.SignOrderUploadModel;
import com.deshine.huishu.app.signOrderUpload.model.bean.dto.FreightOrderDto;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SignOrderUploadModelImpl implements SignOrderUploadModel {
    /**
     * 根据出库单id 查询签收单信息
     *
     * @param osId
     * @param driverUserId
     * @param callBack
     */
    @Override
    public void selectDeliverySignOrder(String osId, String driverUserId, final OnHttpCallBack<BaseResponse<FreightOrderDto>> callBack) {
        CommonRetrofitClientUtil.newInstence(CrmDgcsApiService.BASE_URL)//实例化Retrofit对象
                .create(CrmDgcsApiService.class)//创建Rxjava---->LoginService对象
                .selectDeliverySignOrder(osId,driverUserId)
                .subscribeOn(Schedulers.newThread())//在新线程中执行登录请求
                .observeOn(AndroidSchedulers.mainThread())//在主线程中执行
                .subscribe(new Observer<BaseResponse<FreightOrderDto>>() {
                    private Disposable mDisposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(BaseResponse<FreightOrderDto> response) {
                        if (ResultCode.SUCCESS.equals(response.getResultCode())) {
                            callBack.onSuccessful(response);
                        } else {
                            callBack.onFaild(response.getResultDesc(),response.getResultCode());
                        }
                        mDisposable.dispose();
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
                        mDisposable.dispose();
                    }
                    @Override
                    public void onComplete() {
                        //完成的时候调用
                    }
                });
    }

    /**
     * 签收单图片上传
     *
     * @param sfBillId
     * @param affixList
     * @param callBack
     */
    @Override
    public void addSignOrderPic(String sfBillId, List<CommonAffix> affixList, final OnHttpCallBack<BaseResponse<Integer>> callBack) {
        CommonRetrofitClientUtil.newInstence(CrmDgcsApiService.BASE_URL)//实例化Retrofit对象
                .create(CrmDgcsApiService.class)//创建Rxjava---->LoginService对象
                .addSignOrderPic(sfBillId,affixList)
                .subscribeOn(Schedulers.newThread())//在新线程中执行登录请求
                .observeOn(AndroidSchedulers.mainThread())//在主线程中执行
                .subscribe(new Observer<BaseResponse<Integer>>() {
                    private Disposable mDisposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(BaseResponse<Integer> response) {
                        if (ResultCode.SUCCESS.equals(response.getResultCode())) {
                            callBack.onSuccessful(response);
                        } else {
                            callBack.onFaild(response.getResultDesc(),response.getResultCode());
                        }
                        mDisposable.dispose();
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
                        mDisposable.dispose();
                    }
                    @Override
                    public void onComplete() {
                        //完成的时候调用
                    }
                });
    }

    /**
     * 未上传签收单列表查询
     *
     * @param request
     * @param callBack
     */
    @Override
    public void selectDeliverySignOrderList(BaseRequest<FreightOrderDto> request, final OnHttpCallBack<BaseResponse<List<FreightOrderDto>>> callBack) {
        CommonRetrofitClientUtil.newInstence(CrmDgcsApiService.BASE_URL)//实例化Retrofit对象
                .create(CrmDgcsApiService.class)//创建Rxjava---->LoginService对象
                .selectDeliverySignOrderList(request)
                .subscribeOn(Schedulers.newThread())//在新线程中执行登录请求
                .observeOn(AndroidSchedulers.mainThread())//在主线程中执行
                .subscribe(new Observer<BaseResponse<List<FreightOrderDto>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        RxDisposeManager.get().add("freightOrderList", d);
                    }

                    @Override
                    public void onNext(BaseResponse<List<FreightOrderDto>> response) {
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
