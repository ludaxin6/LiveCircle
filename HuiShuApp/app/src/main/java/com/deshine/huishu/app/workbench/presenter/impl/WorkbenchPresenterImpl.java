package com.deshine.huishu.app.workbench.presenter.impl;

import android.content.Context;

import com.deshine.huishu.app.R;
import com.deshine.huishu.app.app.AppApplication;
import com.deshine.huishu.app.app.AppConstant;
import com.deshine.huishu.app.base.OnHttpCallBack;
import com.deshine.huishu.app.base.response.BaseResponse;
import com.deshine.huishu.app.common.util.CommonCallBackFaild;
import com.deshine.huishu.app.utils.CodeUtil;
import com.deshine.huishu.app.utils.CollectionUtils;
import com.deshine.huishu.app.utils.ResourceUtil;
import com.deshine.huishu.app.workbench.model.WorkbenchModel;
import com.deshine.huishu.app.workbench.model.bean.Menu;
import com.deshine.huishu.app.workbench.model.bean.Workbench;
import com.deshine.huishu.app.workbench.model.impl.WorkbenchModelImpl;
import com.deshine.huishu.app.workbench.presenter.WorkbenchPresenter;
import com.deshine.huishu.app.workbench.view.WorkbenchView;

import java.util.ArrayList;
import java.util.List;

public class WorkbenchPresenterImpl implements WorkbenchPresenter {
    private WorkbenchModel workbenchModel;
    private WorkbenchView workbenchView;
    private String customMenuIds="7bdc99c8-ff99-11e9-93f9-0242ac110004,7bdd9a43-ff99-11e9-93f9-0242ac110004";

    public WorkbenchPresenterImpl(WorkbenchView view) {
        this.workbenchView = view;
        this.workbenchModel = new WorkbenchModelImpl();
    }
    /**
     * 加载工作台菜单
     */
    @Override
    public void loadMenu() {
        workbenchView.startProgressDialog();
        String userId = AppApplication.getAppContext()
                .getSharedPreferences(AppConstant.CACHE_DATA, Context.MODE_PRIVATE).getString(AppConstant.USER_ID, null);
        workbenchModel.fetchMenuList(userId, new OnHttpCallBack<BaseResponse<List<Menu>>>() {
            @Override
            public void onSuccessful(BaseResponse<List<Menu>> listBaseResponse) {
                workbenchView.stopProgressDialog();
                List<Menu> menuList = listBaseResponse.getData();
                List<Workbench> workbenchList = new ArrayList<>();
                if(!CollectionUtils.isEmpty(menuList)){
                    int index = 0;
                    for(Menu menuL1 : menuList){
                        List<Menu> childMenuList = menuL1.getMenuChild();
                        for(Menu menuL2 : childMenuList){
                            List<Menu> menus = menuL2.getMenuChild();
                            for (Menu menu : menus){
                                //定制逻辑
                                if(customMenuIds.indexOf(menu.getMenuId())>=0){
                                    workbenchList.add(new Workbench(menu.getMenuId(),menu.getName(),index,
                                            ResourceUtil.getStringResuorceId(menu.getClassName()),
                                            ResourceUtil.getColorResuorceId(menu.getClassName()+"_c")));
                                    index++;
                                }
                            }
                        }
                    }
                }
                if(CollectionUtils.isEmpty(workbenchList)){
                    workbenchView.showErrorMsg("该用户没有使用权限");//显示错误信息
                    workbenchView.toExit();
                }else{
                    workbenchList.add(new Workbench(CodeUtil.getUUID(),"注销",2,
                            R.string.hs_icon_diandeng,
                            R.color.hs_icon_ziyuan_c));
                }
                workbenchView.loadMenuBack(workbenchList);
            }

            @Override
            public void onFaild(String errorMsg, String errorCode) {
                workbenchView.stopProgressDialog();//隐藏进度条
                workbenchView.showErrorMsg(errorMsg);//显示错误信息
                CommonCallBackFaild.onFaild(errorCode);
            }
        });
    }
}
