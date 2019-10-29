package com.deshine.huishu.app.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.deshine.huishu.app.R;
import com.deshine.huishu.app.app.AppConstant;
import com.deshine.huishu.app.base.BaseActivity;
import com.deshine.huishu.app.login.model.bean.UserInfo;
import com.deshine.huishu.app.login.presenter.LoginPresenter;
import com.deshine.huishu.app.login.presenter.impl.LoginPresenterImpl;
import com.deshine.huishu.app.login.view.LoginView;
import com.deshine.huishu.app.utils.EncryptionUtil;
import com.deshine.huishu.app.widget.LoadingTip;
import com.deshine.huishu.app.workbench.view.WorkbenchActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录页面
 * (看例子之前看一遍下面直白的解释,看完之后再看一遍就更明白MVP模式了)
 * --------M层   对P层传递过来的userInfo进行登录(网络请求)判断,处理完成之后将处理结果回调给P层
 * --------P层   传递完数据给M层处理之后,实例化回调对象,成功了就通知V层登录成功,失败了就通知V层显示错误信息
 * --------V层   负责响应用户的交互(获取数据---->提示操作结果)
 */
public class LoginActivity extends BaseActivity implements LoginView {
    @BindView(R.id.et_login_userName)
    EditText etLoginUserName;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;
    private LoginPresenter mLoginPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }
    public void initPresenter(){

    }

    //初始化view
    public void initView(){
        mLoginPresenter = new LoginPresenterImpl(this);
    }
    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        mLoginPresenter = new LoginPresenter(this);
    }


    @Override
    public void showProgress() {
        startProgressDialog("加载中");
    }

    @Override
    public void hideProgress() {
        stopProgressDialog();
    }
    @Override
    public void showErrorMsg(String msg) {
        this.showNetErrorTip(msg);
    }

    @Override
    public void showInfo(String info) {
        this.showLongToast(info);
    }

    @Override
    public void toMain() {
//        MainActivity.startAction(LoginActivity.this);
//        finish();
        WorkbenchActivity.startAction(LoginActivity.this);
        finish();
    }


    @Override
    public UserInfo getUserLoginInfo() {
        String loginId = etLoginUserName.getText().toString().trim();
        String password = etLoginPwd.getText().toString().trim();
        //缓存登录信息
        //password 转码
        String passwordEnc = EncryptionUtil.md5(password);
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginId(loginId);
        userInfo.setPassword(passwordEnc);
        userInfo.setConfPassword(password);
        userInfo.setClientType(AppConstant.APP_CLIENT_TYPE);
        return userInfo;
    }



    @OnClick({R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                mLoginPresenter.login();
                break;
        }
    }
}
