package com.deshine.huishu.app.login.view;

import com.deshine.huishu.app.login.model.bean.UserInfo;

public interface LoginView {
    UserInfo getUserLoginInfo();//获取用户登录信息
    void showProgress();
    void hideProgress();
    void toMain();
    void showErrorMsg(String msg);
    void showInfo(String info);
}
