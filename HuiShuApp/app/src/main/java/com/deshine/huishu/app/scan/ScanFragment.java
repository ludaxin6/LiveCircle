package com.deshine.huishu.app.scan;


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

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
//
//        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY) {
//            final String picturePath = "";//BGAPhotoPickerActivity.getSelectedPhotos(data).get(0);
//            // 本来就用到 QRCodeView 时可直接调 QRCodeView 的方法，走通用的回调
//            mZXingView.decodeQRCode(picturePath);
//
//            /*
//            没有用到 QRCodeView 时可以调用 QRCodeDecoder 的 syncDecodeQRCode 方法
//
//            这里为了偷懒，就没有处理匿名 AsyncTask 内部类导致 Activity 泄漏的问题
//            请开发在使用时自行处理匿名内部类导致Activity内存泄漏的问题，处理方式可参考 https://github
//            .com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%86%85%E5%AD%98%E6%B3%84%E6%BC%8F%E6%80%BB%E7%BB%93.md
//             */
////            new AsyncTask<Void, Void, String>() {
////                @Override
////                protected String doInBackground(Void... params) {
////                    return QRCodeDecoder.syncDecodeQRCode(picturePath);
////                }
////
////                @Override
////                protected void onPostExecute(String result) {
////                    if (TextUtils.isEmpty(result)) {
////                        Toast.makeText(TestScanActivity.this, "未发现二维码", Toast.LENGTH_SHORT).show();
////                    } else {
////                        Toast.makeText(TestScanActivity.this, result, Toast.LENGTH_SHORT).show();
////                    }
////                }
////            }.execute();
//        }
//    }
}
