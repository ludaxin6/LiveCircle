package com.deshine.huishu.app.scan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.deshine.huishu.app.R;
import com.deshine.huishu.app.base.BaseActivity;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ScanDemo extends BaseActivity {

    @BindView(R.id.scanBtn)
    Button scanBtn;
    @BindView(R.id.result)
    TextView result;
    @BindView(R.id.contentEt)
    EditText contentEt;
    @BindView(R.id.encodeBtn)
    Button encodeBtn;
    @BindView(R.id.contentIv)
    ImageView contentIv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fragScanBtn)
    Button fragScanBtn;
    private int REQUEST_CODE_SCAN = 111;
    private Context mContext;
    @Override
    public int getLayoutId() {
        return R.layout.activity_scan_demo;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        mContext = this;
    }
    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, ScanDemo.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }
    private void scanTodo(){
        AndPermission.with(this)
                .permission(Permission.CAMERA, Permission.READ_EXTERNAL_STORAGE)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        Intent intent = new Intent(mContext, CaptureActivity.class);
                        /*ZxingConfig是配置类  可以设置是否显示底部布局，闪光灯，相册，是否播放提示音  震动等动能
                         * 也可以不传这个参数
                         * 不传的话  默认都为默认不震动  其他都为true
                         * */

                        ZxingConfig config = new ZxingConfig();
                        // config.setPlayBeep(false);//是否播放扫描声音 默认为true
                        //  config.setShake(false);//是否震动  默认为true
                        // config.setDecodeBarCode(false);//是否扫描条形码 默认为true
//                        config.setReactColor(R.color.colorAccent);//设置扫描框四个角的颜色 默认为白色
//                        config.setFrameLineColor(R.color.colorAccent);//设置扫描框边框颜色 默认无色
//                        config.setScanLineColor(R.color.colorAccent);//设置扫描线的颜色 默认白色
                        config.setDecodeBarCode(true);
                        config.setShowbottomLayout(true);//底部布局（包括闪光灯和相册）
                        config.setPlayBeep(true);//是否播放提示音
                        config.setShake(true);//是否震动
                        config.setShowAlbum(true);//是否显示相册
                        config.setShowFlashLight(true);//是否显示闪光灯
                        intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
                        startActivityForResult(intent, REQUEST_CODE_SCAN);
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        Uri packageURI = Uri.parse("package:" + getPackageName());
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivity(intent);

                        Toast.makeText(mContext, "没有权限无法扫描呦", Toast.LENGTH_LONG).show();
                    }
                }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(Constant.CODED_CONTENT);
                result.setText("扫描结果为：" + content);
            }
        }
    }

    @OnClick({R.id.scanBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scanBtn:
                scanTodo();
                break;
        }
    }

}
