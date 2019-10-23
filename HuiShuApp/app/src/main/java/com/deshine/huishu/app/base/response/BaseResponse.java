package com.deshine.huishu.app.base.response;

import com.deshine.huishu.app.api.Response;
import com.deshine.huishu.app.exception.BizServiceException;

import java.util.List;

/**
 * 基础响应类，用于service层
 * Created by tf on 2017/5/24.
 */
public class BaseResponse<T> extends Response {

    private static final long serialVersionUID = 1L;

    public BaseResponse(){

    }

    public BaseResponse(T data){
        /**
         * 异常数据
         */
        if(data instanceof BizServiceException){
            BizServiceException e = (BizServiceException) data;
            this.setResultCode(e.getKey());
            this.setResultDesc(e.getMessage());
            this.data = (T)e.getData();
        }else {
            this.data = data;
            if (data instanceof List) {
                super.setTotal(((List) data).size());
            }
        }
    }
    /**
     * 业务主数据
     */
    private T data;
    /**
     * 业务附加数据
     */
    private Object extData;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
        if(data instanceof List && super.getTotal()==0){
            super.setTotal(((List) data).size());
        }
    }

    public Object getExtData() {
        return extData;
    }

    public void setExtData(Object extData) {
        this.extData = extData;
    }
}
