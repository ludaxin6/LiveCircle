package com.deshine.huishu.app.login.model.bean.request;


import com.deshine.huishu.app.api.Request;
import com.deshine.huishu.app.login.model.bean.UserInfo;

public class LoginRequest extends Request {
    private static final long serialVersionUID = 1L;
    /**
     * 用户信息
     */
    private UserInfo userInfo;

    private String tokenId;
    /**
     * 登录方式
     */
    private int clientType;
    /**
     * 短信验证码
     */
    private String smsCode;
    /**
     * 白名单
     */
    private boolean isWhitelist;

    private String thKey;
    /**
     * app类型，关键字
     */
    private String appType;
    private String appUuid;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public boolean getIsWhitelist() {
        return this.isWhitelist;
    }

    public void setIsWhitelist(boolean whitelist) {
        this.isWhitelist = whitelist;
    }

    public String getThKey() {
        return thKey;
    }

    public void setThKey(String thKey) {
        this.thKey = thKey;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getAppUuid() {
        return appUuid;
    }

    public void setAppUuid(String appUuid) {
        this.appUuid = appUuid;
    }
}
