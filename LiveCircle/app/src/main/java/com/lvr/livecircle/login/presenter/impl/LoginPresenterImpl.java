package com.lvr.livecircle.login.presenter.impl;

import com.lvr.livecircle.app.AppApplication;
import com.lvr.livecircle.base.OnHttpCallBack;
import com.lvr.livecircle.login.model.LoginModel;
import com.lvr.livecircle.login.model.bean.response.UserResponse;
import com.lvr.livecircle.login.model.impl.LoginModelImpl;
import com.lvr.livecircle.login.presenter.LoginPresenter;
import com.lvr.livecircle.login.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView mLoginView;
    private LoginModel mLoginModel;
    public LoginPresenterImpl(LoginView mView) {
        this.mLoginView = mView;
        this.mLoginModel = new LoginModelImpl();
    }
    @Override
    public void login() {
        mLoginView.showProgress(); //显示登录进度条
        final int i = 0;
        mLoginModel.login(mLoginView.getUserLoginInfo(), new OnHttpCallBack<UserResponse>() {
            @Override
            public void onSuccessful(UserResponse tokenResult) {
                mLoginView.hideProgress();//隐藏进度条
                mLoginView.toMain();//跳转到主页面
                mLoginModel.saveUserInfo(AppApplication.getAppContext(), tokenResult.getUserInfo(), tokenResult.getJwt());//保存用户数据
                mLoginView.showInfo("登录成功");//提示用户登录成功
            }

            @Override
            public void onFaild(String errorMsg) {
                mLoginView.hideProgress();//隐藏进度条
                mLoginView.showErrorMsg(errorMsg);//登录失败  显示错误信息
            }
        });
    }
}
