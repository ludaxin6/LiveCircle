package com.deshine.huishu.app.customerInvite;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.deshine.huishu.app.R;
import com.deshine.huishu.app.app.AppConstant;
import com.deshine.huishu.app.base.BaseActivity;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInvite;
import com.deshine.huishu.app.customerInvite.presenter.CustomerInvitePresenter;
import com.deshine.huishu.app.customerInvite.presenter.impl.CustomerInvitePresenterImpl;
import com.deshine.huishu.app.customerInvite.view.CustomerInviteView;
import com.deshine.huishu.app.orcameralib.CameraActivity;
import com.deshine.huishu.app.permission.PermissionUtil;
import com.deshine.huishu.app.permission.callback.PermissionResultCallBack;
import com.deshine.huishu.app.scan.bean.ScanEvent;
import com.deshine.huishu.app.utils.CollectionUtils;
import com.deshine.huishu.app.utils.ToastUitl;
import com.deshine.huishu.app.widget.SeparatedEditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * 客户自提
 */
public class CustomerInviteActivity extends BaseActivity implements CustomerInviteView {
    //toolbar
    @BindView(R.id.common_titlebar)
    Toolbar mToolbar;
    @BindView(R.id.common_titlebar_tv)
    TextView mTextView;

    //提货人姓名
    @BindView(R.id.user_layout)
    LinearLayout mUserLayout;
    @BindView(R.id.user_name)
    TextView mUserName;

    //提货人手机
    @BindView(R.id.sms_layout)
    LinearLayout mSmsLayout;
    @BindView(R.id.sms_val)
    TextView mSmsVal;
    @BindView(R.id.sms_status)
    TextView mSmsStatus;

    //提货人身份证号
    @BindView(R.id.idCard_layout)
    LinearLayout mIdCardLayout;
    @BindView(R.id.idCard_val)
    TextView mIdCardVal;
    @BindView(R.id.idCard_val_1)
    TextView mIdCardVal1;
    @BindView(R.id.idCard_status)
    TextView mIdCardStatus;
    @BindView(R.id.tvFrontImage)
    ImageView tvFrontImage;
    @BindView(R.id.tvBackImage)
    ImageView tvBackImage;
    @BindView(R.id.idCard_submit)
    Button idcardSubmit;
    @BindView(R.id.submit)
    Button submit;

    //短信验证页面
    @BindView(R.id.sms_check_layout)
    LinearLayout mSmsCheckLayout;
    @BindView(R.id.sms_tip)
    TextView mSmsTip;
    @BindView(R.id.edit_hollow)
    SeparatedEditText separatedEdit;
    @BindView(R.id.sms_time)
    TextView mSmsTime;

    //身份证验证
    @BindView(R.id.idCard_check_layout)
    LinearLayout mIdCardCheckLayout;
    @BindView(R.id.tvFront)
    TextView mTvFront;
    @BindView(R.id.tvBack)
    TextView mTvBack;


    private CustomerInvitePresenter customerInvitePresenter;
    private CustomerInvite customerInvite;
    private int time=300;
    private final Handler handler = new Handler();
    private static final int IDCARD_FRONT_REQUEST_CODE = 1001;
    private static final String IDCARD_FRONT_IMAGE_NAME = "idcard_front.jpg";
    private static final int IDCARD_BACK_REQUEST_CODE = 1002;
    private static final String IDCARD_BACK_IMAGE_NAME = "idcard_back.jpg";

    private static final String SMS_PWD="1234";
    private final String scanVal = "XDDEL190918-08032/1";
    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, CustomerInviteActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //身份证正面拍照完成
        if (resultCode == Activity.RESULT_OK && IDCARD_FRONT_REQUEST_CODE==requestCode) {
            mTvFront.setVisibility(View.GONE);
            tvFrontImage.setVisibility(View.VISIBLE);
            tvFrontImage.setImageURI(Uri.fromFile(new File(CustomerInviteActivity.this.getFilesDir(), IDCARD_FRONT_IMAGE_NAME)));

        }
        //身份证反面拍照完成
        else if(resultCode == Activity.RESULT_OK && IDCARD_BACK_REQUEST_CODE==requestCode){
            mTvBack.setVisibility(View.GONE);
            tvBackImage.setVisibility(View.VISIBLE);
            idcardSubmit.setVisibility(View.VISIBLE);
            tvBackImage.setImageURI(Uri.fromFile(new File(CustomerInviteActivity.this.getFilesDir(), IDCARD_BACK_IMAGE_NAME)));
        }
    }
    /*********************
     * 子类实现
     *****************************/
    @Override
    public int getLayoutId() {
        return R.layout.customer_invite;
    }

    @Override
    public void initPresenter() {
        customerInvitePresenter = new CustomerInvitePresenterImpl(this);
    }
    @Override
    public void initView() {
        //设置标题栏
        mTextView.setText(R.string.hs_customer_invite);
        setSupportActionBar(mToolbar);
        //初始化页面显示样式
        initViewVisable();
        //清空缓存数据
        this.getSharedPreferences(AppConstant.CUSTOMER_INVITE, Context.MODE_PRIVATE).edit().clear().commit();
        //扫码事件监听注册
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        ScanEvent scanEvent = new ScanEvent(scanVal,this.getClass().getSimpleName());
        scanBack(scanEvent);
        //启动扫码
        //ScanActivity.startAction(CustomerInviteActivity.this);


    }
    /**
     * 界面点击事件统一处理入口
     * @param view
     */
    @OnClick({R.id.tvFront,R.id.tvBack,R.id.tvFrontImage,R.id.tvBackImage,
            R.id.idCard_submit,R.id.submit})
    public void activityViewClick(View view) {
        switch (view.getId()) {
            case R.id.tvFront:
            case R.id.tvFrontImage:
                //身份证正面拍照点击事件
                idCardFrontClick();
                break;
            case R.id.tvBack:
            case R.id.tvBackImage:
                //身份证正面拍照点击事件
                idCardBackClick();
                break;
            case R.id.idCard_submit:
                //身份证上传
                idCardUpload();
                break;
            case R.id.submit:
                //客户自提提交
                submit();
                break;
        }
    }
    /**
     * 更新页面
     *
     * @param customerInvite
     */
    @Override
    public void updateInfo(CustomerInvite customerInvite) {
        this.customerInvite = customerInvite;
        mUserName.setText(customerInvite.getConsigneeName());
        //调用发送短信验证码

        mSmsTip.setText("验证码已发至提货人手机"+customerInvite.getConsigneeMobilePhone()+"，请输入验证码");
        mSmsVal.setText(customerInvite.getConsigneeMobilePhone());
        //倒计时
        time=300;
        mSmsTime.setText(""+time);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                time--;
                if(mSmsTime != null) mSmsTime.setText(""+time);
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 1000);
        separatedEdit.setPassword(true);
        separatedEdit.showSoftInput();
        separatedEdit.setTextChangedListener(new SeparatedEditText.TextChangedListener() {
            @Override
            public void textChanged(CharSequence changeText) {
            }

            @Override
            public void textCompleted(CharSequence text) {
                smsCallback(text.toString());
            }
        });
    }
    @Subscribe
    public void onScanSuccessBackEvent(ScanEvent event) {
        scanBack(event);
    }


    //最初始界面
    public void initViewVisable(){
        mUserLayout.setVisibility(View.GONE);
        mSmsLayout.setVisibility(View.GONE);
        mIdCardLayout.setVisibility(View.GONE);
        mSmsCheckLayout.setVisibility(View.GONE);
        mIdCardCheckLayout.setVisibility(View.GONE);
        tvFrontImage.setVisibility(View.GONE);
        tvBackImage.setVisibility(View.GONE);
        idcardSubmit.setVisibility(View.GONE);
        submit.setVisibility(View.GONE);
    }
    //扫码返回后界面
    public void initAfterScanView(){
        mUserLayout.setVisibility(View.VISIBLE);
    }
    //获取客户自提数据后界面
    public void initCustomerInviteDataView(){
        mSmsCheckLayout.setVisibility(View.VISIBLE);
    }
    //短信验证通过
    public void initSmsSuccessView(){
        mSmsCheckLayout.setVisibility(View.GONE);
        mSmsLayout.setVisibility(View.VISIBLE);
        mIdCardLayout.setVisibility(View.VISIBLE);
        mIdCardCheckLayout.setVisibility(View.VISIBLE);
    }
    //扫码完成回调
    public void scanBack(ScanEvent event){
        if(this.getClass().getSimpleName().equals(event.getTargetActivityName())) {
            //扫码后界面显示
            initAfterScanView();
            //获取客户自提数据
            String soNo = event.getScanValue();
            String userId = this.getSharedPreferences(AppConstant.CACHE_DATA, Context.MODE_PRIVATE).getString(AppConstant.USER_ID, null);
            ToastUitl.showLong("soNo:" + soNo + " userId:" + userId);
            //扫码完成后显示的界面
            initCustomerInviteDataView();
            customerInvitePresenter.fetchCustomerInvite(soNo, userId);
        }
    }
    //短信验证回调
    public void smsCallback(String text){
        if(SMS_PWD.equals(text)){
            ToastUitl.showLong("短信验证通过");
            //定时销毁
            handler.removeCallbacksAndMessages(null);
            //隐藏软键盘
            separatedEdit.hideSoftInput();
            initSmsSuccessView();
            mSmsStatus.setText(R.string.hs_check_success);
            mSmsStatus.setTextColor(getResources().getColor(R.color.hs_success));
            mIdCardVal.setText(this.customerInvite.getIdCardNo().substring(0,this.customerInvite.getIdCardNo().length()-4));
            mIdCardVal1.setText(this.customerInvite.getIdCardNo().substring(this.customerInvite.getIdCardNo().length()-4));
        }else{
            ToastUitl.showLong("短信验证失败");
            if(time>0){
                separatedEdit.clearText();
            }
        }
    }
    //身份证正面拍照
    public void idCardFrontClick(){
        Bundle bundle = new Bundle();
        bundle.putString("outputFilePath", new File(CustomerInviteActivity.this.getFilesDir(), IDCARD_FRONT_IMAGE_NAME).toString());
        bundle.putString("contentType", "IDCardFront");
        startActivityForResult(CameraActivity.class,bundle,IDCARD_FRONT_REQUEST_CODE);
    }
    //身份证反面拍照
    public void idCardBackClick(){
        Bundle bundle = new Bundle();
        bundle.putString("outputFilePath", new File(CustomerInviteActivity.this.getFilesDir(), IDCARD_BACK_IMAGE_NAME).toString());
        bundle.putString("contentType", "IDCardBack");
        startActivityForResult(CameraActivity.class,bundle,IDCARD_BACK_REQUEST_CODE);
    }
    //身份证上传
    public void idCardUpload(){
        //身份证上传TODO
        mIdCardCheckLayout.setVisibility(View.GONE);
        mIdCardStatus.setVisibility(View.VISIBLE);
        mIdCardStatus.setText(R.string.hs_idCard_success);
        mIdCardStatus.setTextColor(getResources().getColor(R.color.hs_success));
        idcardSubmit.setVisibility(View.GONE);
        submit.setVisibility(View.VISIBLE);
    }
    //客户自提提交
    public void submit(){

    }
    @Deprecated
    public void applyPermission(String[] permissions){
        if(permissions == null || permissions.length<=0){
            permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
        }
        PermissionUtil.getInstance().request(CustomerInviteActivity.this,
                permissions,
                new PermissionResultCallBack() {
                    /**
                     * 当所有权限的申请被用户同意之后,该方法会被调用
                     */
                    @Override
                    public void onPermissionGranted() {
                        ToastUitl.showLong("all granted");
                    }
                    /**
                     * 返回此次申请中通过的权限列表
                     */
                    @Override
                    public void onPermissionGranted(String... permissions) {
                        StringBuilder builder = new StringBuilder();
                        for (String permission : permissions) {
                            builder.append(permission.substring(permission.lastIndexOf(".") + 1) + " ");
                        }
                        ToastUitl.showLong(builder.toString() + " is granted");
                    }
                    /**
                     * 当权限申请中的某一个或多个权限,在此次申请中被用户否定了,并勾选了不再提醒选项时（权限的申请窗口不能再弹出，
                     * 没有办法再次申请）,该方法将会被调用。该方法调用时机在onRationalShow之前.onDenied和onRationalShow
                     * 有可能都会被触发.
                     */
                    @Override
                    public void onPermissionDenied(String... permissions) {
                        StringBuilder builder = new StringBuilder();
                        for (String permission : permissions) {
                            builder.append(permission.substring(permission.lastIndexOf(".") + 1) + " ");
                        }
                        ToastUitl.showLong(builder.toString() + " is denied");
                    }
                    /**
                     * 当权限申请中的某一个或多个权限,在此次申请中被用户否定了,但没有勾选不再提醒选项时（权限申请窗口还能再次申请弹出）
                     * 该方法将会被调用.这个方法会在onPermissionDenied之后调用,当申请权限为多个时,onDenied和onRationalShow
                     * 有可能都会被触发.
                     */
                    @Override
                    public void onRationalShow(String... permissions) {
                        StringBuilder builder = new StringBuilder();
                        for (String permission : permissions) {
                            builder.append(permission.substring(permission.lastIndexOf(".") + 1) + " ");
                        }
                        ToastUitl.showLong(builder.toString() + " show Rational");
                    }
                });
    }
}
