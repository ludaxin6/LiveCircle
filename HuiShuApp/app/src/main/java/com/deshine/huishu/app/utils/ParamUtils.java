package com.deshine.huishu.app.utils;

import android.text.TextUtils;

import com.deshine.huishu.app.api.ResultCode;
import com.deshine.huishu.app.exception.BizServiceException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 参数校验辅助类
 * @author tf
 * @date 2018-12-19 14:55
 */
public class ParamUtils {

    /**
     * 检查不能为空
     * @param object
     */
    public static void validateNotEmpty(Object object){
        validateNotEmpty(object, "参数");
    }

    /**
     * 检查不能为空
     * @param object
     * @param name
     */
    public static void validateNotEmpty(Object object, String name){
        if(object instanceof String){
            checkStr(object.toString(), name);
        }else if(object instanceof List || object instanceof Map){
            checkList((List) object, name);
        }else{
            if(null == object){
                throw new BizServiceException(name + "不能为空", ResultCode.Base.Param.EMPTY);
            }
        }
    }

    private static void checkStr(String str, String name){
        if(TextUtils.isEmpty(str)){
            throw new BizServiceException(name + "值不能为空", ResultCode.Base.Param.EMPTY);
        }
    }

    private static void checkList(Collection collection, String name){
        if(CollectionUtils.isEmpty(collection)){
            throw new BizServiceException(name + "值不能为空", ResultCode.Base.Param.EMPTY);
        }
    }



}
