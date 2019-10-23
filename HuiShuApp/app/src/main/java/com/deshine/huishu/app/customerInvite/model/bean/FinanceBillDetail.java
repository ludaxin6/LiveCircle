package com.deshine.huishu.app.customerInvite.model.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 业务单据-明细
 *
 * @author shaozhen
 */
public class FinanceBillDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    public FinanceBillDetail() {

    }

    private String billDetailId;
    private Integer billDetailNo;
    private String detailBizCode;
    private String billId;
    private String billTypeCode;
    //子单据类型代码
    private String subBillTypeCode;
    private String billNo;
    private String refBillTypeCode;
    private String refBillId;
    //关联单号
    private String refBillNo;
    private String refBillDetailId;
    //关联明细余额id
    private String refBillBalanceId;
    private String accountCode;
    private String customerId;
    private String contactId;
    private String companyId;
    private String ownerCompanyId;
    private String ownerOrgId;
    private String ownerId;
    private String operatorId;
    //销售变更单id
    private String saId;
    private String contractOrderId;
    //合同号
    private String contractOrderNo;
    private String coSoId;
    //采购合同id
    private String pcoId;
    private String pcoNo;
    //销售订单id
    private String soId;
    private String soNo;
    //采购订单id
    private String poId;
    private String poNo;
    //销售合同行id
    private String scoLineId;
    //采购合同行id
    private String pcoLineId;
    //销售订单行id
    private String soLineId;
    //采购订单行id
    private String poLineId;
    private String wbsId;
    private String locationId;
    private String goodsId;
    private String goodsNo;
    private String goodsSubClassName;
    private String goodsCategory;
    private String goodsModel;
    private String goodsDesc;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal amount;
    private Date createTime;
    private Date updateTime;
    private Integer sort;
    //交易日期
    private Date tradeDate;
    private BigDecimal handledQuantity;
    //备注
    private String memo;
    //币种
    private String currency;
    //外币单价
    private BigDecimal foreignCurrencyPrice;
    //外币金额
    private BigDecimal foreignCurrencyAmount;
    //汇率
    private BigDecimal exchangeRate;
    //核算外币
    private Integer accounting;
    //平均成本单价
    private BigDecimal avgCostPrice;
    //限价成本单价
    private BigDecimal fixCostPrice;
    //目标成本单价
    private BigDecimal aimCostPrice;
    //进项税平均单价
    private BigDecimal entryAvgTaxPrice;

    //进项税目标单价
    private BigDecimal entryAimTaxPrice;
    //销项税单价
    private BigDecimal outputTaxPrice;
    //出库单明细-已打印数
    private Integer printedNum = 0;
    //出库单明细-已生产数
    private Integer producedNum = 0;

    /*扫码数量 */
    private BigDecimal collarQuantity;

    /*是否需要序列号 */
    private boolean needSn;

    public String getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(String billDetailId) {
        this.billDetailId = billDetailId;
    }

    public Integer getBillDetailNo() {
        return billDetailNo;
    }

    public void setBillDetailNo(Integer billDetailNo) {
        this.billDetailNo = billDetailNo;
    }

    public String getDetailBizCode() {
        return detailBizCode;
    }

    public void setDetailBizCode(String detailBizCode) {
        this.detailBizCode = detailBizCode;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getBillTypeCode() {
        return billTypeCode;
    }

    public void setBillTypeCode(String billTypeCode) {
        this.billTypeCode = billTypeCode;
    }

    public String getSubBillTypeCode() {
        return subBillTypeCode;
    }

    public void setSubBillTypeCode(String subBillTypeCode) {
        this.subBillTypeCode = subBillTypeCode;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getRefBillTypeCode() {
        return refBillTypeCode;
    }

    public void setRefBillTypeCode(String refBillTypeCode) {
        this.refBillTypeCode = refBillTypeCode;
    }

    public String getRefBillId() {
        return refBillId;
    }

    public void setRefBillId(String refBillId) {
        this.refBillId = refBillId;
    }

    public String getRefBillNo() {
        return refBillNo;
    }

    public void setRefBillNo(String refBillNo) {
        this.refBillNo = refBillNo;
    }

    public String getRefBillDetailId() {
        return refBillDetailId;
    }

    public void setRefBillDetailId(String refBillDetailId) {
        this.refBillDetailId = refBillDetailId;
    }

    public String getRefBillBalanceId() {
        return refBillBalanceId;
    }

    public void setRefBillBalanceId(String refBillBalanceId) {
        this.refBillBalanceId = refBillBalanceId;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getOwnerCompanyId() {
        return ownerCompanyId;
    }

    public void setOwnerCompanyId(String ownerCompanyId) {
        this.ownerCompanyId = ownerCompanyId;
    }

    public String getOwnerOrgId() {
        return ownerOrgId;
    }

    public void setOwnerOrgId(String ownerOrgId) {
        this.ownerOrgId = ownerOrgId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getSaId() {
        return saId;
    }

    public void setSaId(String saId) {
        this.saId = saId;
    }

    public String getContractOrderId() {
        return contractOrderId;
    }

    public void setContractOrderId(String contractOrderId) {
        this.contractOrderId = contractOrderId;
    }

    public String getContractOrderNo() {
        return contractOrderNo;
    }

    public void setContractOrderNo(String contractOrderNo) {
        this.contractOrderNo = contractOrderNo;
    }

    public String getCoSoId() {
        return coSoId;
    }

    public void setCoSoId(String coSoId) {
        this.coSoId = coSoId;
    }

    public String getPcoId() {
        return pcoId;
    }

    public void setPcoId(String pcoId) {
        this.pcoId = pcoId;
    }

    public String getPcoNo() {
        return pcoNo;
    }

    public void setPcoNo(String pcoNo) {
        this.pcoNo = pcoNo;
    }

    public String getSoId() {
        return soId;
    }

    public void setSoId(String soId) {
        this.soId = soId;
    }

    public String getSoNo() {
        return soNo;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public String getPoId() {
        return poId;
    }

    public void setPoId(String poId) {
        this.poId = poId;
    }

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public String getScoLineId() {
        return scoLineId;
    }

    public void setScoLineId(String scoLineId) {
        this.scoLineId = scoLineId;
    }

    public String getPcoLineId() {
        return pcoLineId;
    }

    public void setPcoLineId(String pcoLineId) {
        this.pcoLineId = pcoLineId;
    }

    public String getSoLineId() {
        return soLineId;
    }

    public void setSoLineId(String soLineId) {
        this.soLineId = soLineId;
    }

    public String getPoLineId() {
        return poLineId;
    }

    public void setPoLineId(String poLineId) {
        this.poLineId = poLineId;
    }

    public String getWbsId() {
        return wbsId;
    }

    public void setWbsId(String wbsId) {
        this.wbsId = wbsId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getGoodsSubClassName() {
        return goodsSubClassName;
    }

    public void setGoodsSubClassName(String goodsSubClassName) {
        this.goodsSubClassName = goodsSubClassName;
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }

    public String getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(String goodsCategory) {
        this.goodsCategory = goodsCategory;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public BigDecimal getHandledQuantity() {
        return handledQuantity;
    }

    public void setHandledQuantity(BigDecimal handledQuantity) {
        this.handledQuantity = handledQuantity;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getForeignCurrencyPrice() {
        return foreignCurrencyPrice;
    }

    public void setForeignCurrencyPrice(BigDecimal foreignCurrencyPrice) {
        this.foreignCurrencyPrice = foreignCurrencyPrice;
    }

    public BigDecimal getForeignCurrencyAmount() {
        return foreignCurrencyAmount;
    }

    public void setForeignCurrencyAmount(BigDecimal foreignCurrencyAmount) {
        this.foreignCurrencyAmount = foreignCurrencyAmount;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Integer getAccounting() {
        return accounting;
    }

    public void setAccounting(Integer accounting) {
        this.accounting = accounting;
    }

    public BigDecimal getAvgCostPrice() {
        return avgCostPrice;
    }

    public void setAvgCostPrice(BigDecimal avgCostPrice) {
        this.avgCostPrice = avgCostPrice;
    }

    public BigDecimal getFixCostPrice() {
        return fixCostPrice;
    }

    public void setFixCostPrice(BigDecimal fixCostPrice) {
        this.fixCostPrice = fixCostPrice;
    }

    public BigDecimal getAimCostPrice() {
        return aimCostPrice;
    }

    public void setAimCostPrice(BigDecimal aimCostPrice) {
        this.aimCostPrice = aimCostPrice;
    }

    public BigDecimal getEntryAvgTaxPrice() {
        return entryAvgTaxPrice;
    }

    public void setEntryAvgTaxPrice(BigDecimal entryAvgTaxPrice) {
        this.entryAvgTaxPrice = entryAvgTaxPrice;
    }

    public BigDecimal getEntryAimTaxPrice() {
        return entryAimTaxPrice;
    }

    public void setEntryAimTaxPrice(BigDecimal entryAimTaxPrice) {
        this.entryAimTaxPrice = entryAimTaxPrice;
    }

    public BigDecimal getOutputTaxPrice() {
        return outputTaxPrice;
    }

    public void setOutputTaxPrice(BigDecimal outputTaxPrice) {
        this.outputTaxPrice = outputTaxPrice;
    }

    public Integer getPrintedNum() {
        return printedNum;
    }

    public void setPrintedNum(Integer printedNum) {
        this.printedNum = printedNum;
    }

    public Integer getProducedNum() {
        return producedNum;
    }

    public void setProducedNum(Integer producedNum) {
        this.producedNum = producedNum;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public BigDecimal getCollarQuantity() {
        return collarQuantity;
    }

    public void setCollarQuantity(BigDecimal collarQuantity) {
        this.collarQuantity = collarQuantity;
    }

    public boolean isNeedSn() {
        return needSn;
    }

    public void setNeedSn(boolean needSn) {
        this.needSn = needSn;
    }
}
