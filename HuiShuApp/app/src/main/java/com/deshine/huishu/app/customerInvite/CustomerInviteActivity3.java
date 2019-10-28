package com.deshine.huishu.app.customerInvite;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.deshine.huishu.app.R;
import com.deshine.huishu.app.adapter.CommonAffixAdapter;
import com.deshine.huishu.app.app.AppConstant;
import com.deshine.huishu.app.app.AppManager;
import com.deshine.huishu.app.base.BaseActivity;
import com.deshine.huishu.app.cameralib.util.LogUtil;
import com.deshine.huishu.app.commonAffix.bean.CommonAffix;
import com.deshine.huishu.app.commonAffix.constants.AffixBizType;
import com.deshine.huishu.app.customerInvite.model.bean.FinanceBillDto;
import com.deshine.huishu.app.customerInvite.presenter.CustomerInvitePresenter;
import com.deshine.huishu.app.customerInvite.presenter.CustomerInvitePresenter3;
import com.deshine.huishu.app.customerInvite.presenter.impl.CustomerInvitePresenter3Impl;
import com.deshine.huishu.app.customerInvite.presenter.impl.CustomerInvitePresenterImpl;
import com.deshine.huishu.app.customerInvite.view.CustomerInviteView;
import com.deshine.huishu.app.customerInvite.view.CustomerInviteView3;
import com.deshine.huishu.app.permission.PermissionUtil;
import com.deshine.huishu.app.permission.callback.PermissionResultCallBack;
import com.deshine.huishu.app.utils.GesonUtil;
import com.deshine.huishu.app.utils.ToastUitl;
import com.deshine.huishu.app.workbench.view.WorkbenchActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 客户自提
 */
public class CustomerInviteActivity3 extends BaseActivity implements CustomerInviteView3 {
    //toolbar
    @BindView(R.id.common_titlebar)
    Toolbar mToolbar;

    //提货人姓名
    @BindView(R.id.user_name)
    TextView mUserName;

    //提货人手机
    @BindView(R.id.sms_val)
    TextView mSmsVal;
    @BindView(R.id.sms_status)
    TextView mSmsStatus;

    //提货人身份证号
    @BindView(R.id.idCard_val)
    TextView mIdCardVal;
    @BindView(R.id.idCard_val_1)
    TextView mIdCardVal1;
    @BindView(R.id.idCard_status)
    TextView mIdCardStatus;
    @BindView(R.id.submit)
    Button submit;

    //签收单上传
    @BindView(R.id.sign_order_layout)
    LinearLayout signOrderLayoput;
    @BindView(R.id.sign_order_count)
    TextView signOrderCount;
    @BindView(R.id.camera_btn)
    TextView cameraBtn;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;


    private CustomerInvitePresenter3 customerInvitePresenter;
    private FinanceBillDto customerInviteDto;

    private static final int SIGN_ORDER_REQUEST_CODE = 100;
    private int SIGN_ORDER_OPT_INDEX = -1;
    private List<CommonAffix> affixList = null;
    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, CustomerInviteActivity3.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //签收单拍照返回
        if (resultCode == 101 && SIGN_ORDER_REQUEST_CODE==requestCode) {
            LogUtil.i("picture");
            String path = data.getStringExtra("path");
            signOrderPhotoCallback(path);
        }
        //相机录像返回
        else if (resultCode == 102 && SIGN_ORDER_REQUEST_CODE==requestCode) {
            LogUtil.i("video");
            String path = data.getStringExtra("path");
        }
        //权限异常返回
        else if (resultCode == 103 && SIGN_ORDER_REQUEST_CODE==requestCode) {
            ToastUitl.showLong("请检查相机权限");
        }
    }
    /*********************
     * 子类实现
     *****************************/
    @Override
    public int getLayoutId() {
        return R.layout.customer_invite_step3;
    }

    @Override
    public void initPresenter() {
        customerInvitePresenter = new CustomerInvitePresenter3Impl(this);
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
        mSmsStatus.setText(R.string.hs_check_success);
        mSmsStatus.setTextColor(getResources().getColor(R.color.hs_success));
        String idCardNo = getIdCard();
        mIdCardVal.setText(idCardNo.substring(0,idCardNo.length()-4));
        mIdCardVal1.setText(idCardNo.substring(idCardNo.length()-4));
        mIdCardStatus.setText(R.string.hs_idCard_success);
        mIdCardStatus.setTextColor(getResources().getColor(R.color.hs_success));
        signOrderCount.setText("共"+customerInviteDto.getSignOrderTotal()+"页");
    }

    /**
     * 界面点击事件统一处理入口
     * @param view
     */
    @OnClick({R.id.submit,R.id.camera_btn})
    public void activityViewClick(View view) {
        switch (view.getId()) {
            case R.id.camera_btn:
                //签收单拍照点击事件
                signOrderPhotograph();
                break;
            case R.id.submit:
                //客户自提提交
                submit();
                break;
        }
    }
    public String getIdCard(){
        String idCard = "";
        String address = customerInviteDto.getConsigneeAddress();
        idCard = address.replace("客户自提 ","");
        return idCard;
    }

    /**
     * 设置签收单附件
     */
    public void setSignOrderPhoto(List<CommonAffix> affixList){
        customerInviteDto.setAffixList(affixList);
        customerInvitePresenter.customerInviteSubmit(customerInviteDto);
    }
    public void loading(){startProgressDialog("加载中");}
    public void stopLoading(){stopProgressDialog();}
    public void showErrorMsg(String errorMsg){
        ToastUitl.showShort(errorMsg);
    }
    //客户自提成功处理完成跳转到工作台
    public void toSuccessPage(){
        ToastUitl.showShort("出库成功");
        AppManager appManager = AppManager.getAppManager();
        appManager.finishActivity(CustomerInviteActivity1.class);
        appManager.finishActivity(CustomerInviteActivity2.class);
        appManager.returnToActivity(WorkbenchActivity.class);
        finish();
    }
    //签收单上传完成后，界面初始化
    public void initSubmit(){
        cameraBtn.setVisibility(View.GONE);
        submit.setVisibility(View.VISIBLE);
    }
    //签收单拍照
    public void signOrderPhotograph(){
        startActivityForResult(com.deshine.huishu.app.camera.CameraActivity.class,SIGN_ORDER_REQUEST_CODE);
    }
    //签收单拍照回调
    public void signOrderPhotoCallback(String imgPath){
        //cameraBtn.setVisibility(View.GONE);
        //signOrderPhoto.setVisibility(View.VISIBLE);
        //signOrderPhoto.setImageBitmap(BitmapFactory.decodeFile(imgPath));

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        if(affixList == null) affixList = new ArrayList<>();
        String suffixName = imgPath.substring(imgPath.lastIndexOf("/")+1);
        String suffix = suffixName.substring(suffixName.lastIndexOf(".")+1).toUpperCase();
        suffixName = affixList.size()+"."+suffix.toLowerCase();
        String userId = this.getSharedPreferences(AppConstant.CACHE_DATA, Context.MODE_PRIVATE).getString(AppConstant.USER_ID,null);
        CommonAffix commonAffix = new CommonAffix(null, AffixBizType.SF_SIGN_RECEIPT_NUM,null,
                imgPath,suffixName,suffix,0,userId,userId,new Date(),new Date());

        if(SIGN_ORDER_OPT_INDEX>=0){
            commonAffix.setBizId(affixList.get(SIGN_ORDER_OPT_INDEX).getBizId());
            commonAffix.setAffixId(affixList.get(SIGN_ORDER_OPT_INDEX).getAffixId());
            commonAffix.setSuffixName(affixList.size()+"."+suffix.toLowerCase());
            affixList.set(SIGN_ORDER_OPT_INDEX,commonAffix);
            SIGN_ORDER_OPT_INDEX = -1;
        }else{
            affixList.add(commonAffix);
        }
        int leaveCount = customerInviteDto.getSignOrderTotal()-affixList.size();
        if(leaveCount>0){
            cameraBtn.setText(getResources().getString(R.string.hs_sign_order_camera)+"，还需"+leaveCount+"张");
        }else if(leaveCount==0){
            initSubmit();
        }
        CommonAffixAdapter mAdapter = new CommonAffixAdapter(this,affixList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new CommonAffixAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //图片被点击
                SIGN_ORDER_OPT_INDEX = position;
                signOrderPhotograph();
            }
        });
    }
    //客户自提提交
    public void submit(){
        //调用签收单上传
        customerInvitePresenter.uploadSignOrderPhoto(this.affixList);
    }
    @Deprecated
    public void applyPermission(String[] permissions){
        if(permissions == null || permissions.length<=0){
            permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
        }
        PermissionUtil.getInstance().request(CustomerInviteActivity3.this,
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
