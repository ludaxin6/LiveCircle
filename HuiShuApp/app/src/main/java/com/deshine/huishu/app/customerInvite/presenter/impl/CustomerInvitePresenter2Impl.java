package com.deshine.huishu.app.customerInvite.presenter.impl;

import android.content.Context;

import com.deshine.huishu.app.app.AppApplication;
import com.deshine.huishu.app.app.AppConstant;
import com.deshine.huishu.app.base.OnHttpCallBack;
import com.deshine.huishu.app.base.response.BaseResponse;
import com.deshine.huishu.app.common.model.CommonModel;
import com.deshine.huishu.app.common.model.impl.CommonModelImpl;
import com.deshine.huishu.app.common.util.CommonCallBackFaild;
import com.deshine.huishu.app.commonAffix.bean.CommonAffix;
import com.deshine.huishu.app.commonAffix.constants.AffixBizType;
import com.deshine.huishu.app.customerInvite.model.CustomerInviteModel;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInviteAffix;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInviteScanData;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInviteVo;
import com.deshine.huishu.app.customerInvite.model.bean.FinanceBillDto;
import com.deshine.huishu.app.customerInvite.model.bean.FinanceBillResponse;
import com.deshine.huishu.app.customerInvite.model.bean.ResultFile;
import com.deshine.huishu.app.customerInvite.model.bean.SoDetailVo;
import com.deshine.huishu.app.customerInvite.model.bean.TransFeeExt;
import com.deshine.huishu.app.customerInvite.model.impl.CustomerInviteModelImpl;
import com.deshine.huishu.app.customerInvite.presenter.CustomerInvitePresenter;
import com.deshine.huishu.app.customerInvite.presenter.CustomerInvitePresenter2;
import com.deshine.huishu.app.customerInvite.view.CustomerInviteView;
import com.deshine.huishu.app.customerInvite.view.CustomerInviteView2;
import com.deshine.huishu.app.utils.CodeUtil;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerInvitePresenter2Impl implements CustomerInvitePresenter2 {
    private CustomerInviteModel customerInviteModel;
    private CustomerInviteView2 customerInviteView;
    private CommonModel commonModel;
    private Context mContext;
    public CustomerInvitePresenter2Impl(CustomerInviteView2 view){
        customerInviteView = view;
        mContext = AppApplication.getAppContext();
        customerInviteModel = new CustomerInviteModelImpl();
        commonModel = new CommonModelImpl();
    }

    /**
     * 身份证上传
     */
    @Override
    public void uploadIdCardPhoto(String fileDir) {
        customerInviteView.loading();
        List<File> idCardPhotos = new ArrayList<>();
        //身份证正面
        idCardPhotos.add(new File(fileDir, AppConstant.IDCARD_FRONT_IMAGE_NAME));
        //身份证反面
        idCardPhotos.add(new File(fileDir, AppConstant.IDCARD_BACK_IMAGE_NAME));
        commonModel.uploadFile(idCardPhotos, new OnHttpCallBack<List<ResultFile>>() {
            @Override
            public void onSuccessful(List<ResultFile> resultFiles) {
                customerInviteView.stopLoading();//隐藏进度条
                //更新界面数据
                String userId = mContext.getSharedPreferences(AppConstant.CACHE_DATA, Context.MODE_PRIVATE).getString(AppConstant.USER_ID, null);
                List<CommonAffix> idCardAffixList = new ArrayList<>();
                for(ResultFile file : resultFiles){
                    CommonAffix commonAffix = new CommonAffix(CodeUtil.getUUID(), AffixBizType.SF_KHZT_IDCARD,null,file.getUrl(),file.getOriginal(),file.getSuffix(),
                            file.getSize().intValue(),
                            userId,userId,new Date(),new Date());
                    idCardAffixList.add(commonAffix);
                }
                customerInviteView.setIdCardData(idCardAffixList);
            }

            @Override
            public void onFaild(String errorMsg, String errorCode) {
                customerInviteView.stopLoading();//隐藏进度条
                customerInviteView.showErrorMsg(errorMsg);//显示错误信息
                CommonCallBackFaild.onFaild(errorCode);
            }
        });
    }
}
