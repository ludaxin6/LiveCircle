package com.deshine.huishu.app.signOrderUpload.model.bean.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 运费单查询对象
 */
public class FreightOrderDto {
    private Date bookedDate;//记账日期
    private String deliverName;//送货人
    private String locationName;//发货库房
    private String mailNo;//物流号
    private String sfBillNo;//费用单号
    private String coNo;//合同号
    private String soNo;//销售单号
    private String osNo;//出库单号
    private String consigneeCompanyName;//收货公司
    private String consigneeName;//收货联系人
    private String consigneePhone;//收货联系电话
    private String consigneeAddress;//收货地址
    private String consigneeMobilePhone;//收货联系电话
    private Integer pieceCount;//件数
    private BigDecimal weight;//重量
    private BigDecimal freightFee;//运费
    private BigDecimal freightFeeLeft;//未付运费
    private Integer signReceiptNum;//签收单张数
    private Integer signReceiptPicNum;//签收单图片数量

    private String coId;
    private String soId;
    private String osId;
    private String sfBillId;

    private String bookedDateStart;
    private String bookedDateEnd;
    private String logisticsGoodsId;
    private Integer sign;
    private String userId;
    private String deliveryUserId;


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

    public String getConsigneeMobilePhone() {
        return consigneeMobilePhone;
    }

    public void setConsigneeMobilePhone(String consigneeMobilePhone) {
        this.consigneeMobilePhone = consigneeMobilePhone;
    }

    public String getDeliveryUserId() {
        return deliveryUserId;
    }

    public void setDeliveryUserId(String deliveryUserId) {
        this.deliveryUserId = deliveryUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSfBillId() {
        return sfBillId;
    }

    public void setSfBillId(String sfBillId) {
        this.sfBillId = sfBillId;
    }

    public String getBookedDateStart() {
        return bookedDateStart;
    }

    public void setBookedDateStart(String bookedDateStart) {
        this.bookedDateStart = bookedDateStart;
    }

    public String getBookedDateEnd() {
        return bookedDateEnd;
    }

    public void setBookedDateEnd(String bookedDateEnd) {
        this.bookedDateEnd = bookedDateEnd;
    }

    public String getLogisticsGoodsId() {
        return logisticsGoodsId;
    }

    public void setLogisticsGoodsId(String logisticsGoodsId) {
        this.logisticsGoodsId = logisticsGoodsId;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public Date getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(Date bookedDate) {
        this.bookedDate = bookedDate;
    }

    public String getDeliverName() {
        return deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getMailNo() {
        return mailNo;
    }

    public void setMailNo(String mailNo) {
        this.mailNo = mailNo;
    }

    public String getSfBillNo() {
        return sfBillNo;
    }

    public void setSfBillNo(String sfBillNo) {
        this.sfBillNo = sfBillNo;
    }

    public String getCoNo() {
        return coNo;
    }

    public void setCoNo(String coNo) {
        this.coNo = coNo;
    }

    public String getSoNo() {
        return soNo;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public String getOsNo() {
        return osNo;
    }

    public void setOsNo(String osNo) {
        this.osNo = osNo;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public Integer getPieceCount() {
        return pieceCount;
    }

    public void setPieceCount(Integer pieceCount) {
        this.pieceCount = pieceCount;
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

    public BigDecimal getFreightFeeLeft() {
        return freightFeeLeft;
    }

    public void setFreightFeeLeft(BigDecimal freightFeeLeft) {
        this.freightFeeLeft = freightFeeLeft;
    }

    public Integer getSignReceiptNum() {
        return signReceiptNum;
    }

    public void setSignReceiptNum(Integer signReceiptNum) {
        this.signReceiptNum = signReceiptNum;
    }

    public Integer getSignReceiptPicNum() {
        return signReceiptPicNum;
    }

    public void setSignReceiptPicNum(Integer signReceiptPicNum) {
        this.signReceiptPicNum = signReceiptPicNum;
    }

    public String getCoId() {
        return coId;
    }

    public void setCoId(String coId) {
        this.coId = coId;
    }

    public String getSoId() {
        return soId;
    }

    public void setSoId(String soId) {
        this.soId = soId;
    }

    public String getOsId() {
        return osId;
    }

    public void setOsId(String osId) {
        this.osId = osId;
    }
}
