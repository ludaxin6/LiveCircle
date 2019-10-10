package com.deshine.huishu.app.login.model.impl;

import android.content.Context;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.deshine.huishu.app.api.ApiService;
import com.deshine.huishu.app.api.ResultCode;
import com.deshine.huishu.app.app.AppConstant;
import com.deshine.huishu.app.base.OnHttpCallBack;
import com.deshine.huishu.app.login.model.LoginModel;
import com.deshine.huishu.app.login.model.bean.UserInfo;
import com.deshine.huishu.app.login.model.bean.request.LoginRequest;
import com.deshine.huishu.app.login.model.bean.response.UserResponse;
import com.deshine.huishu.app.utils.GesonUtil;
import com.deshine.huishu.app.utils.RetrofitUtils;

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
        RetrofitUtils.newInstence(ApiService.BASE_URL)//实例化Retrofit对象
                .create(ApiService.class)//创建Rxjava---->LoginService对象
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
                            callBack.onFaild("用户名或密码错误!");//登录失败
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
                                callBack.onFaild("服务器出错");
                            }
                        } else if (e instanceof ConnectException) {
                            callBack.onFaild("网络断开,请打开网络!");
                        } else if (e instanceof SocketTimeoutException) {
                            callBack.onFaild("网络连接超时!!");
                        } else {
                            Log.e("LoginModelImpl", e.getMessage());
                            callBack.onFaild("发生未知错误" + e.getMessage());
                        }
                        mDisposable.dispose();
                    }
                    @Override
                    public void onComplete() {
                        //完成的时候调用
                    }
                });
    }

    @Override
    public void saveUserInfo(Context context, UserInfo user, String token) {
        Log.e("LoginModelImpl","开始保存用户信息"+user.getUserName());
        context.getSharedPreferences(AppConstant.CACHE_DATA, Context.MODE_PRIVATE).edit()
                .putString(AppConstant.JWT, token)
                .putString(AppConstant.USER_INFO_JSON, GesonUtil.getGson().toJson(user))
                .commit();
    }
}
