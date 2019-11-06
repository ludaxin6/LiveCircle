package com.deshine.huishu.app.customerInvite;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
import com.deshine.huishu.app.workbench.view.WorkbenchActivity;
import com.deshine.huishu.idcardcamera.camera.IDCardCamera;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

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

    //身份证验证
    @BindView(R.id.idCard_check_layout)
    LinearLayout mIdCardCheckLayout;
    @BindView(R.id.tvFront)
    TextView mTvFront;
    @BindView(R.id.tvBack)
    TextView mTvBack;

    //滚动对象
    @BindView(R.id.main_scroll)
    ScrollView scrollView;

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
            //滚动到底部
            //EventBus.getDefault().post(new MScrollEvent(ScrollView.FOCUS_DOWN,this.getClass().getSimpleName()));
            scrollView.post(new Runnable() {
                public void run() {
                    scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                }
            });
        }
        //新的控件
//        else if (resultCode == IDCardCamera.RESULT_CODE) {
//            //获取图片路径，显示图片
//            final String path = IDCardCamera.getImagePath(data);
//            if (!TextUtils.isEmpty(path)) {
//                if (requestCode == IDCardCamera.TYPE_IDCARD_FRONT) { //身份证正面
//                    mTvFront.setVisibility(View.GONE);
//                    tvFrontImage.setVisibility(View.VISIBLE);
//                    tvFrontImage.setImageBitmap(BitmapFactory.decodeFile(path));
//                } else if (requestCode == IDCardCamera.TYPE_IDCARD_BACK) {  //身份证反面
//                    mTvBack.setVisibility(View.GONE);
//                    tvBackImage.setVisibility(View.VISIBLE);
//                    tvBackImage.setImageBitmap(BitmapFactory.decodeFile(path));
//                }
//            }
//        }
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

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

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

    //短信验证通过
    public void initSmsSuccessView(){
        mSmsLayout.setVisibility(View.VISIBLE);
        mIdCardLayout.setVisibility(View.VISIBLE);
        mIdCardCheckLayout.setVisibility(View.VISIBLE);
    }
    //身份证正面拍照
    public void idCardFrontClick(){
        Bundle bundle = new Bundle();
        bundle.putString(OcrCameraActivity.KEY_OUTPUT_FILE_PATH, new File(CustomerInviteActivity2.this.getFilesDir(), AppConstant.IDCARD_FRONT_IMAGE_NAME).toString());
        bundle.putString(OcrCameraActivity.KEY_CONTENT_TYPE, OcrCameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
        bundle.putBoolean(OcrCameraActivity.KEY_HIDE_PICTURE, true);//不允许从相册选取
        startActivityForResult(OcrCameraActivity.class,bundle,IDCARD_FRONT_REQUEST_CODE);

//        IDCardCamera.create(CustomerInviteActivity2.this).openCamera(IDCardCamera.TYPE_IDCARD_FRONT);
    }
    //身份证反面拍照
    public void idCardBackClick(){
        Bundle bundle = new Bundle();
        bundle.putString(OcrCameraActivity.KEY_OUTPUT_FILE_PATH, new File(CustomerInviteActivity2.this.getFilesDir(), AppConstant.IDCARD_BACK_IMAGE_NAME).toString());
        bundle.putString(OcrCameraActivity.KEY_CONTENT_TYPE, OcrCameraActivity.CONTENT_TYPE_ID_CARD_BACK);
        bundle.putBoolean(OcrCameraActivity.KEY_HIDE_PICTURE, true);//不允许从相册选取
        startActivityForResult(OcrCameraActivity.class,bundle,IDCARD_BACK_REQUEST_CODE);
//        IDCardCamera.create(this).openCamera(IDCardCamera.TYPE_IDCARD_BACK);
    }
    //身份证上传
    public void idCardUpload(){
        //身份证上传
        if(tvFrontImage.getVisibility() == View.GONE){
            ToastUitl.showShort("请上传身份证头像面照片");
        }else if(tvBackImage.getVisibility() == View.GONE){
            ToastUitl.showShort("请上传身份证国徽面照片");
        }else{
            customerInvitePresenter.uploadIdCardPhoto(CustomerInviteActivity2.this.getFilesDir().toString());
        }
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
    @Subscribe
    public void onScrollEvent(MScrollEvent event) {
        if(this.getClass().getSimpleName().equals(event.getTargetActivityName())){
            event.toFullScrollByType(event.getScrollType());
        }
    }
    public class MScrollEvent{
        private int scrollType;
        private String targetActivityName;
        public MScrollEvent(){}
        public MScrollEvent(int scrollType, String targetActivityName){
            this.scrollType = scrollType;
            this.targetActivityName = targetActivityName;
        }
        public int getScrollType() {
            return scrollType;
        }

        public void setScrollType(int scrollType) {
            this.scrollType = scrollType;
        }

        public String getTargetActivityName() {
            return targetActivityName;
        }

        public void setTargetActivityName(String targetActivityName) {
            this.targetActivityName = targetActivityName;
        }

        public void toFullScrollByType(int scrollType){
            scrollView.fullScroll(ScrollView.FOCUS_DOWN);
        }
    }
}
