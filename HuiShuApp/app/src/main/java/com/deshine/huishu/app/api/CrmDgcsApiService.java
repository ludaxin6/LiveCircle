package com.deshine.huishu.app.api;

import com.deshine.huishu.app.base.response.BaseResponse;
import com.deshine.huishu.app.commonAffix.bean.CommonAffix;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInviteAffix;
import com.deshine.huishu.app.customerInvite.model.bean.CustomerInviteVo;
import com.deshine.huishu.app.customerInvite.model.bean.FinanceBillResponse;
import com.deshine.huishu.app.customerInvite.model.bean.ResultFile;
import com.deshine.huishu.app.login.model.bean.request.LoginRequest;
import com.deshine.huishu.app.login.model.bean.response.UserResponse;

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
    public static final String BASE_URL = "http://192.168.38.174:8080/crm_dgcs/api/";
    //登录
    @POST("user/login")
    Observable<UserResponse> login(@Body LoginRequest request);
    //客户自提查询
    @GET("app/erp/finance/bill/ps4Os/{bizNo}/{userId}")
    Observable<BaseResponse<CustomerInviteVo>> getPsData4Os(@Path("bizNo") String bizNo, @Path("userId") String userId);
    //客户自提出库
    @POST("erp/finance/bill/osFromPs")
    Observable<FinanceBillResponse> addOs4PtBillFromPs(@Body Map<String,Object> request);
    //客户自提附件添加索引
    @POST("app/erp/finance/bill/customerInvite/{osId}save/affixList")
    Observable<BaseResponse<Integer>> saveCustomerInviteAffix(@Path("osId") String osId,@Body CustomerInviteAffix request);
    //附件上传到文件服务器
    @POST("file/ueditorUpload")
    @Multipart
    Observable<List<ResultFile>> upLoad(@Part List<MultipartBody.Part> files);
    //附件索引上传
    @POST("affix/save/{bizId}/{bizType}/{userId}/affix")
    Observable<BaseResponse<List<CommonAffix>>> uploadAffixInfo(@Path("bizId") String bizId, @Path("bizType") String bizType, @Path("userId") String userId, @Body List<CommonAffix> affixList);
}
