package com.deshine.huishu.app.customerInvite.model.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shaozhen
 */
public class FinanceBillOsFromPsDto extends FinanceBill implements Serializable {
    private static final long serialVersionUID = 1L;
    private String consigneeCompanyName;//收货公司
    private String consigneeName;//收货联系人
    private String consigneePhone;//收货联系电话
    private String consigneeAddress;//收货地址

    /**
     * 收支类型-收款单、付款单使用
     */
    private String methodCode;

    //承运商列表
    private List<Logistics> logisticsList;

    private TransFeeExt transFeeExt;

    //关联的生产单、移库单
    private List<FinanceBillDto> refBillList = new ArrayList<>();

    public String getConsigneeCompanyName() {
        return consigneeCompanyName;
    }

    public void setConsigneeCompanyName(String consigneeCompanyName) {
        this.consigneeCompanyName = consigneeCompanyName;
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

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getMethodCode() {
        return methodCode;
    }

    public void setMethodCode(String methodCode) {
        this.methodCode = methodCode;
    }

    public List<Logistics> getLogisticsList() {
        return logisticsList;
    }

    public void setLogisticsList(List<Logistics> logisticsList) {
        this.logisticsList = logisticsList;
    }

    public TransFeeExt getTransFeeExt() {
        return transFeeExt;
    }

    public void setTransFeeExt(TransFeeExt transFeeExt) {
        this.transFeeExt = transFeeExt;
    }

    public List<FinanceBillDto> getRefBillList() {
        return refBillList;
    }

    public void setRefBillList(List<FinanceBillDto> refBillList) {
        this.refBillList = refBillList;
    }
}
