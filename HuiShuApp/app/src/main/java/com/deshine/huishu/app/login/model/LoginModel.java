package com.deshine.huishu.app.login.model;

import android.content.Context;

import com.deshine.huishu.app.base.OnHttpCallBack;
import com.deshine.huishu.app.login.model.bean.UserInfo;
import com.deshine.huishu.app.login.model.bean.response.UserResponse;

public interface LoginModel {
    void login(UserInfo userInfo, OnHttpCallBack<UserResponse> callBack);//登录

    void saveUserInfo(Context context, UserInfo user, String token);//登录成功就保存用户信息

    void removeUserInfo(Context context);
}
