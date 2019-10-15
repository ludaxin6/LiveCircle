package com.deshine.huishu.app.customerInvite.model.bean;

import java.io.Serializable;

public class CustomerInvite implements Serializable {
    private static final long serialVersionUID = 1L;

    private String consigneeName;//收货联系人
    private String consigneePhone;//收货联系电话
    private String consigneeMobilePhone;//收货联系电话
    private String consigneeAddress;//收货地址
    private String idCardNo;//身份证号

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
