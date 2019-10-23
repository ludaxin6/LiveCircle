package com.deshine.huishu.app.common.util;

import android.view.View;

import com.deshine.huishu.app.api.ResultCode;
import com.deshine.huishu.app.app.AppManager;
import com.deshine.huishu.app.base.BaseActivity;
import com.deshine.huishu.app.login.LoginActivity;

public class CommonCallBackFaild {
    public static void onFaild(String resultCode){
        if(ResultCode.Base.Auth.TOKEN_EXPIRE.equals(resultCode) ||
        ResultCode.Base.Auth.INVALID_TOKEN.equals(resultCode) ||
        ResultCode.Base.Auth.NO_AUTH.equals(resultCode) ||
        ResultCode.Base.Auth.NO_BINDING_INFO.equals(resultCode)||
        ResultCode.Biz.User.DATA_LOST.equals(resultCode)||
        ResultCode.Biz.User.LOGIN_ERROR.equals(resultCode)){
            //跳转到登录页面
            LoginActivity.startAction(AppManager.getAppManager().currentActivity());
        }
    }
}
