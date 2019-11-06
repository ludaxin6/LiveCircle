package com.deshine.huishu.app.customerInvite;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.deshine.huishu.app.R;
import com.deshine.huishu.app.app.AppConstant;
import com.deshine.huishu.app.app.AppManager;
import com.deshine.huishu.app.base.BaseActivity;
import com.deshine.huishu.app.cameralib.util.LogUtil;
import com.deshine.huishu.app.commonAffix.bean.CommonAffix;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInviteScanData;
import com.deshine.huishu.app.customerInvite.model.bean.FinanceBillDto;
import com.deshine.huishu.app.customerInvite.presenter.CustomerInvitePresenter;
import com.deshine.huishu.app.customerInvite.presenter.CustomerInvitePresenter1;
import com.deshine.huishu.app.customerInvite.presenter.impl.CustomerInvitePresenter1Impl;
import com.deshine.huishu.app.customerInvite.presenter.impl.CustomerInvitePresenterImpl;
import com.deshine.huishu.app.customerInvite.view.CustomerInviteView1;
import com.deshine.huishu.app.utils.GesonUtil;
import com.deshine.huishu.app.utils.ToastUitl;
import com.deshine.huishu.app.widget.SeparatedEditText;
import com.deshine.huishu.app.workbench.view.WorkbenchActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 客户自提
 */
public class CustomerInviteActivity1 extends BaseActivity implements CustomerInviteView1 {
    //toolbar
    @BindView(R.id.common_titlebar)
    Toolbar mToolbar;

    //提货人姓名
    @BindView(R.id.user_layout)
    LinearLayout mUserLayout;
    @BindView(R.id.user_name)
    TextView mUserName;

    //短信验证页面
    @BindView(R.id.sms_check_layout)
    LinearLayout mSmsCheckLayout;
    @BindView(R.id.sms_tip)
    TextView mSmsTip;
    @BindView(R.id.edit_hollow)
    SeparatedEditText separatedEdit;
    @BindView(R.id.sms_time)
    TextView mSmsTime;


    private CustomerInvitePresenter1 customerInvitePresenter;
    private FinanceBillDto customerInviteDto;
    private int time=120;
    private CountDownTimer timer;
    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity,CustomerInviteScanData scanData) {
        Intent intent = new Intent(activity, CustomerInviteActivity1.class);
        intent.putExtra(AppConstant.CUSTOMER_INVITE_SCAN_DATA,scanData);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(timer != null) timer.cancel();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.i("重新启用customerinviteActivity1");
        updateInfo(customerInviteDto);
    }

    /*********************
     * 子类实现
     *****************************/
    @Override
    public int getLayoutId() {
        return R.layout.customer_invite_step1;
    }

    @Override
    public void initPresenter() {
        customerInvitePresenter = new CustomerInvitePresenter1Impl(this);
    }
    @Override
    public void initView() {
        //设置标题栏
        mToolbar.setTitle(R.string.hs_customer_invite);
        baseToolbar(mToolbar);
        //初始化页面显示样式
        initViewVisable();
        //清空缓存数据
        this.getSharedPreferences(AppConstant.CACHE_DATA, Context.MODE_PRIVATE).edit()
                .remove(AppConstant.CUSTOMER_INVITE)
                .apply();
        CustomerInviteScanData scanData = (CustomerInviteScanData)getIntent().getSerializableExtra(AppConstant.CUSTOMER_INVITE_SCAN_DATA);
        //获取客户自提数据,并发送短信验证码
        if(customerInviteDto == null){
            customerInvitePresenter.fetchCustomerInvite(scanData);
        }else{
            LogUtil.i("---------重复扫码了");
        }

    }
    /**
     * 界面点击事件统一处理入口
     * @param view
     */
    @OnClick({R.id.sms_time})
    public void activityViewClick(View view) {
        switch (view.getId()) {
            case R.id.sms_time:
                if(mSmsTime.getText().equals("重新发送")){
                    //发送短信验证码
                    separatedEdit.clearText();
                    time = 120;
                    customerInvitePresenter.sendSmsVerifyCode(getIdCard(),customerInviteDto.getConsigneeMobilePhone(),time);
                }
                break;
        }
    }
    /**
     * 更新页面
     */
    @Override
    public void updateInfo(FinanceBillDto dto) {
        this.customerInviteDto = dto;
        this.getSharedPreferences(AppConstant.CACHE_DATA, Context.MODE_PRIVATE).edit()
                .putString(AppConstant.CUSTOMER_INVITE, GesonUtil.getGson().toJson(this.customerInviteDto))
                .apply();
        //扫码后界面显示
        initAfterScanView();
        mUserName.setText(customerInviteDto.getConsigneeName());
        //扫码完成后显示的界面
        initCustomerInviteDataView();
        //发送短信验证码
        customerInvitePresenter.sendSmsVerifyCode(getIdCard(),customerInviteDto.getConsigneeMobilePhone(),time);
    }
    @Override
    public void sendSmsVerifyCodeBack() {
        //调用发送短信验证码
        mSmsTip.setText("验证码已发至提货人手机"+customerInviteDto.getConsigneeMobilePhone());
        timeCountDown();
        separatedEdit.clearText();
        separatedEdit.setPassword(true);
        separatedEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
        separatedEdit.showSoftInput();
        separatedEdit.requestFocus();
        separatedEdit.setTextChangedListener(new SeparatedEditText.TextChangedListener() {
            @Override
            public void textChanged(CharSequence changeText) {
            }

            @Override
            public void textCompleted(CharSequence text) {
                //调用短信验证
                customerInvitePresenter.checkSmsVerifyCode(getIdCard(),text.toString().trim());
            }
        });
    }
    @Override
    public void checkSmsVerifyCodeBack(boolean flage) {
        if(flage){
            ToastUitl.showShort("短信验证通过");
            //定时销毁
            if(timer!=null)timer.cancel();
            //隐藏软键盘
            separatedEdit.hideSoftInput();
            CustomerInviteActivity2.startAction(this);
        }else{
            ToastUitl.showShort("短信验证失败");
            if(time>0){
                separatedEdit.clearText();
            }
        }
    }
    public String getIdCard(){
        String idCard = "";
        String address = customerInviteDto.getConsigneeAddress();
        idCard = address.replace("客户自提 ","");
        return idCard;
    }
    public void timeCountDown(){
        //倒计时
        time=120;
        //倒计时60秒,这里不直接写60000,而用1000*60是因为后者看起来更直观,每走一步是1000毫秒也就是1秒
        timer = new CountDownTimer(1000 * 120, 1000) {
            @SuppressLint("DefaultLocale")
            @Override
            public void onTick(long millisUntilFinished) {
                time --;
                if(time<=0){
                    this.onFinish();
                    return;
                }
                mSmsTime.setText(""+time);
            }

            @Override
            public void onFinish() {
                mSmsTime.setText("重新获取");
                mSmsTime.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                timer.cancel();
            }
        };
        timer.start();
    }

    public void loading(){startProgressDialog("加载中");}
    public void stopLoading(){stopProgressDialog();}
    public void showErrorMsg(String errorMsg){
        ToastUitl.showShort(errorMsg);
    }

    @Override
    public void backToWorkBench() {
        AppManager.getAppManager().returnToActivity(WorkbenchActivity.class);
    }


    //最初始界面
    public void initViewVisable(){
        mUserLayout.setVisibility(View.GONE);
        mSmsCheckLayout.setVisibility(View.GONE);
    }
    //扫码返回后界面
    public void initAfterScanView(){
        if(mUserLayout != null && mUserLayout.getVisibility() != View.VISIBLE) mUserLayout.setVisibility(View.VISIBLE);
        mSmsCheckLayout.setVisibility(View.GONE);
    }
    //获取客户自提数据后界面
    public void initCustomerInviteDataView(){
        mSmsTime.setBackgroundColor(getResources().getColor(R.color.hs_gray));
        mSmsCheckLayout.setVisibility(View.VISIBLE);

    }
}
