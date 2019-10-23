package com.deshine.huishu.app.customerInvite.model.bean;


import com.deshine.huishu.app.commonAffix.bean.CommonAffix;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 运费单扩展表
 *
 * @author
 */
public class TransFeeExt implements Serializable {

    private static final long serialVersionUID = 1L;

    public TransFeeExt() {
    }


    public TransFeeExt(String mailNo, String logisticsCode, String logisticsGoods, BigDecimal weight, BigDecimal freightFee, String payMethod, int pieceCount) {
        this.billId = billId;
        this.mailNo = mailNo;
        this.logisticsCode = logisticsCode;
        this.logisticsGoods = logisticsGoods;
        this.weight = weight;
        this.freightFee = freightFee;
        this.payMethod = payMethod;
        this.pieceCount = pieceCount;
    }


    public TransFeeExt(String billId, String mailNo, String logisticsCode, String logisticsGoods, BigDecimal weight, BigDecimal freightFee, String payMethod, int pieceCount) {
        this.billId = billId;
        this.mailNo = mailNo;
        this.logisticsCode = logisticsCode;
        this.logisticsGoods = logisticsGoods;
        this.weight = weight;
        this.freightFee = freightFee;
        this.payMethod = payMethod;
        this.pieceCount = pieceCount;
    }

    //主键
    private String billId;
    //快递单号
    private String mailNo;
    //物流公司代码(SF:顺丰 YTO:圆通)
    private String logisticsCode;
    private String logisticsName;
    //物流方式
    private String logisticsGoods;
    private String logisticsGoodsName;
    //重量(千克)
    private BigDecimal weight;
    //运费
    private BigDecimal freightFee;
    //付款方式
    private String payMethod;
    //件数-包裹数量
    private int pieceCount;
    private int updateCount = 0;
    private List<CommonAffix> affixList;
    private String monthCardNum;
    //收货确认时间
    private Date receivedConfirmTime;
    //收货确认状态 1已收货、0未收货
    private Integer receivedStatus;
    //收货确认方式:1手动确认 0 自动确认
    private Integer receivedConfirmMethod;
    private String receivedContactId;
    private String oldMailNo;//原始运单号
    private Integer signReceiptNum;

    public String getOldMailNo() {
        return oldMailNo;
    }

    public void setOldMailNo(String oldMailNo) {
        this.oldMailNo = oldMailNo;
    }

    public int getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(int updateCount) {
        this.updateCount = updateCount;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getMailNo() {
        return mailNo;
    }

    public void setMailNo(String mailNo) {
        this.mailNo = mailNo;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getLogisticsGoods() {
        return logisticsGoods;
    }

    public void setLogisticsGoods(String logisticsGoods) {
        this.logisticsGoods = logisticsGoods;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public String getLogisticsGoodsName() {
        return logisticsGoodsName;
    }

    public void setLogisticsGoodsName(String logisticsGoodsName) {
        this.logisticsGoodsName = logisticsGoodsName;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getFreightFee() {
        return freightFee;
    }

    public void setFreightFee(BigDecimal freightFee) {
        this.freightFee = freightFee;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public int getPieceCount() {
        return pieceCount;
    }

    public void setPieceCount(int pieceCount) {
        this.pieceCount = pieceCount;
    }

    public List<CommonAffix> getAffixList() {
        return affixList;
    }

    public void setAffixList(List<CommonAffix> affixList) {
        this.affixList = affixList;
    }


    public String getMonthCardNum() {
        return monthCardNum;
    }

    public void setMonthCardNum(String monthCardNum) {
        this.monthCardNum = monthCardNum;
    }


    public Date getReceivedConfirmTime() {
        return receivedConfirmTime;
    }

    public void setReceivedConfirmTime(Date receivedConfirmTime) {
        this.receivedConfirmTime = receivedConfirmTime;
    }

    public Integer getReceivedStatus() {
        return receivedStatus;
    }

    public void setReceivedStatus(Integer receivedStatus) {
        this.receivedStatus = receivedStatus;
    }

    public Integer getReceivedConfirmMethod() {
        return receivedConfirmMethod;
    }

    public void setReceivedConfirmMethod(Integer receivedConfirmMethod) {
        this.receivedConfirmMethod = receivedConfirmMethod;
    }

    public String getReceivedContactId() {
        return receivedContactId;
    }

    public void setReceivedContactId(String receivedContactId) {
        this.receivedContactId = receivedContactId;
    }

    public Integer getSignReceiptNum() {
        return signReceiptNum;
    }

    public void setSignReceiptNum(Integer signReceiptNum) {
        this.signReceiptNum = signReceiptNum;
    }
}
