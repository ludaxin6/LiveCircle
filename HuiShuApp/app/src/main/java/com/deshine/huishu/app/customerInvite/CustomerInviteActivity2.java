package com.deshine.huishu.app.customerInvite;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.deshine.huishu.app.R;
import com.deshine.huishu.app.app.AppConstant;
import com.deshine.huishu.app.base.BaseActivity;
import com.deshine.huishu.app.commonAffix.bean.CommonAffix;
import com.deshine.huishu.app.customerInvite.model.bean.FinanceBillDto;
import com.deshine.huishu.app.customerInvite.presenter.CustomerInvitePresenter2;
import com.deshine.huishu.app.customerInvite.presenter.impl.CustomerInvitePresenter2Impl;
import com.deshine.huishu.app.customerInvite.view.CustomerInviteView2;
import com.deshine.huishu.app.orcameralib.OcrCameraActivity;
import com.deshine.huishu.app.permission.PermissionUtil;
import com.deshine.huishu.app.permission.callback.PermissionResultCallBack;
import com.deshine.huishu.app.utils.GesonUtil;
import com.deshine.huishu.app.utils.ToastUitl;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 客户自提
 */
public class CustomerInviteActivity2 extends BaseActivity implements CustomerInviteView2 {
    //toolbar
    @BindView(R.id.common_titlebar)
    Toolbar mToolbar;

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

    //身份证验证
    @BindView(R.id.idCard_check_layout)
    LinearLayout mIdCardCheckLayout;
    @BindView(R.id.tvFront)
    TextView mTvFront;
    @BindView(R.id.tvBack)
    TextView mTvBack;


    private CustomerInvitePresenter2 customerInvitePresenter;
    private FinanceBillDto customerInviteDto;
    private static final int IDCARD_FRONT_REQUEST_CODE = 1001;
    private static final int IDCARD_BACK_REQUEST_CODE = 1002;
    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, CustomerInviteActivity2.class);
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
            tvFrontImage.setImageURI(Uri.fromFile(new File(CustomerInviteActivity2.this.getFilesDir(), AppConstant.IDCARD_FRONT_IMAGE_NAME)));

        }
        //身份证反面拍照完成
        else if(resultCode == Activity.RESULT_OK && IDCARD_BACK_REQUEST_CODE==requestCode){
            mTvBack.setVisibility(View.GONE);
            tvBackImage.setVisibility(View.VISIBLE);
            idcardSubmit.setVisibility(View.VISIBLE);
            tvBackImage.setImageURI(Uri.fromFile(new File(CustomerInviteActivity2.this.getFilesDir(), AppConstant.IDCARD_BACK_IMAGE_NAME)));
        }
    }
    /*********************
     * 子类实现
     *****************************/
    @Override
    public int getLayoutId() {
        return R.layout.customer_invite_step2;
    }

    @Override
    public void initPresenter() {
        customerInvitePresenter = new CustomerInvitePresenter2Impl(this);
    }
    @Override
    public void initView() {
        //设置标题栏
        mToolbar.setTitle(R.string.hs_customer_invite);
        baseToolbar(mToolbar);
        String json = this.getSharedPreferences(AppConstant.CACHE_DATA, Context.MODE_PRIVATE).getString(AppConstant.CUSTOMER_INVITE,null);
        this.customerInviteDto =GesonUtil.getGson().fromJson(json,FinanceBillDto.class);
        mUserName.setText(customerInviteDto.getConsigneeName());
        mSmsVal.setText(customerInviteDto.getConsigneeMobilePhone());
        initSmsSuccessView();
        mSmsStatus.setText(R.string.hs_check_success);
        mSmsStatus.setTextColor(getResources().getColor(R.color.hs_success));
        String idCardNo = getIdCard();
        mIdCardVal.setText(idCardNo.substring(0,idCardNo.length()-4));
        mIdCardVal1.setText(idCardNo.substring(idCardNo.length()-4));

    }

    /**
     * 界面点击事件统一处理入口
     * @param view
     */
    @OnClick({R.id.tvFront,R.id.tvBack,R.id.tvFrontImage,R.id.tvBackImage,
            R.id.idCard_submit})
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
        }
    }

    /**
     * 更新身份证数据
     * @param idCardList
     */
    public void setIdCardData(List<CommonAffix> idCardList){
        customerInviteDto.setIdCardAffixList(idCardList);
        this.getSharedPreferences(AppConstant.CACHE_DATA, Context.MODE_PRIVATE).edit()
                .putString(AppConstant.CUSTOMER_INVITE, GesonUtil.getGson().toJson(this.customerInviteDto))
                .commit();
//        mIdCardStatus.setText(R.string.hs_idCard_success);
//        mIdCardStatus.setTextColor(getResources().getColor(R.color.hs_success));
        CustomerInviteActivity3.startAction(this);
    }

    public String getIdCard(){
        String idCard = "";
        String address = customerInviteDto.getConsigneeAddress();
        idCard = address.replace("客户自提 ","");
        return idCard;
    }
    public void loading(){startProgressDialog("加载中");}
    public void stopLoading(){stopProgressDialog();}
    public void showErrorMsg(String errorMsg){
        ToastUitl.showShort(errorMsg);
    }
    //客户自提成功处理完成跳转到工作台
    public void toSuccessPage(){
    }

    //短信验证通过
    public void initSmsSuccessView(){
        mSmsLayout.setVisibility(View.VISIBLE);
        mIdCardLayout.setVisibility(View.VISIBLE);
        mIdCardCheckLayout.setVisibility(View.VISIBLE);
    }
    //身份证正面拍照
    public void idCardFrontClick(){
        Bundle bundle = new Bundle();
        bundle.putString("outputFilePath", new File(CustomerInviteActivity2.this.getFilesDir(), AppConstant.IDCARD_FRONT_IMAGE_NAME).toString());
        bundle.putString("contentType", "IDCardFront");
        startActivityForResult(OcrCameraActivity.class,bundle,IDCARD_FRONT_REQUEST_CODE);
    }
    //身份证反面拍照
    public void idCardBackClick(){
        Bundle bundle = new Bundle();
        bundle.putString("outputFilePath", new File(CustomerInviteActivity2.this.getFilesDir(), AppConstant.IDCARD_BACK_IMAGE_NAME).toString());
        bundle.putString("contentType", "IDCardBack");
        startActivityForResult(OcrCameraActivity.class,bundle,IDCARD_BACK_REQUEST_CODE);
    }
    //身份证上传
    public void idCardUpload(){
        //身份证上传
        customerInvitePresenter.uploadIdCardPhoto(CustomerInviteActivity2.this.getFilesDir().toString());
    }
    @Deprecated
    public void applyPermission(String[] permissions){
        if(permissions == null || permissions.length<=0){
            permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
        }
        PermissionUtil.getInstance().request(CustomerInviteActivity2.this,
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
