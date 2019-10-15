package com.deshine.huishu.app.scan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.deshine.huishu.app.R;
import com.deshine.huishu.app.base.BaseActivity;
import com.deshine.huishu.app.scan.bean.ScanEvent;
import com.deshine.huishu.app.utils.StatusBarSetting;
import com.deshine.huishu.app.utils.ToastUitl;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.core.BarcodeType;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import de.greenrobot.event.EventBus;

/**
 * 扫码工具组件
 */
public class ScanActivity extends BaseActivity implements QRCodeView.Delegate {
    private static final int REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY = 666;

    @BindView(R.id.zxingview)
    ZXingView mZXingView;
    @BindView(R.id.common_titlebar)
    Toolbar mToolbar;
    @BindView(R.id.common_titlebar_tv)
    TextView mTextView;
    private Context mContext;

    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, ScanActivity.class);
        intent.putExtra("fromActivity",activity.getClass().getSimpleName());
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_scan;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        //设置标题栏
        mTextView.setText(R.string.hs_scan);
        setSupportActionBar(mToolbar);

        mContext = this;

        BGAQRCodeUtil.setDebug(true);
        mZXingView.setDelegate(this);

//        mZXingView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别
//        mZXingView.changeToScanQRCodeStyle(); // 切换成扫描二维码样式
//        mZXingView.setType(BarcodeType.ALL, null); // 识别所有类型的码
//        mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
    }

    @Override
    protected void onStart() {
        super.onStart();

        mZXingView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
//        mZXingView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别

        mZXingView.changeToScanQRCodeStyle(); // 切换成扫描二维码样式
        mZXingView.setType(BarcodeType.ALL, null); // 识别所有类型的码
        mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
    }
    @Override
    protected void onStop() {
        mZXingView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mZXingView.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
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
            //eventBus 通知扫码消息
            String type = getIntent().getStringExtra("fromActivity");
            EventBus.getDefault().post(new ScanEvent(result, type));
            finish();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY) {
            final String picturePath = "";//BGAPhotoPickerActivity.getSelectedPhotos(data).get(0);
            // 本来就用到 QRCodeView 时可直接调 QRCodeView 的方法，走通用的回调
            mZXingView.decodeQRCode(picturePath);

            /*
            没有用到 QRCodeView 时可以调用 QRCodeDecoder 的 syncDecodeQRCode 方法

            这里为了偷懒，就没有处理匿名 AsyncTask 内部类导致 Activity 泄漏的问题
            请开发在使用时自行处理匿名内部类导致Activity内存泄漏的问题，处理方式可参考 https://github
            .com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%86%85%E5%AD%98%E6%B3%84%E6%BC%8F%E6%80%BB%E7%BB%93.md
             */
//            new AsyncTask<Void, Void, String>() {
//                @Override
//                protected String doInBackground(Void... params) {
//                    return QRCodeDecoder.syncDecodeQRCode(picturePath);
//                }
//
//                @Override
//                protected void onPostExecute(String result) {
//                    if (TextUtils.isEmpty(result)) {
//                        Toast.makeText(TestScanActivity.this, "未发现二维码", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(TestScanActivity.this, result, Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }.execute();
        }
    }
}
