package com.deshine.huishu.app.customerInvite;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.deshine.huishu.app.R;
import com.deshine.huishu.app.base.BaseActivity;
import com.deshine.huishu.app.scan.ScanActivity;
import com.deshine.huishu.app.scan.bean.ScanEvent;

import butterknife.BindView;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * 客户自提
 */
public class CustomerInviteActivity extends BaseActivity {
    @BindView(R.id.scan_content)
    TextView mTextView;
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
    /*********************
     * 子类实现
     *****************************/
    @Override
    public int getLayoutId() {
        return R.layout.customer_invite_first;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        //启动扫码
        ScanActivity.startAction(CustomerInviteActivity.this);

    }

    @Subscribe
    public void onScanSuccessBackEvent(ScanEvent event) {
        if(this.getClass().getSimpleName().equals(event.getTargetActivityName())){
            mTextView.setText(event.getScanValue());
        }
    }
}
