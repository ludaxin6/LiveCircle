package com.deshine.huishu.app.api;

import com.deshine.huishu.app.BuildConfig;
import com.deshine.huishu.app.base.request.BaseRequest;
import com.deshine.huishu.app.base.response.BaseResponse;
import com.deshine.huishu.app.commonAffix.bean.CommonAffix;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInviteAffix;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInviteVo;
import com.deshine.huishu.app.customerInvite.model.bean.FinanceBillResponse;
import com.deshine.huishu.app.customerInvite.model.bean.ResultFile;
import com.deshine.huishu.app.login.model.bean.request.LoginRequest;
import com.deshine.huishu.app.login.model.bean.response.UserResponse;
import com.deshine.huishu.app.signOrderUpload.model.bean.dto.FreightOrderDto;
import com.deshine.huishu.app.workbench.model.bean.Menu;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface CrmDgcsApiService {
    public static final String BASE_URL = BuildConfig.SERVER_URL;
    //附件上传到文件服务器
    @POST("file/ueditorUpload")
    @Multipart
    Observable<List<ResultFile>> upLoad(@Part List<MultipartBody.Part> files);
    //附件索引上传
    @POST("affix/save/{bizId}/{bizType}/{userId}/affix")
    Observable<BaseResponse<List<CommonAffix>>> uploadAffixInfo(@Path("bizId") String bizId, @Path("bizType") String bizType, @Path("userId") String userId, @Body List<CommonAffix> affixList);



    //登录
    @POST("user/login")
    Observable<UserResponse> login(@Body LoginRequest request);

    //客户自提查询
    @GET("app/erp/finance/bill/ps4Os/{bizNo}/{userId}")
    Observable<BaseResponse<CustomerInviteVo>> getPsData4Os(@Path("bizNo") String bizNo, @Path("userId") String userId);
    //客户自提附件添加索引
    @POST("app/erp/finance/bill/customerInvite/{osId}save/affixList")
    Observable<BaseResponse<Integer>> saveCustomerInviteAffix(@Path("osId") String osId,@Body CustomerInviteAffix request);
    //发送短信验证码
    @POST("app/erp/finance/bill/customerInvite/{idCardNo}/{mobilePhone}/{expireSeconds}/smsVerifyCode")
    Observable<BaseResponse<Boolean>> sendSmsVerifyCode(@Path("idCardNo") String idCardNo, @Path("mobilePhone") String mobilePhone, @Path("expireSeconds") int expireSeconds);
    //校验短信验证码
    @POST("app/erp/finance/bill/customerInvite/{idCardNo}/{verifyCode}/checkSmsVerifyCode")
    Observable<BaseResponse<Boolean>> checkSmsVerifyCode(@Path("idCardNo") String idCardNo, @Path("verifyCode") String verifyCode);

    //客户自提出库
    @POST("erp/finance/bill/osFromPs")
    Observable<FinanceBillResponse> addOs4PtBillFromPs(@Body Map<String,Object> request);

    //未上传签收单列表查询
    @POST("erp/bill/sf/deliverySignOrderList")
    Observable<BaseResponse<List<FreightOrderDto>>> selectDeliverySignOrderList(@Body BaseRequest<FreightOrderDto> request);
    //根据出库单查询签收单数据
    @GET("erp/bill/sf/{osId}/{driverUserId}/deliverySignOrder")
    Observable<BaseResponse<FreightOrderDto>> selectDeliverySignOrder(@Path("osId") String osId, @Path("driverUserId") String driverUserId);
    //签收单图片上传
    @POST("erp/bill/sf/zzps/{sfBillId}/addSignPic")
    Observable<BaseResponse<Integer>> addSignOrderPic(@Path("sfBillId") String sfBillId,@Body List<CommonAffix> affixList);

    @GET("menu/userId/{userId}/mobile")
    Observable<BaseResponse<List<Menu>>> fetchMenuList(@Path("userId") String userId);


}
