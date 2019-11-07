package com.deshine.huishu.app.scan;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deshine.huishu.app.R;
import com.deshine.huishu.app.base.BaseFragment;
import com.deshine.huishu.app.cameralib.util.LogUtil;
import com.deshine.huishu.app.permission.PermissionUtil;
import com.deshine.huishu.app.permission.callback.PermissionResultCallBack;
import com.deshine.huishu.app.scan.bean.ScanEvent;
import com.deshine.huishu.app.utils.ToastUitl;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.core.BarcodeType;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import de.greenrobot.event.EventBus;

public class ScanFragment extends BaseFragment implements QRCodeView.Delegate {

    @BindView(R.id.zxingview)
    ZXingView mZXingView;
    private Context mContext;
    private String param;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_base_scan;
    }

    @Override
    public void initView() {
        mContext = getActivity();
        Bundle bundle = getArguments();
        //这里就拿到了之前传递的参数
        param = bundle.getString("fromActivity");


//        mZXingView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别
//        mZXingView.changeToScanQRCodeStyle(); // 切换成扫描二维码样式
//        mZXingView.setType(BarcodeType.ALL, null); // 识别所有类型的码
//        mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
    }
    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if(isVisible){
            //可见，并且是第一次加载
            BGAQRCodeUtil.setDebug(true);
            mZXingView.setDelegate(this);
            applyPermission(null);
            isFirst = true;
        }else{
            //取消加载
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        mZXingView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
//        mZXingView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别

        mZXingView.changeToScanQRCodeStyle(); // 切换成扫描二维码样式
        mZXingView.setType(BarcodeType.ONLY_QR_CODE, null); // 识别所有类型的码
        mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
    }
    @Override
    public void onStop() {
        mZXingView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        if(mZXingView!=null){
            mZXingView.onDestroy(); // 销毁二维码扫描控件
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void vibrate() {
//        Vibrator vibrator = (Vibrator) getContext().getSystemService(VIBRATOR_SERVICE);
//        vibrator.vibrate(500);
    }
    /**
     * 处理扫描结果
     *
     * @param result 摄像头扫码时只要回调了该方法 result 就一定有值，不会为 null。解析本地图片或 Bitmap 时 result 可能为 null
     */
    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i(mContext.getClass().getSimpleName(), "result:" + result);
        if(TextUtils.isEmpty(result)){
            vibrate();
            mZXingView.startSpot(); // 开始识别
        }else{
            vibrate();
            mZXingView.stopSpot();//成功识别后停止识别
            //eventBus 通知扫码消息
            EventBus.getDefault().post(new ScanEvent(result, param));
            this.onDestroy();
        }

    }

    /**
     * 摄像头环境亮度发生变化
     *
     * @param isDark 是否变暗
     */
    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {
        // 这里是通过修改提示文案来展示环境是否过暗的状态，接入方也可以根据 isDark 的值来实现其他交互效果
        String tipText = mZXingView.getScanBoxView().getTipText();
        String ambientBrightnessTip = "\n环境过暗，请打开闪光灯";
        if (isDark) {
            if (!tipText.contains(ambientBrightnessTip)) {
                mZXingView.getScanBoxView().setTipText(tipText + ambientBrightnessTip);
            }
        } else {
            if (tipText.contains(ambientBrightnessTip)) {
                tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip));
                mZXingView.getScanBoxView().setTipText(tipText);
            }
        }
    }

    /**
     * 处理打开相机出错
     */
    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(mContext.getClass().getSimpleName(), "打开相机出错");
        ToastUitl.showShort("打开相机出错");
    }
    public void applyPermission(String[] permissions){
    if(permissions == null || permissions.length<=0){
        permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
    }
    PermissionUtil.getInstance().request(getActivity(),
            permissions,
            new PermissionResultCallBack() {
                /**
                 * 当所有权限的申请被用户同意之后,该方法会被调用
                 */
                @Override
                public void onPermissionGranted() {
                    //ToastUitl.showLong("all granted");
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
                    //ToastUitl.showLong(builder.toString() + " is granted");
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
                    //ToastUitl.showLong(builder.toString() + " is denied");
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
                    //ToastUitl.showLong(builder.toString() + " show Rational");
                }
            });
}
}
