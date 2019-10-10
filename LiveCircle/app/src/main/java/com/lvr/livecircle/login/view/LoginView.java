package com.lvr.livecircle.login.view;

import com.lvr.livecircle.login.model.bean.UserInfo;

public interface LoginView {
    UserInfo getUserLoginInfo();//获取用户登录信息
    void showProgress();
    void hideProgress();
    void toMain();
    void showErrorMsg(String msg);
    void showInfo(String info);
}
