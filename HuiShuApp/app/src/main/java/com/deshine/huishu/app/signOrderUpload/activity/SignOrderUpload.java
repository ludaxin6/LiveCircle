package com.deshine.huishu.app.signOrderUpload.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.deshine.huishu.app.R;
import com.deshine.huishu.app.adapter.CommonAffixAdapter;
import com.deshine.huishu.app.app.AppConstant;
import com.deshine.huishu.app.base.BaseActivity;
import com.deshine.huishu.app.camera.CameraActivity;
import com.deshine.huishu.app.cameralib.util.LogUtil;
import com.deshine.huishu.app.commonAffix.bean.CommonAffix;
import com.deshine.huishu.app.commonAffix.constants.AffixBizType;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInviteScanData;
import com.deshine.huishu.app.signOrderUpload.model.bean.dto.FreightOrderDto;
import com.deshine.huishu.app.signOrderUpload.presenter.SignOrderUploadPresenter;
import com.deshine.huishu.app.signOrderUpload.presenter.impl.SignOrderUploadPresenterImpl;
import com.deshine.huishu.app.signOrderUpload.view.SignOrderUploadView;
import com.deshine.huishu.app.utils.DateUtil;
import com.deshine.huishu.app.utils.ToastUitl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SignOrderUpload extends BaseActivity implements SignOrderUploadView {

    //toolbar
    @BindView(R.id.common_titlebar)
    Toolbar mToolbar;

    //收货人姓名
    @BindView(R.id.user_name)
    TextView mUserName;
    //记账日期
    @BindView(R.id.book_date)
    TextView bookDate;

    //收货人手机
    @BindView(R.id.sms_val)
    TextView mSmsVal;
    //收货地址
    @BindView(R.id.address_val)
    TextView address;
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

    private CustomerInviteScanData scanData;
    private FreightOrderDto freighOrderDto;
    private SignOrderUploadPresenter mPresenter;
    private static final int SIGN_ORDER_REQUEST_CODE = 100;
    private int SIGN_ORDER_OPT_INDEX = -1;
    private List<CommonAffix> affixList = null;
    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity, CustomerInviteScanData scanData) {
        Intent intent = new Intent(activity, SignOrderUpload.class);
        intent.putExtra(AppConstant.CUSTOMER_INVITE_SCAN_DATA,scanData);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }
    /*********************
     * 子类实现
     *****************************/
    @Override
    public int getLayoutId() {
        return R.layout.activity_sign_order_upload;
    }

    @Override
    public void initPresenter() {
        mPresenter = new SignOrderUploadPresenterImpl(this);
    }

    @Override
    public void initView() {
        //设置标题栏
        mToolbar.setTitle(R.string.hs_sign_order_title);
        baseToolbar(mToolbar);
        scanData = (CustomerInviteScanData)getIntent().getSerializableExtra(AppConstant.CUSTOMER_INVITE_SCAN_DATA);
        //获取客户自提数据,并发送短信验证码
        if(freighOrderDto == null){
            mPresenter.fetchSignOrderByOsId(scanData.getOsId());
        }else{
            LogUtil.i("---------重复扫码了");
        }
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
                //签收单上传提交
                submit();
                break;
        }
    }
    public void submit(){
        //调用签收单上传
        mPresenter.signOrderUpload(freighOrderDto.getSfBillId(),freighOrderDto.getSfBillNo(),this.affixList);
    }
    //签收单拍照
    public void signOrderPhotograph(){
        Bundle bundle = new Bundle();
        bundle.putBoolean(CameraActivity.KEY_HIDE_PICTURE, true);//不允许从相册选取
        bundle.putString(CameraActivity.KEY_TIP, "");
        startActivityForResult(CameraActivity.class,bundle,SIGN_ORDER_REQUEST_CODE);
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

    /*签收单查询返回*/
    public void fetchSignOrderByOsIdBack(FreightOrderDto freightOrderDto){
        this.freighOrderDto = freightOrderDto;
        mUserName.setText(freightOrderDto.getConsigneeName());
        bookDate.setText(DateUtil.format(freightOrderDto.getBookedDate(),"yyyy-MM-dd"));
        mSmsVal.setText(TextUtils.isEmpty(freightOrderDto.getConsigneeMobilePhone())?freightOrderDto.getConsigneePhone():freightOrderDto.getConsigneeMobilePhone());
        address.setText(freightOrderDto.getConsigneeAddress());
        signOrderCount.setText("共"+scanData.getSignOrderTotal()+"页");
    }
    //签收单上传完成后，界面初始化
    public void initSubmit(){
        cameraBtn.setVisibility(View.GONE);
        submit.setVisibility(View.VISIBLE);
    }
    //签收单拍照回调
    public void signOrderPhotoCallback(String imgPath){

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
        int leaveCount = scanData.getSignOrderTotal()-affixList.size();
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
    public void loading(){startProgressDialog("加载中");}
    public void stopLoading(){stopProgressDialog();}
    public void showErrorMsg(String errorMsg){
        ToastUitl.showShort(errorMsg);
    }
    //客户自提成功处理完成跳转到工作台
    public void toSuccessPage(){
        ToastUitl.showShort("签收单处理成功");
        finish();
    }
}
