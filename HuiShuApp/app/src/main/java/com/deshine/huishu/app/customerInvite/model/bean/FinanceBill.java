package com.deshine.huishu.app.customerInvite.model.bean;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 业务单据
 *
 * @author shaozhen
 */
public class FinanceBill implements Serializable {
    private static final long serialVersionUID = 1L;
    //单据编号
    private String billId;
    //单据类型代码
    private String billTypeCode;
    //子单据类型代码
    private String subBillTypeCode;
    //明细业务代码
    private String detailBizCode;
    //业务单据编号
    private String billNo;
    //关联单据类型
    private String refBillTypeCode;
    //关联单据ID
    private String refBillId;
    //关联单据明细行ID
    private String refBillDetailId;
    //科目代码
    private String accountCode;
    //客商ID
    private String customerId;
    //联系人ID
    private String contactId;
    //业务公司
    private String companyId;
    //经手公司
    private String ownerCompanyId;
    //经手部门
    private String ownerOrgId;
    //经手人
    private String ownerId;
    //操作人
    private String operatorId;
    //WBSID
    private String wbsId;
    //商机ID
    private String caseId;
    //销售变更单id
    private String saId;
    //合同ID
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

    //库存地点
    private String locationId;
    /**
     * 库存地点2
     * 移库时用于入库库存地点
     */
    private String locationId2;
    //收款账户
    private String receiptAccountCode;
    //付款账户
    private String paymentAccountCode;
    //金额
    private BigDecimal amount;
    //创建人
    private String creator;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //记账时间
    private Date bookDate;
    //交易日期
    private Date tradeDate;
    //银行交易交易日期
    private Date bankTradeDate;
    //审批时间
    private Date approveTime;
    //状态 Booked已记账 Open未记账  BeRedBook已反冲  PartBooked部分记账  Cancel取消  Approve审批通过
    private String status;
    //备注
    private String memo;
    //发票类型
    private String invoiceType;
    //币种
    private String currency;
    //外币金额
    private BigDecimal foreignCurrencyAmount;
    //汇率
    private BigDecimal exchangeRate;

    //核算外币 1需要核算 0不需要
    private Integer accounting;

    /**
     * 出库单-单据标识
     */
    private String billFlag;
    //账期(天)
    private Integer debtTerm;

    private List<FinanceBillDetaillDto> financeBillDetailList = null;

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

    public String getDetailBizCode() {
        return detailBizCode;
    }

    public void setDetailBizCode(String detailBizCode) {
        this.detailBizCode = detailBizCode;
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

    public String getRefBillDetailId() {
        return refBillDetailId;
    }

    public void setRefBillDetailId(String refBillDetailId) {
        this.refBillDetailId = refBillDetailId;
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

    public String getWbsId() {
        return wbsId;
    }

    public void setWbsId(String wbsId) {
        this.wbsId = wbsId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
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

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationId2() {
        return locationId2;
    }

    public void setLocationId2(String locationId2) {
        this.locationId2 = locationId2;
    }

    public String getReceiptAccountCode() {
        return receiptAccountCode;
    }

    public void setReceiptAccountCode(String receiptAccountCode) {
        this.receiptAccountCode = receiptAccountCode;
    }

    public String getPaymentAccountCode() {
        return paymentAccountCode;
    }

    public void setPaymentAccountCode(String paymentAccountCode) {
        this.paymentAccountCode = paymentAccountCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public Date getBankTradeDate() {
        return bankTradeDate;
    }

    public void setBankTradeDate(Date bankTradeDate) {
        this.bankTradeDate = bankTradeDate;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public List<FinanceBillDetaillDto> getFinanceBillDetailList() {
        return financeBillDetailList;
    }

    public void setFinanceBillDetailList(List<FinanceBillDetaillDto> financeBillDetailList) {
        this.financeBillDetailList = financeBillDetailList;
    }

    public String getBillFlag() {
        return billFlag;
    }

    public void setBillFlag(String billFlag) {
        this.billFlag = billFlag;
    }

    public Integer getDebtTerm() {
        return debtTerm;
    }

    public void setDebtTerm(Integer debtTerm) {
        this.debtTerm = debtTerm;
    }
}
