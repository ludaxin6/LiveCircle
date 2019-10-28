package com.deshine.huishu.app.customerInvite.presenter.impl;

import android.content.Context;

import com.deshine.huishu.app.app.AppApplication;
import com.deshine.huishu.app.app.AppConstant;
import com.deshine.huishu.app.base.OnHttpCallBack;
import com.deshine.huishu.app.base.response.BaseResponse;
import com.deshine.huishu.app.common.model.CommonModel;
import com.deshine.huishu.app.common.model.impl.CommonModelImpl;
import com.deshine.huishu.app.common.util.CommonCallBackFaild;
import com.deshine.huishu.app.customerInvite.model.CustomerInviteModel;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInviteScanData;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInviteVo;
import com.deshine.huishu.app.customerInvite.model.bean.FinanceBillDto;
import com.deshine.huishu.app.customerInvite.model.bean.SoDetailVo;
import com.deshine.huishu.app.customerInvite.model.bean.TransFeeExt;
import com.deshine.huishu.app.customerInvite.model.impl.CustomerInviteModelImpl;
import com.deshine.huishu.app.customerInvite.presenter.CustomerInvitePresenter1;
import com.deshine.huishu.app.customerInvite.view.CustomerInviteView1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerInvitePresenter1Impl implements CustomerInvitePresenter1 {
    private CustomerInviteModel customerInviteModel;
    private CustomerInviteView1 customerInviteView;
    private Context mContext;
    public CustomerInvitePresenter1Impl(CustomerInviteView1 view){
        customerInviteView = view;
        mContext = AppApplication.getAppContext();
        customerInviteModel = new CustomerInviteModelImpl();
    }

    /**
     * 根据扫码数据查询客户自提数据
     *
     * @param scanData
     */
    @Override
    public void fetchCustomerInvite(final CustomerInviteScanData scanData) {
        customerInviteView.loading();
        String soNo = scanData.getSoNo();
        String userId = mContext.getSharedPreferences(AppConstant.CACHE_DATA, Context.MODE_PRIVATE).getString(AppConstant.USER_ID, null);
        customerInviteModel.getCustomerInvite(soNo, userId, new OnHttpCallBack<BaseResponse<CustomerInviteVo>>() {
            @Override
            public void onSuccessful(BaseResponse<CustomerInviteVo> response) {
                customerInviteView.stopLoading();//隐藏进度条
                CustomerInviteVo vo = response.getData();
                FinanceBillDto psBill = vo.getPsBill();
                TransFeeExt transFeeExt = new TransFeeExt("","11dea034-8419-11e8-b922-0242ac110002","",
                        scanData.getWeight(),new BigDecimal(0),"PAYMENT_ARRIVAL",scanData.getPieceCount().intValue());
                psBill.setTransFeeExt(transFeeExt);
                List<Map<String,String>> refBillList = new ArrayList<>();
                for(int i=0;i<vo.getSoDetailVoList().size();i++){
                    SoDetailVo item = vo.getSoDetailVoList().get(i);
                    if("waitSend".equals(item.getStatus())){
                        boolean isIn = false;
                        for(int j=0;j<refBillList.size();j++){
                            if(item.getBillId().equals(refBillList.get(j).get("billId"))){
                                isIn = true;break;
                            }
                        }
                        if(!isIn){
                            Map<String,String> map = new HashMap<>();
                            map.put("billId",item.getBillId());
                            refBillList.add(map);
                        }
                    }
                }
                psBill.setBillTypeCode("OS");
                psBill.setCommandExecDto(vo.getCommandExecDto());
                psBill.setRefBillList(refBillList);
                psBill.setSignOrderTotal(scanData.getSignOrderTotal());
                customerInviteView.updateInfo(psBill);
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
     * 发送短信验证码
     *
     * @param idCardNo
     * @param mobilePhone
     * @param expireSeconds
     */
    @Override
    public void sendSmsVerifyCode(String idCardNo, String mobilePhone, int expireSeconds) {
        customerInviteView.sendSmsVerifyCodeBack();
//        customerInviteView.loading();
//        customerInviteModel.sendSmsVerifyCode(idCardNo,mobilePhone,expireSeconds,new OnHttpCallBack<BaseResponse<Boolean>>() {
//            @Override
//            public void onSuccessful(BaseResponse<Boolean> response) {
//                customerInviteView.stopLoading();//隐藏进度条
//                if(response.getData()){
//                    customerInviteView.sendSmsVerifyCodeBack();
//                }else{
//                    customerInviteView.stopLoading();//隐藏进度条
//                    customerInviteView.showErrorMsg("发送短信验证码失败");//显示错误信息
//                }
//            }
//
//            @Override
//            public void onFaild(String errorMsg, String errorCode) {
//                customerInviteView.stopLoading();//隐藏进度条
//                customerInviteView.showErrorMsg(errorMsg);//显示错误信息
//                CommonCallBackFaild.onFaild(errorCode);
//            }
//        });
    }

    /**
     * 短信验证码校验
     *
     * @param idCardNo
     * @param verifyCode
     */
    @Override
    public void checkSmsVerifyCode(String idCardNo, String verifyCode) {
        customerInviteView.checkSmsVerifyCodeBack(true);
//        customerInviteView.loading();
//        customerInviteModel.checkSmsVerifyCode(idCardNo,verifyCode,new OnHttpCallBack<BaseResponse<Boolean>>() {
//            @Override
//            public void onSuccessful(BaseResponse<Boolean> response) {
//                customerInviteView.stopLoading();//隐藏进度条
//                customerInviteView.checkSmsVerifyCodeBack(response.getData());
//            }
//
//            @Override
//            public void onFaild(String errorMsg, String errorCode) {
//                customerInviteView.stopLoading();//隐藏进度条
//                customerInviteView.showErrorMsg(errorMsg);//显示错误信息
//                CommonCallBackFaild.onFaild(errorCode);
//            }
//        });
    }
}
