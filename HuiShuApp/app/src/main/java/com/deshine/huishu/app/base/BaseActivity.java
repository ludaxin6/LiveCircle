package com.deshine.huishu.app.base;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.deshine.huishu.app.R;
import com.deshine.huishu.app.app.AppManager;
import com.deshine.huishu.app.cameralib.util.LogUtil;
import com.deshine.huishu.app.utils.StatusBarSetting;
import com.deshine.huishu.app.utils.StatusBarUtil;
import com.deshine.huishu.app.utils.ToastUitl;
import com.deshine.huishu.app.widget.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 基类
 */

public abstract class BaseActivity extends AppCompatActivity {

    public Context mContext;
    private Unbinder mUnbinder;
    private int count;//记录开启进度条的情况 只能开一个

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforeSetcontentView();
        setContentView(getLayoutId());
        // 默认着色状态栏
//        SetStatusBarColor();
        StatusBarSetting.setColorNoTranslucent(this, getResources().getColor(R.color.colorPrimary));
        mUnbinder = ButterKnife.bind(this);
        mContext = this;

        this.initPresenter();
        this.initView();
    }
    public void baseToolbar(Toolbar mToolbar){
        baseToolbarNoEvent(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void baseToolbarNoEvent(Toolbar mToolbar){
        TextView titleView = null;
        if(mToolbar.getChildCount()>1){
            titleView = (TextView)mToolbar.getChildAt(1);
            titleView.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
            titleView.setGravity(Gravity.CENTER_HORIZONTAL);
            titleView.setPadding(0,0,250,0);
        }
        setSupportActionBar(mToolbar);
    }

    /**
     * 设置layout前配置
     */
    private void doBeforeSetcontentView() {
        // 把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


    }

    /*********************
     * 子类实现
     *****************************/
    //获取布局文件
    public abstract int getLayoutId();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    //初始化view
    public abstract void initView();

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor() {
        StatusBarSetting.setColor(this, getResources().getColor(R.color.colorPrimary));
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(int color) {
        StatusBarSetting.setColor(this, color);
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar() {
        StatusBarSetting.setTranslucent(this);
    }
    protected void statusBarLightMode(){
        StatusBarUtil.statusBarLightMode(this);
    }


    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 开启浮动加载进度条
     */
    public void startProgressDialog() {
        count++;
        if(count==1){
            LoadingDialog.showDialogForLoading(this);
        }
    }

    /**
     * 开启浮动加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg) {
        count++;
        if(count==1){
            LoadingDialog.showDialogForLoading(this, msg, true);
        }
    }

    /**
     * 停止浮动加载进度条
     */
    public void stopProgressDialog() {
        count--;
        if(count==0){
            LoadingDialog.cancelDialogForLoading();
        }

    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text) {
        ToastUitl.showShort(text);
    }

    /**
     * 短暂显示Toast提示(id)
     **/
    public void showShortToast(int resId) {
        ToastUitl.showShort(resId);
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    public void showLongToast(int resId) {
        ToastUitl.showLong(resId);
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    public void showLongToast(String text) {
        ToastUitl.showLong(text);
    }

    /**
     * 带图片的toast
     *
     * @param text
     * @param res
     */
    public void showToastWithImg(String text, int res) {
        ToastUitl.showToastWithImg(text, res);
    }

    /**
     * 网络访问错误提醒
     */
    public void showNetErrorTip() {
        ToastUitl.showToastWithImg(getText(R.string.net_error).toString(), R.drawable.ic_wifi_off);
    }

    public void showNetErrorTip(String error) {
        ToastUitl.showToastWithImg(error, R.drawable.ic_wifi_off);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }
    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.e("++++++++BaseActivity活动被停止了");
        AppManager appManager = AppManager.getAppManager();
        LogUtil.e("堆栈数量："+ appManager.currentStackSize()+" 活动名称："+appManager.activityStackName());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        AppManager.getAppManager().finishActivity(this);
        //关闭弹出框
        LoadingDialog.cancelDialogForLoading();
    }
}
