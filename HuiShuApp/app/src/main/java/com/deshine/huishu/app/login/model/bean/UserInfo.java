package com.deshine.huishu.app.login.model.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 * Created by Administrator on 2016/7/22.
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userName;//用户名
    private String pwd;//密码
    private String address;//地址
    private String phone;//手机号
    private String userId;


    private String alias;

    private String loginId;

    private String password;

    private String confPassword;

    /**
     * 身份证号码
     */
    private String idcardNo;
    /**
     * 合同到期日
     */
    private Date lcExpireDate;
    /**
     * 登录方式 - 兼容老版本
     */
    private int clientType;

    private String mobilephone;// 手机

    private String telephone;// 电话

    private String qq;// QQ

    private String weixin;// 微信

    private String weixinPic;// 微信二维码

    private String email;// 邮箱

    private String fax;// 传真

    @Deprecated
    private String titleofpost;// 职务

    private String roleId;

    private String company;// 所属公司

    private String companyId;// 公司ID

    private String orgId;

    private Date workedTime;

    private Integer isAdmin;

    private Integer isEnable;


    //是否离职 1 离职 0 在职
    private Integer isQuit;

    private String memo;

    private String createBy;

    private Date createDt;

    private String updateBy;

    private Date updateDt;

    private String ssoId;

    private String position;

    private String locationName;

    private String img;

    private String superleader;

    private String userNamePy;

    private Date lastpwdDt;

    private int pwdChange;

    private String mobileOwnerType;

    private String mobileOwnerId;

    private String cloudId;

    private String mailPassword;

    private String orgName;

    /**
     * 包含公司简称-部门-姓名
     * 如果上海-运营组-韦忠福
     */
    private String fullName;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    //========非表字段====
    private UserInfo leader;//用户主管

    public String getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo;
    }

    public Date getLcExpireDate() {
        return lcExpireDate;
    }

    public void setLcExpireDate(Date lcExpireDate) {
        this.lcExpireDate = lcExpireDate;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }

    public String getCloudId() {
        return cloudId;
    }

    public void setCloudId(String cloudId) {
        this.cloudId = cloudId;
    }

    public String getMobileOwnerType() {
        return mobileOwnerType;
    }

    public void setMobileOwnerType(String mobileOwnerType) {
        this.mobileOwnerType = mobileOwnerType;
    }

    public String getMobileOwnerId() {
        return mobileOwnerId;
    }

    public void setMobileOwnerId(String mobileOwnerId) {
        this.mobileOwnerId = mobileOwnerId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getWeixinPic() {
        return weixinPic;
    }

    public void setWeixinPic(String weixinPic) {
        this.weixinPic = weixinPic;
    }

    public int getPwdChange() {
        return pwdChange;
    }

    public void setPwdChange(int pwdChange) {
        this.pwdChange = pwdChange;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword == null ? null : confPassword.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone == null ? null : mobilephone.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getTitleofpost() {
        return titleofpost;
    }

    public void setTitleofpost(String titleofpost) {
        this.titleofpost = titleofpost == null ? null : titleofpost.trim();
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public Date getWorkedTime() {
        return workedTime;
    }

    public void setWorkedTime(Date workedTime) {
        this.workedTime = workedTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName == null ? null : locationName.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getSuperleader() {
        return superleader;
    }

    public void setSuperleader(String superleader) {
        this.superleader = superleader == null ? null : superleader.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public Date getLastpwdDt() {
        return lastpwdDt;
    }

    public void setLastpwdDt(Date lastpwdDt) {
        this.lastpwdDt = lastpwdDt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getIsQuit() {
        return isQuit;
    }

    public void setIsQuit(Integer isQuit) {
        this.isQuit = isQuit;
    }

    public String getSsoId() {
        return ssoId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public String getUserNamePy() {
        return userNamePy;
    }

    public void setUserNamePy(String userNamePy) {
        this.userNamePy = userNamePy;
    }

    public UserInfo getLeader() {
        return leader;
    }

    public void setLeader(UserInfo leader) {
        this.leader = leader;
    }

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }


    public UserInfo() {
    }

    public UserInfo(String userName, String pwd) {
        this.userName = userName;
        this.pwd = pwd;
    }

    public UserInfo(String userName, String pwd, String address, String phone) {
        this.userName = userName;
        this.pwd = pwd;
        this.address = address;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
