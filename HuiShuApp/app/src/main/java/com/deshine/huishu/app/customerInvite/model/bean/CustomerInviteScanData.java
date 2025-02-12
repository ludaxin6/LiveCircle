package com.deshine.huishu.app.customerInvite.model.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class CustomerInviteScanData implements Serializable {
    private static final long serialVersionUID = 1L;
    private String soNo;//销售单号
    private Integer pieceCount;//件数
    private BigDecimal weight;//重量
    private Integer signOrderTotal;//签收单张数
    private String osId;//出库单id

    public String getOsId() {
        return osId;
    }

    public void setOsId(String osId) {
        this.osId = osId;
    }

    public String getSoNo() {
        return soNo;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
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

    public Integer getSignOrderTotal() {
        return signOrderTotal;
    }

    public void setSignOrderTotal(Integer signOrderTotal) {
        this.signOrderTotal = signOrderTotal;
    }
}
