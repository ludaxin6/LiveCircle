package com.deshine.huishu.app.exception;
/**
 * 业务接口异常
 * @author tanf
 * @date 2013-6-20 下午5:20:42
 */
public class BizServiceException extends RuntimeException {
    /**
     * 序列号
     */
    private static final long serialVersionUID = -1146636106136589243L;
    /**
     * 异常代码
     */
    private String key;
    /**
     * 一些其他信息
     */
    private Object data;

    //推荐
    public BizServiceException(String message, String key){
        super(message);
        this.key = key;
    }
    @Deprecated//需要用错误码，请使用BizServiceException(String message,String key)
    public BizServiceException(String message) {
        super(message);
    }

    public BizServiceException() {
        super();
    }
    public BizServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BizServiceException(Throwable throwable) {
        super(throwable);
    }
    
    public BizServiceException(String message, String key, Object data){
        super(message);
        this.key = key;
        this.data = data;
    }
    
    public String getKey() {
        return key;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
