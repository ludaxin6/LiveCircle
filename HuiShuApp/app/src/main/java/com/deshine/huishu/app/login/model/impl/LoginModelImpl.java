package com.deshine.huishu.app.login.model.impl;

import android.content.Context;
import android.util.Log;

import com.deshine.huishu.app.api.CrmDgcsApiService;
import com.deshine.huishu.app.cameralib.util.LogUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.deshine.huishu.app.api.ResultCode;
import com.deshine.huishu.app.app.AppConstant;
import com.deshine.huishu.app.base.OnHttpCallBack;
import com.deshine.huishu.app.login.model.LoginModel;
import com.deshine.huishu.app.login.model.bean.UserInfo;
import com.deshine.huishu.app.login.model.bean.request.LoginRequest;
import com.deshine.huishu.app.login.model.bean.response.UserResponse;
import com.deshine.huishu.app.utils.GesonUtil;
import com.deshine.huishu.app.http.CommonRetrofitClientUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginModelImpl implements LoginModel {
    @Override
    public void login(final UserInfo userInfo, final OnHttpCallBack<UserResponse> callBack) {
        //登录的网络请求
        LoginRequest request = new LoginRequest();
        request.setUserInfo(userInfo);
        request.setClientType(userInfo.getClientType());
        request.setTokenId("null");
        CommonRetrofitClientUtil.newInstence(CrmDgcsApiService.BASE_URL)//实例化Retrofit对象
                .create(CrmDgcsApiService.class)//创建Rxjava---->LoginService对象
                .login(request)//调用登录的接口
                .subscribeOn(Schedulers.newThread())//在新线程中执行登录请求
                .observeOn(AndroidSchedulers.mainThread())//在主线程中执行
                .subscribe(new Observer<UserResponse>() {//网络(登录)请求回调
                    private Disposable mDisposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(UserResponse loginResultUserHttpResult) {
                        if (ResultCode.SUCCESS.equals(loginResultUserHttpResult.getResultCode())) {
                            callBack.onSuccessful(loginResultUserHttpResult);//登录成功------获取完数据,返回给P---P获取到数据之后就将数据交回给V
                        } else {
                            callBack.onFaild(loginResultUserHttpResult.getResultDesc(),loginResultUserHttpResult.getResultCode());//登录失败
                        }
                        mDisposable.dispose();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("LoginModelImpl", e.getMessage()+ "--");
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
        //CommonRetrofitClientUtil.getOkHttpClient().interceptors().clear();
    }

    @Override
    public void saveUserInfo(Context context, UserInfo user, String token) {
        Log.e("LoginModelImpl","开始保存用户信息"+user.getUserName());
        context.getSharedPreferences(AppConstant.CACHE_DATA, Context.MODE_PRIVATE).edit()
                .putString(AppConstant.JWT, token)
                .putString(AppConstant.USER_ID, user.getUserId())
                .putString(AppConstant.USER_NAME, user.getUserName())
                .putString(AppConstant.USER_INFO_JSON, GesonUtil.getGson().toJson(user))
                .commit();
    }

    @Override
    public void removeUserInfo(Context context) {
        Log.e("LoginModelImpl","开始移除用户信息");
        context.getSharedPreferences(AppConstant.CACHE_DATA, Context.MODE_PRIVATE).edit()
                .remove(AppConstant.JWT)
                .remove(AppConstant.USER_ID)
                .remove(AppConstant.USER_NAME)
                .remove(AppConstant.USER_INFO_JSON)
                .commit();
    }
}
