package com.lvr.livecircle.login.model.bean.response;

import com.lvr.livecircle.api.Response;
import com.lvr.livecircle.login.model.bean.UserInfo;

public class UserResponse extends Response {
    private static final long serialVersionUID = 1L;

    private UserInfo userInfo;

    private String companyShortName;

    private String tokenId;

    private String jwt;

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

    public String getCompanyShortName() {
        return companyShortName;
    }

    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

}
