package com.lvr.livecircle.login.model;

import android.content.Context;

import com.lvr.livecircle.base.OnHttpCallBack;
import com.lvr.livecircle.login.model.bean.UserInfo;
import com.lvr.livecircle.login.model.bean.response.UserResponse;

public interface LoginModel {
    void login(UserInfo userInfo, OnHttpCallBack<UserResponse> callBack);//登录

    void saveUserInfo(Context context, UserInfo user, String token);//登录成功就保存用户信息
}
