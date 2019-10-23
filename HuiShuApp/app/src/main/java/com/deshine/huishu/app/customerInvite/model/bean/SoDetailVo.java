package com.deshine.huishu.app.customerInvite.model.bean;

import android.text.TextUtils;

import java.math.BigDecimal;

/**
 * 销售订单明细汇总视图
 *
 * @Author: tf
 * @Date: 2018/9/5 19:28
 */
public class SoDetailVo {

    /**
     * 单据明细ID
     */
    private String billDetailId;
    /**
     * 单据ID
     */
    private String billId;
    /**
     * 单号
     */
    private String billNo;
    /**
     * 生产单号
     */
    private String productBillNo;
    /**
     * 型号
     */
    private String model;
    /**
     * 商品ID
     */
    private String goodsId;
    /**
     * 描述
     */
    private String goodsDesc;
    /**
     * 发货名称
     */
    private String sendGoodsName;
    /**
     * 数量
     */
    private BigDecimal quantity;
    //总重量(千克)
    private int weight;
    //件数-包裹数量
    private int pieceCount;
    //件数-包裹总数
    private int pieceTotalCount;

    /**
     * 状态 (shipped已发货、waitSend待发货、waitSend生产中、waitProduce待生产、lackParts缺备件)
     */
    private String status;
    private String statusDesc;//状态描述


    /**
     * 排产单id
     */
    private String psId;

    /**
     * 生产单状态：未记账、已记账
     * 排产单状态：已审批、已记账
     * 出库单状态：已记账
     * <p>
     * 1已发货(shipped)：已记账的出库单所关联的生产单
     * 2待发货(waitSend)：已记账的未关联出库单的生产单
     * 3生产中(production)：未记账的生产单
     * 4待生产(waitProduce)：未生成生产单的排产单剩余数据
     * 5缺备件(lackParts)：待生产中库存不足
     */
    public String getStatusDesc() {
        if (!TextUtils.isEmpty(status)) {
            if ("shipped".equals(status)) {
                statusDesc = "已发货";
            } else if ("waitSend".equals(status)) {
                statusDesc = "待发货";
            } else if ("production".equals(status)) {
                statusDesc = "生产中";
            } else if ("waitProduce".equals(status)) {
                statusDesc = "待生产";
            } else if ("lackParts".equals(status)) {
                statusDesc = "缺备件";
            }
        }
        return statusDesc;
    }

    public String getProductBillNo() {
        return productBillNo;
    }

    public void setProductBillNo(String productBillNo) {
        this.productBillNo = productBillNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(String billDetailId) {
        this.billDetailId = billDetailId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getSendGoodsName() {
        return sendGoodsName;
    }

    public void setSendGoodsName(String sendGoodsName) {
        this.sendGoodsName = sendGoodsName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPieceCount() {
        return pieceCount;
    }

    public void setPieceCount(int pieceCount) {
        this.pieceCount = pieceCount;
    }

    public int getPieceTotalCount() {
        return pieceTotalCount;
    }

    public void setPieceTotalCount(int pieceTotalCount) {
        this.pieceTotalCount = pieceTotalCount;
    }

    public String getPsId() {
        return psId;
    }

    public void setPsId(String psId) {
        this.psId = psId;
    }

}
