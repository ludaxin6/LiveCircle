package com.deshine.huishu.app.signOrderUpload.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.deshine.huishu.app.R;
import com.deshine.huishu.app.adapter.HomeViewPagerAdapter;
import com.deshine.huishu.app.base.BaseActivity;
import com.deshine.huishu.app.cameralib.util.LogUtil;
import com.deshine.huishu.app.customerInvite.CustomerInviteActivity1;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInviteScanData;
import com.deshine.huishu.app.scan.ScanFragment;
import com.deshine.huishu.app.scan.bean.ScanEvent;
import com.deshine.huishu.app.signOrderUpload.fragment.SignOrderListFragment;
import com.deshine.huishu.app.utils.GesonUtil;
import com.deshine.huishu.app.utils.ToastUitl;

import java.util.ArrayList;

import butterknife.BindView;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public class SignOrderUpload1 extends BaseActivity {

    //toolbar
    @BindView(R.id.common_titlebar)
    Toolbar mToolbar;
    @BindView(R.id.vp_moudle)
    ViewPager mVpMoudle;
    @BindView(R.id.navigation)
    BottomNavigationView navigationView;

    private ArrayList<Fragment> mFragmentList = new ArrayList<Fragment>();
    private ScanFragment mScanFragment;
    private SignOrderListFragment mSignOrderListFragment;
    private HomeViewPagerAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment(savedInstanceState);
        setViewPager();
        //设置默认选中item
        navigationView.getMenu().getItem(0).setCheckable(true).setEnabled(true);
        mVpMoudle.setCurrentItem(0);
    }
    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, SignOrderUpload1.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }
    /*********************
     * 子类实现
     *****************************/
    @Override
    public int getLayoutId() {
        return R.layout.activity_sign_order_upload1;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        //设置标题栏
        mToolbar.setTitle(R.string.hs_sign_order_title);
        baseToolbar(mToolbar);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        mVpMoudle.setCurrentItem(0);
                        return true;
                    case R.id.navigation_dashboard:
                        mVpMoudle.setCurrentItem(1);
                        return true;
                }
                return false;
            }
        });

    }

    /**
     * 初始化fragment的记忆状态
     *
     * @param savedInstanceState
     */
    private void initFragment(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mSignOrderListFragment = (SignOrderListFragment) getSupportFragmentManager().getFragment(savedInstanceState, "SignOrderListFragment");
            mScanFragment = (ScanFragment) getSupportFragmentManager().getFragment(savedInstanceState, "ScanFragment");
        } else {
            mSignOrderListFragment = new SignOrderListFragment();
            mScanFragment = new ScanFragment();
            Bundle bundle = new Bundle();
            bundle.putString("fromActivity", SignOrderUpload1.class.getSimpleName());
            mScanFragment.setArguments(bundle);
        }
        mFragmentList.add(mScanFragment);
        mFragmentList.add(mSignOrderListFragment);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        FragmentManager manager = getSupportFragmentManager();
        if (mSignOrderListFragment.isAdded()) {
            manager.putFragment(outState, "SignOrderListFragment", mSignOrderListFragment);
        }
        if (mScanFragment.isAdded()) {
            manager.putFragment(outState, "ScanFragment", mScanFragment);
        }
    }
    /**
     * 填充ViewPager内容
     */
    private void setViewPager() {
        mAdapter = new HomeViewPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mVpMoudle.setAdapter(mAdapter);
        mVpMoudle.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //注意这个方法滑动时会调用多次，下面是参数解释：
                //position当前所处页面索引,滑动调用的最后一次绝对是滑动停止所在页面
                //positionOffset:表示从位置的页面偏移的[0,1]的值。
                //positionOffsetPixels:以像素为单位的值，表示与位置的偏移
            }

            @Override
            public void onPageSelected(int position) {
                //该方法只在滑动停止时调用，position滑动停止所在页面位置
//                当滑动到某一位置，导航栏对应位置被按下
                navigationView.getMenu().getItem(position).setChecked(true);
                if(position!=0){
                    mScanFragment.onStop();
                }else{
                    mScanFragment.onStart();
                }
                //这里使用navigation.setSelectedItemId(position);无效，
                //setSelectedItemId(position)的官网原句：Set the selected
                // menu item ID. This behaves the same as tapping on an item
                //未找到原因
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//这个方法在滑动是调用三次，分别对应下面三种状态
// 这个方法对于发现用户何时开始拖动，
// 何时寻呼机自动调整到当前页面，或何时完全停止/空闲非常有用。
//                state表示新的滑动状态，有三个值：
//                SCROLL_STATE_IDLE：开始滑动（空闲状态->滑动），实际值为0
//                SCROLL_STATE_DRAGGING：正在被拖动，实际值为1
//                SCROLL_STATE_SETTLING：拖动结束,实际值为2
            }
        });
    }
    @Subscribe
    public void onScanSuccessBackEvent(ScanEvent event) {
        if(this.getClass().getSimpleName().equals(event.getTargetActivityName())){
            //设置参数
            LogUtil.i("扫码结果："+event.getScanValue());
            CustomerInviteScanData scanData = GesonUtil.getGson().fromJson(event.getScanValue(), CustomerInviteScanData.class);
            SignOrderUpload.startAction(SignOrderUpload1.this,scanData);

        }
    }
}
