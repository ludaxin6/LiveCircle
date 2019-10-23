package com.deshine.huishu.app.common.model.impl;

import android.util.Log;

import com.deshine.huishu.app.api.CrmDgcsApiService;
import com.deshine.huishu.app.base.OnHttpCallBack;
import com.deshine.huishu.app.common.model.CommonModel;
import com.deshine.huishu.app.customerInvite.model.bean.ResultFile;
import com.deshine.huishu.app.http.CommonRetrofitClientUtil;
import com.deshine.huishu.app.utils.CollectionUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.File;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CommonModelImpl implements CommonModel {
    /**
     * 文件上传
     *
     * @param files
     * @param callBack
     */
    @Override
    public void uploadFile(List<File> files, final OnHttpCallBack<List<ResultFile>> callBack) {
        List<MultipartBody.Part> bodyFiles = new ArrayList<>();
        for(File file : files){
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
            bodyFiles.add(body);
        }

        CommonRetrofitClientUtil.newInstence(CrmDgcsApiService.BASE_URL)//实例化Retrofit对象
                .create(CrmDgcsApiService.class)//创建Rxjava---->LoginService对象
                .upLoad(bodyFiles)
                .subscribeOn(Schedulers.newThread())//在新线程中执行登录请求
                .observeOn(AndroidSchedulers.mainThread())//在主线程中执行
                .subscribe(new Observer<List<ResultFile>>() {//网络(登录)请求回调
                    private Disposable mDisposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(List<ResultFile> resultFiles) {
                        if (!CollectionUtils.isEmpty(resultFiles)) {
                            callBack.onSuccessful(resultFiles);//登录成功------获取完数据,返回给P---P获取到数据之后就将数据交回给V
                        } else {
                            callBack.onFaild("文件上传失败");//登录失败
                        }
                        mDisposable.dispose();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("LoginModelImpl", e.getMessage()+ "--");
                        e.printStackTrace();
                        //失败的时候调用-----一下可以忽略 直接 callBack.onFaild("请求失败");
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            //httpException.response().errorBody().string()
                            int code = httpException.code();
                            if (code == 500 || code == 404) {
                                callBack.onFaild("服务器出错");
                            }
                        } else if (e instanceof ConnectException) {
                            callBack.onFaild("网络断开,请打开网络!");
                        } else if (e instanceof SocketTimeoutException) {
                            callBack.onFaild("网络连接超时!!");
                        } else {
                            Log.e("LoginModelImpl", e.getMessage());
                            callBack.onFaild("发生未知错误" + e.getMessage());
                        }
                        mDisposable.dispose();
                    }
                    @Override
                    public void onComplete() {
                        //完成的时候调用
                    }
                });
    }
}
