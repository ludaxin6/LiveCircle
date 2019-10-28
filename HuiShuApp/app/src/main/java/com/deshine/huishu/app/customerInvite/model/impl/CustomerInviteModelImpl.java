package com.deshine.huishu.app.customerInvite.model.impl;


import com.deshine.huishu.app.api.CrmDgcsApiService;
import com.deshine.huishu.app.api.ResultCode;
import com.deshine.huishu.app.base.OnHttpCallBack;
import com.deshine.huishu.app.base.response.BaseResponse;
import com.deshine.huishu.app.cameralib.util.LogUtil;
import com.deshine.huishu.app.customerInvite.model.CustomerInviteModel;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInviteAffix;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInviteVo;
import com.deshine.huishu.app.customerInvite.model.bean.FinanceBillResponse;
import com.deshine.huishu.app.http.CommonRetrofitClientUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CustomerInviteModelImpl implements CustomerInviteModel {
    /**
     * 获取客户自提数据
     *
     * @param soNo
     * @param userId
     * @param callBack
     */
    @Override
    public void getCustomerInvite(String soNo, String userId, final OnHttpCallBack<BaseResponse<CustomerInviteVo>> callBack) {
        CommonRetrofitClientUtil.newInstence(CrmDgcsApiService.BASE_URL)//实例化Retrofit对象
                .create(CrmDgcsApiService.class)//创建Rxjava---->LoginService对象
                .getPsData4Os(soNo, userId)//调用登录的接口
                .subscribeOn(Schedulers.newThread())//在新线程中执行登录请求
                .observeOn(AndroidSchedulers.mainThread())//在主线程中执行
                .subscribe(new Observer<BaseResponse<CustomerInviteVo>>() {//网络(登录)请求回调
                    private Disposable mDisposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(BaseResponse<CustomerInviteVo> baseResponse) {
                        if (ResultCode.SUCCESS.equals(baseResponse.getResultCode())) {
                            callBack.onSuccessful(baseResponse);//登录成功------获取完数据,返回给P---P获取到数据之后就将数据交回给V
                        } else {
                            callBack.onFaild(baseResponse.getResultDesc(),baseResponse.getResultCode());//登录失败
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
     * 客户自提出库
     */
    @Override
    public void customerInviteSubmit(Map<String,Object> request, final OnHttpCallBack<FinanceBillResponse> callBack) {
        CommonRetrofitClientUtil.newInstence(CrmDgcsApiService.BASE_URL)//实例化Retrofit对象
                .create(CrmDgcsApiService.class)//创建Rxjava---->LoginService对象
                .addOs4PtBillFromPs(request)//调用客户自提出库
                .subscribeOn(Schedulers.newThread())//在新线程中执行登录请求
                .observeOn(AndroidSchedulers.mainThread())//在主线程中执行
                .subscribe(new Observer<FinanceBillResponse>() {
                    private Disposable mDisposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(FinanceBillResponse response) {
                        if (ResultCode.SUCCESS.equals(response.getResultCode())) {
                            callBack.onSuccessful(response);
                        } else {
                            callBack.onFaild(response.getResultDesc(),response.getResultCode());//登录失败
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
     * 签收单附件添加索引
     *
     * @param osId
     * @param request
     */
    @Override
    public void uploadCustomerInviteAffix(String osId, CustomerInviteAffix request,final OnHttpCallBack<BaseResponse<Integer>> callBack) {
        CommonRetrofitClientUtil.newInstence(CrmDgcsApiService.BASE_URL)//实例化Retrofit对象
                .create(CrmDgcsApiService.class)//创建Rxjava---->LoginService对象
                .saveCustomerInviteAffix(osId,request)//调用客户自提出库
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
                            callBack.onFaild(ResultCode.Base.Affix.AFFIX_UPLOAD_ERROR_DESC, ResultCode.Base.Affix.AFFIX_UPLOAD_ERROR);//登录失败
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
     * 发送短信验证码
     *
     * @param idCardNo
     * @param mobilePhone
     * @param expireSeconds
     */
    @Override
    public void sendSmsVerifyCode(String idCardNo, String mobilePhone, int expireSeconds,final OnHttpCallBack<BaseResponse<Boolean>> callBack) {
        CommonRetrofitClientUtil.newInstence(CrmDgcsApiService.BASE_URL)//实例化Retrofit对象
                .create(CrmDgcsApiService.class)//创建Rxjava---->LoginService对象
                .sendSmsVerifyCode(idCardNo,mobilePhone,expireSeconds)
                .subscribeOn(Schedulers.newThread())//在新线程中执行登录请求
                .observeOn(AndroidSchedulers.mainThread())//在主线程中执行
                .subscribe(new Observer<BaseResponse<Boolean>>() {
                    private Disposable mDisposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(BaseResponse<Boolean> response) {
                        if (ResultCode.SUCCESS.equals(response.getResultCode())) {
                            callBack.onSuccessful(response);
                        } else {
                            callBack.onFaild(response.getResultDesc(),response.getResultCode());//登录失败
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
     * 短信验证码校验
     *
     * @param idCardNo
     * @param verifyCode
     * @param callBack
     */
    @Override
    public void checkSmsVerifyCode(String idCardNo, String verifyCode, final OnHttpCallBack<BaseResponse<Boolean>> callBack) {
        CommonRetrofitClientUtil.newInstence(CrmDgcsApiService.BASE_URL)//实例化Retrofit对象
                .create(CrmDgcsApiService.class)//创建Rxjava---->LoginService对象
                .checkSmsVerifyCode(idCardNo,verifyCode)
                .subscribeOn(Schedulers.newThread())//在新线程中执行登录请求
                .observeOn(AndroidSchedulers.mainThread())//在主线程中执行
                .subscribe(new Observer<BaseResponse<Boolean>>() {
                    private Disposable mDisposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(BaseResponse<Boolean> response) {
                        if (ResultCode.SUCCESS.equals(response.getResultCode())) {
                            callBack.onSuccessful(response);
                        } else {
                            callBack.onFaild(response.getResultDesc(),response.getResultCode());//登录失败
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
}
