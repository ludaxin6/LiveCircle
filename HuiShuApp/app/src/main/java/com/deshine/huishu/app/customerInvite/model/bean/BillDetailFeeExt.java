package com.deshine.huishu.app.customerInvite.model.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:费用单明细扩展表
 *
 * @author shaozhen
 */
public class BillDetailFeeExt implements Serializable {
    private static final long serialVersionUID = 1L;
    private String billDetailId;
    //发生日期
    private Date costDate;
    //费用发生-相关业务
    private String bizType;
    //费用发生-相关业务id
    private String bizId;
    //费用发生-相关业务no
    private String bizNo;
    //费用发生-相关业务描述
    private String bizDesc;

    //费用单-已核销金额
    private BigDecimal bwLeftAmount;

    public String getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(String billDetailId) {
        this.billDetailId = billDetailId;
    }

    public Date getCostDate() {
        return costDate;
    }

    public void setCostDate(Date costDate) {
        this.costDate = costDate;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getBizDesc() {
        return bizDesc;
    }

    public void setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
    }

    public BigDecimal getBwLeftAmount() {
        return bwLeftAmount;
    }

    public void setBwLeftAmount(BigDecimal bwLeftAmount) {
        this.bwLeftAmount = bwLeftAmount;
    }
}
