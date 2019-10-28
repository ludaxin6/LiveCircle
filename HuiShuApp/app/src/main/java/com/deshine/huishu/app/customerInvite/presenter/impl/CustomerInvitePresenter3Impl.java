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
import com.deshine.huishu.app.customerInvite.presenter.CustomerInvitePresenter3;
import com.deshine.huishu.app.customerInvite.view.CustomerInviteView;
import com.deshine.huishu.app.customerInvite.view.CustomerInviteView3;
import com.deshine.huishu.app.utils.CodeUtil;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerInvitePresenter3Impl implements CustomerInvitePresenter3 {
    private CustomerInviteModel customerInviteModel;
    private CustomerInviteView3 customerInviteView;
    private CommonModel commonModel;
    private Context mContext;
    public CustomerInvitePresenter3Impl(CustomerInviteView3 view){
        customerInviteView = view;
        mContext = AppApplication.getAppContext();
        customerInviteModel = new CustomerInviteModelImpl();
        commonModel = new CommonModelImpl();
    }

    /**
     * 上传签收单到文件系统
     *
     */
    @Override
    public void uploadSignOrderPhoto(final List<CommonAffix> commonAffixes) {
        customerInviteView.loading();
        List<File> affixList = new ArrayList<>();
        for(CommonAffix affix: commonAffixes){
            String affixUrl = affix.getAffixUrl();
            File file = new File(affixUrl);
            affixList.add(file);
        }
        commonModel.uploadFile(affixList, new OnHttpCallBack<List<ResultFile>>() {
            @Override
            public void onSuccessful(List<ResultFile> resultFiles) {
                customerInviteView.stopLoading();//隐藏进度条
                //更新界面数据
                String userId = mContext.getSharedPreferences(AppConstant.CACHE_DATA, Context.MODE_PRIVATE).getString(AppConstant.USER_ID, null);
                List<CommonAffix> idCardAffixList = new ArrayList<>();
                for(ResultFile file : resultFiles){
                    CommonAffix commonAffix = new CommonAffix(CodeUtil.getUUID(), AffixBizType.SF_SIGN_RECEIPT_NUM,null,file.getUrl(),file.getOriginal(),file.getSuffix(),
                            file.getSize().intValue(),
                            userId,userId,new Date(),new Date());
                    idCardAffixList.add(commonAffix);
                }
                customerInviteView.setSignOrderPhoto(idCardAffixList);
            }

            @Override
            public void onFaild(String errorMsg, String errorCode) {
                customerInviteView.stopLoading();//隐藏进度条
                customerInviteView.showErrorMsg(errorMsg);//显示错误信息
                CommonCallBackFaild.onFaild(errorCode);
            }
        });
    }

    /**
     * 客户自提出库
     *
     * @param dto
     */
    @Override
    public void customerInviteSubmit(final FinanceBillDto dto) {
        customerInviteView.loading();
        Map<String,Object> map = new HashMap<>();
        map.put("entity",dto);
        map.put("commandExecDto",dto.getCommandExecDto());
        customerInviteModel.customerInviteSubmit(map, new OnHttpCallBack<FinanceBillResponse>() {
            @Override
            public void onSuccessful(FinanceBillResponse response) {

                uploadCustomerInviteAffix(response.getFinanceBill().getBillId(),dto);
            }

            @Override
            public void onFaild(String errorMsg, String errorCode) {
                customerInviteView.stopLoading();//隐藏进度条
                customerInviteView.showErrorMsg(errorMsg);//显示错误信息
                CommonCallBackFaild.onFaild(errorCode);
            }
        });
    }

    /**
     * 根据出库id,客户自提附件信息，条件附件索引记录
     */
    @Override
    public void uploadCustomerInviteAffix(String osId, FinanceBillDto dto) {
        customerInviteView.loading();
        CustomerInviteAffix request = new CustomerInviteAffix(dto.getIdCardAffixList(), dto.getAffixList());
        customerInviteModel.uploadCustomerInviteAffix(osId,request,new OnHttpCallBack<BaseResponse<Integer>>() {
            @Override
            public void onSuccessful(BaseResponse<Integer> response) {
                customerInviteView.stopLoading();//隐藏进度条
                //页面成功跳转
                customerInviteView.toSuccessPage();
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
