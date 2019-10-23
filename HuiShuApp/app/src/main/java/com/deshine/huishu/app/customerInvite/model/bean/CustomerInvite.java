package com.deshine.huishu.app.customerInvite.model.bean;

import com.deshine.huishu.app.commonAffix.bean.CommonAffix;

import java.io.Serializable;
import java.util.List;

public class CustomerInvite implements Serializable {
    private static final long serialVersionUID = 1L;

    private String consigneeName;//收货联系人
    private String consigneePhone;//收货联系电话
    private String consigneeMobilePhone;//收货联系电话
    private String consigneeAddress;//收货地址
    private String idCardNo;//身份证号
    private int signOrderCount;
    private List<CommonAffix> affixList;

    public List<CommonAffix> getAffixList() {
        return affixList;
    }

    public void setAffixList(List<CommonAffix> affixList) {
        this.affixList = affixList;
    }

    public int getSignOrderCount() {
        return signOrderCount;
    }

    public void setSignOrderCount(int signOrderCount) {
        this.signOrderCount = signOrderCount;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public String getConsigneeMobilePhone() {
        return consigneeMobilePhone;
    }

    public void setConsigneeMobilePhone(String consigneeMobilePhone) {
        this.consigneeMobilePhone = consigneeMobilePhone;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }
}
