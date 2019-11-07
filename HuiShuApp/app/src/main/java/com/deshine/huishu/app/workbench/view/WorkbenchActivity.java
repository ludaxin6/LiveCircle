package com.deshine.huishu.app.workbench.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.deshine.huishu.app.R;
import com.deshine.huishu.app.adapter.WorkbenchAdapter;
import com.deshine.huishu.app.adapter.WorkbenchAdapter1;
import com.deshine.huishu.app.app.AppApplication;
import com.deshine.huishu.app.app.AppManager;
import com.deshine.huishu.app.base.BaseActivity;
import com.deshine.huishu.app.cameralib.util.LogUtil;
import com.deshine.huishu.app.customerInvite.CustomerInviteActivity1;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInviteScanData;
import com.deshine.huishu.app.home.MainActivity;
import com.deshine.huishu.app.login.LoginActivity;
import com.deshine.huishu.app.login.model.LoginModel;
import com.deshine.huishu.app.login.model.impl.LoginModelImpl;
import com.deshine.huishu.app.scan.ScanActivity;
import com.deshine.huishu.app.scan.TestScanActivity;
import com.deshine.huishu.app.scan.bean.ScanEvent;
import com.deshine.huishu.app.signOrderUpload.activity.SignOrderUpload1;
import com.deshine.huishu.app.utils.GesonUtil;
import com.deshine.huishu.app.utils.ToastUitl;
import com.deshine.huishu.app.workbench.model.bean.Workbench;
import com.deshine.huishu.app.workbench.presenter.WorkbenchPresenter;
import com.deshine.huishu.app.workbench.presenter.impl.WorkbenchPresenterImpl;

import java.util.List;

import butterknife.BindView;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public class WorkbenchActivity extends BaseActivity implements WorkbenchView{
    @BindView(R.id.common_titlebar)
    Toolbar mToolbar;
//    @BindView(R.id.work_list)
//    RecyclerView mWorkbenchRv;
    @BindView(R.id.workbench_grid_view)
    GridView gridView;
    private WorkbenchAdapter1 mWorkbenchAdapter;
    private WorkbenchPresenter workbenchPresenter;
    private String scanType = "";
    @Override
    public int getLayoutId() {
        return R.layout.activity_workbench_1;
    }

    @Override
    public void initPresenter() {
        workbenchPresenter = new WorkbenchPresenterImpl(this);
    }

    @Override
    public void initView() {
        //设置标题栏
        mToolbar.setTitle(R.string.hs_working);
        baseToolbar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManager.getAppManager().AppExit(AppApplication.getAppContext(),false);
            }
        });
        //加载工作台九宫格数据
        workbenchPresenter.loadMenu();

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }
    @Override
    public void loadMenuBack(List<Workbench> tables){
//        mWorkbenchAdapter = new WorkbenchAdapter1(this, tables);
//        mWorkbenchRv.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
//        mWorkbenchRv.setItemAnimator(new DefaultItemAnimator());
//        mWorkbenchRv.setAdapter(mWorkbenchAdapter);
//        mWorkbenchAdapter.setOnItemClickListener(new WorkbenchAdapter1.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                itemClickEvent(view, position);
//            }
//        });
        mWorkbenchAdapter = new WorkbenchAdapter1(this, R.layout.workbench_item,tables);
        mWorkbenchAdapter.setOnItemClickListener(new WorkbenchAdapter1.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                itemClickEvent(view, position);
            }
        });
        gridView.setAdapter(mWorkbenchAdapter);
    }
    public void itemClickEvent(View view, int position){
        Workbench workbench = mWorkbenchAdapter.getAdapterData().get(position);
        if(workbench.getName().equals("客户自提")){
//                    CustomerInviteActivity.startAction(WorkbenchActivity.this);
            scanType = workbench.getName();
            ScanActivity.startAction(WorkbenchActivity.this);
        }if(workbench.getName().equals("签收单上传")){
//                    CustomerInviteActivity.startAction(WorkbenchActivity.this);
            scanType = workbench.getName();
            SignOrderUpload1.startAction(WorkbenchActivity.this);
        }else if(workbench.getName().equals("注销")){
            LoginModel mLoginModel = new LoginModelImpl();
            mLoginModel.removeUserInfo(AppApplication.getAppContext());
            ToastUitl.showShort("用户已注销");
            LoginActivity.startAction(WorkbenchActivity.this);
            finish();
        }else if(workbench.getName().equals("扫一扫")){
            scanType = workbench.getName();
            TestScanActivity.startAction(WorkbenchActivity.this);
        }else if(workbench.getName().equals("更多...")){
            MainActivity.startAction(WorkbenchActivity.this);
        }
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        ToastUitl.showShort(errorMsg);
    }

    @Override
    public void toExit() {
        LoginActivity.startAction(this);
        finish();
    }

    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, WorkbenchActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }

    @Subscribe
    public void onScanSuccessBackEvent(ScanEvent event) {
        if(this.getClass().getSimpleName().equals(event.getTargetActivityName())){
            //设置参数
            LogUtil.i("扫码结果："+event.getScanValue());
            CustomerInviteScanData scanData = GesonUtil.getGson().fromJson(event.getScanValue(), CustomerInviteScanData.class);
            CustomerInviteActivity1.startAction(WorkbenchActivity.this,scanData);

        }
    }
}
