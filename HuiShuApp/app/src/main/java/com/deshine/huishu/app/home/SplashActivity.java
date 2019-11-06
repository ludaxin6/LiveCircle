package com.deshine.huishu.app.home;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.deshine.huishu.app.R;
import com.deshine.huishu.app.app.AppApplication;
import com.deshine.huishu.app.app.AppConstant;
import com.deshine.huishu.app.base.BaseActivity;
import com.deshine.huishu.app.login.LoginActivity;
import com.deshine.huishu.app.workbench.view.WorkbenchActivity;

import butterknife.BindView;

/**
 * Created by lvr on 2017/2/6.
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.iv_logo)
    ImageView mIvLogo;
    @BindView(R.id.tv_name)
    TextView mTvName;

    @Override
    public int getLayoutId() {
        return R.layout.activty_splash;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        SetTranslanteBar();
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.3f, 1f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.3f, 1f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.3f, 1f);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(mTvName, alpha, scaleX, scaleY);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(mIvLogo, alpha, scaleX, scaleY);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1, objectAnimator2);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.setDuration(2000);
        animatorSet.addListener(new Animator.AnimatorListener() {
            private void initMain(){
                //有jwt初始化应用
                //MainActivity.startAction(SplashActivity.this);
                WorkbenchActivity.startAction(SplashActivity.this);
                finish();
            }
            private void initLogin(){
                //登陆初始化
                LoginActivity.startAction(SplashActivity.this);
                finish();
            }
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //检查登陆jwt
                SharedPreferences sharedPreferences = AppApplication.getAppContext().getSharedPreferences(AppConstant.CACHE_DATA, Context.MODE_PRIVATE);
                if(null == sharedPreferences){
                    initMain();
                }else{
                    //没有jwt 跳转到登陆页面
                    String jwt = sharedPreferences.getString(AppConstant.JWT,null);
                    if(TextUtils.isEmpty(jwt)){
                        initLogin();
                    }else{
                        initMain();
                    }
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSet.start();
    }

}
