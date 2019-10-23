package com.deshine.huishu.app.http.interceptor;

import android.content.Context;
import android.text.TextUtils;

import com.deshine.huishu.app.api.ResultCode;
import com.deshine.huishu.app.app.AppApplication;
import com.deshine.huishu.app.app.AppConstant;
import com.deshine.huishu.app.cameralib.util.LogUtil;
import com.deshine.huishu.app.exception.BizServiceException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhouds
 * Created time  2018/11/5.
 * Description: 自定义拦截器、添加请求头
 * Version: V 1.0
 */
public class HttpCommonInterceptor implements Interceptor {
    private Map<String, String> mHeaderParamsMap = new HashMap<>();

    public HttpCommonInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        // 新的请求
         Request.Builder requestBuilder = oldRequest.newBuilder();
         requestBuilder.method(oldRequest.method(), oldRequest.body());
        // 添加公共参数,添加到header中
        if (mHeaderParamsMap.size() > 0) {
            for (Map.Entry<String, String> params : mHeaderParamsMap.entrySet()) {
                requestBuilder.header(params.getKey(), params.getValue());
            }
        }
        //添加公共参数拦截器
        String jwt = AppApplication.getAppContext().getSharedPreferences(AppConstant.CACHE_DATA, Context.MODE_PRIVATE).getString(AppConstant.JWT,null);
        if(TextUtils.isEmpty(jwt) && oldRequest.url().toString().indexOf("user/login")<0){
            //如果jwt为空如何跳转到登录页面（EventBus 发布消息给工作台，工作台去跳转） TODO
            new BizServiceException("JWT 信息丢失,请重新登陆", ResultCode.Biz.User.DATA_LOST_DESC);
        }else if(oldRequest.url().toString().indexOf("user/login")<0){
            requestBuilder.header(AppConstant.JWT, jwt);
            LogUtil.i("jwt:"+jwt);
        }
        Request newRequest = requestBuilder.build();
        return chain.proceed(newRequest);
    }

    public static class Builder {
        HttpCommonInterceptor mHttpCommonInterceptor;

        public Builder() {
            mHttpCommonInterceptor = new HttpCommonInterceptor();
        }

        public Builder addHeaderParams(String key, String value) {
            mHttpCommonInterceptor.mHeaderParamsMap.put(key, value);
            return this;
        }

        public Builder addHeaderParams(String key, int value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, float value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, long value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, double value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public HttpCommonInterceptor build() {
            return mHttpCommonInterceptor;
        }
    }
}