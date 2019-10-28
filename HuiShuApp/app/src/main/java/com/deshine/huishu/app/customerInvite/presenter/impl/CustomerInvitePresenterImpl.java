package com.deshine.huishu.app.customerInvite.presenter.impl;

import android.content.Context;

import com.deshine.huishu.app.app.AppApplication;
import com.deshine.huishu.app.app.AppConstant;
import com.deshine.huishu.app.base.BaseActivity;
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
import com.deshine.huishu.app.customerInvite.view.CustomerInviteView;
import com.deshine.huishu.app.utils.CodeUtil;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerInvitePresenterImpl implements CustomerInvitePresenter {
    private CustomerInviteModel customerInviteModel;
    private CustomerInviteView customerInviteView;
    private CommonModel commonModel;
    private Context mContext;
    public CustomerInvitePresenterImpl(CustomerInviteView view){
        customerInviteView = view;
        mContext = AppApplication.getAppContext();
        customerInviteModel = new CustomerInviteModelImpl();
        commonModel = new CommonModelImpl();
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
