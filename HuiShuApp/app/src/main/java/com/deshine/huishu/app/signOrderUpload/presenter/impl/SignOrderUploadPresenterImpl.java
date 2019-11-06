package com.deshine.huishu.app.signOrderUpload.presenter.impl;

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
import com.deshine.huishu.app.customerInvite.model.bean.ResultFile;
import com.deshine.huishu.app.signOrderUpload.model.SignOrderUploadModel;
import com.deshine.huishu.app.signOrderUpload.model.bean.dto.FreightOrderDto;
import com.deshine.huishu.app.signOrderUpload.model.impl.SignOrderUploadModelImpl;
import com.deshine.huishu.app.signOrderUpload.presenter.SignOrderUploadPresenter;
import com.deshine.huishu.app.signOrderUpload.view.SignOrderUploadView;
import com.deshine.huishu.app.utils.CodeUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignOrderUploadPresenterImpl implements SignOrderUploadPresenter {
    private SignOrderUploadModel signOrderUploadModel;
    private CommonModel commonModel;
    private SignOrderUploadView signOrderUploadView;
    private Context mContext;
    public SignOrderUploadPresenterImpl(SignOrderUploadView view){
        signOrderUploadView = view;
        mContext = AppApplication.getAppContext();
        signOrderUploadModel = new SignOrderUploadModelImpl();
        commonModel = new CommonModelImpl();
    }
    /**
     * 根据出库单id 查询签收单
     *
     * @param osId
     */
    @Override
    public void fetchSignOrderByOsId(String osId) {
        signOrderUploadView.loading();
        String userId = mContext.getSharedPreferences(AppConstant.CACHE_DATA, Context.MODE_PRIVATE).getString(AppConstant.USER_ID, null);
        signOrderUploadModel.selectDeliverySignOrder(osId, userId, new OnHttpCallBack<BaseResponse<FreightOrderDto>>() {
            @Override
            public void onSuccessful(BaseResponse<FreightOrderDto> response) {
                signOrderUploadView.stopLoading();
                signOrderUploadView.fetchSignOrderByOsIdBack(response.getData());
            }

            @Override
            public void onFaild(String errorMsg, String errorCode) {
                signOrderUploadView.stopLoading();//隐藏进度条
                signOrderUploadView.showErrorMsg(errorMsg);//显示错误信息
                CommonCallBackFaild.onFaild(errorCode);
            }
        });
    }

    /**
     * 签收单上传
     *
     * @param sfBillId
     * @param sfBillNo
     * @param affixList
     */
    @Override
    public void signOrderUpload(final String sfBillId, final String sfBillNo, List<CommonAffix> affixList) {
        signOrderUploadView.loading();
        List<File> files = new ArrayList<>();
        for(CommonAffix affix: affixList){
            String affixUrl = affix.getAffixUrl();
            File file = new File(affixUrl);
            files.add(file);
        }
        commonModel.uploadFile(files,new OnHttpCallBack<List<ResultFile>>() {
            @Override
            public void onSuccessful(List<ResultFile> resultFiles) {
                //更新界面数据
                String userId = mContext.getSharedPreferences(AppConstant.CACHE_DATA, Context.MODE_PRIVATE).getString(AppConstant.USER_ID, null);
                List<CommonAffix> commonAffixes = new ArrayList<>();
                int i=0;
                for(ResultFile file : resultFiles){
                    CommonAffix commonAffix = new CommonAffix(CodeUtil.getUUID(), AffixBizType.SF_SIGN_RECEIPT_NUM,sfBillId,file.getUrl(),
                            sfBillNo+"-"+i+"."+file.getSuffix(),file.getSuffix(),
                            file.getSize().intValue(),
                            userId,userId,new Date(),new Date());
                    commonAffixes.add(commonAffix);
                    i++;
                }
                //调用上传
                signOrderUploadSubmit(sfBillId,commonAffixes);
            }

            @Override
            public void onFaild(String errorMsg, String errorCode) {
                signOrderUploadView.stopLoading();//隐藏进度条
                signOrderUploadView.showErrorMsg(errorMsg);//显示错误信息
                CommonCallBackFaild.onFaild(errorCode);
            }
        });
    }

    /**
     * 签收单上传提交
     *
     * @param sfBillId
     * @param affixList
     */
    @Override
    public void signOrderUploadSubmit(String sfBillId, List<CommonAffix> affixList) {
        //signOrderUploadView.loading();
        signOrderUploadModel.addSignOrderPic(sfBillId,affixList,new OnHttpCallBack<BaseResponse<Integer>>() {
            @Override
            public void onSuccessful(BaseResponse<Integer> response) {
                signOrderUploadView.stopLoading();
                signOrderUploadView.toSuccessPage();
            }

            @Override
            public void onFaild(String errorMsg, String errorCode) {
                signOrderUploadView.stopLoading();//隐藏进度条
                signOrderUploadView.showErrorMsg(errorMsg);//显示错误信息
                CommonCallBackFaild.onFaild(errorCode);
            }
        });
    }
}
