package com.deshine.huishu.app.customerInvite.model.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author shaozhen
 */
public class FinanceBillDetaillDto extends FinanceBillDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    //客商
    private String customerName;
    //联系人
    private String contactName;
    //业务公司
    private String companyName;
    //经手公司
    private String ownerCompanyName;
    //经手公司简称
    private String ownerCompanyShortName;
    //经手公司别名
    private String ownerCompanyAlias;
    //经手部门
    private String ownerOrgName;
    //经手人
    private String ownerName;
    //经手人-全名
    private String ownerFullName;
    //操作人
    private String operatorName;
    //库房名称
    private String locationName;
    //币种描述
    private String currencyDesc;
    //科目名称
    private String accountName;
    //============================================
    //查询用
    //起始创建时间
    private Date startCreateTime;
    //结束创建时间
    private Date endCreateTime;

    private List<String> companyIdList;//业务公司
    private List<String> ownerCompanyIdList;//经手公司
    private List<String> locationIdList;//库房名称
    private List<String> currencyList;//币种

    //外币金额
    private BigDecimal minForeignCurrencyAmount;
    private BigDecimal maxForeignCurrencyAmount;
    //外币单价
    private BigDecimal minForeignCurrencyPrice;
    private BigDecimal maxForeignCurrencyPrice;
    //金额
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    //汇率
    private BigDecimal minExchangeRate;
    private BigDecimal maxExchangeRate;
    //数量
    private BigDecimal minQuantity;
    private BigDecimal maxQuantity;
    //单价
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    //外币原始金额
    private BigDecimal originalForeignAmount;       //ORIGINAL_AMOUNT
    /**
     * 明细账-原始金额 联查
     */
    private BigDecimal originalAmount;
    /**
     * 明细账-核后余额 联查
     */
    //核销余额
    private BigDecimal amountAfterBW;


    //核销余额-外币
    private BigDecimal foreignAmountAfterBW;
    /**
     * 明细账-应收金额  扩展字段
     */
    private BigDecimal arAmount;

    /**
     * 明细账-应收金额-外币  扩展字段
     */
    private BigDecimal arForeignAmount;

    private Date expecteDate;
    //关联单据记账日期
    private Date refBillBookDate;
    //往来账类型
    private String caType;
    //往来账类型-子类型
    private String caSubType;
    //往来账子类型显示码
    private String caSubTypeName;

    //收款单-预收款-文本信息
    private String acExtInfo;
    private String supplyCustId;
    private String supplyCustName;
    private String supplyContactId;
    private String supplyContactName;
    //费用单据扩展
    private BillDetailFeeExt feeExt;
    //费用单-已核销金额
    private BigDecimal bwLeftAmount;
    /*库存数量*/
    private BigDecimal stockQuantity;
    /*调前成本*/
    private BigDecimal stockCostPre;
    /*调后成本*/
    private BigDecimal stockCostNew;
    /*成本变化*/
    private BigDecimal stockCostDiff;
    /*剩余金额*/
    private BigDecimal billAmount;
    /*本次调价金额*/
    private BigDecimal currentBwAmount;
    /*入库单id*/
    private String piBillId;
    /*入库单no*/
    private String piBillNo;
    /*采购订单id*/
    private String poBillId;

    /*采购订单No*/
    private String poBillNo;

    /*受款方名称*/
    private String payeeCustName;

    private String simpleConfigInfo;

    //采购应付金额
    private BigDecimal poApAmount;

    //关联采购费用类型
    private String refPoMoneyType;

    public BigDecimal getPoApAmount() {
        return poApAmount;
    }

    public void setPoApAmount(BigDecimal poApAmount) {
        this.poApAmount = poApAmount;
    }

    public BigDecimal getBwLeftAmount() {
        return bwLeftAmount;
    }

    public void setBwLeftAmount(BigDecimal bwLeftAmount) {
        this.bwLeftAmount = bwLeftAmount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOwnerCompanyName() {
        return ownerCompanyName;
    }

    public void setOwnerCompanyName(String ownerCompanyName) {
        this.ownerCompanyName = ownerCompanyName;
    }

    public String getOwnerCompanyShortName() {
        return ownerCompanyShortName;
    }

    public void setOwnerCompanyShortName(String ownerCompanyShortName) {
        this.ownerCompanyShortName = ownerCompanyShortName;
    }

    public String getOwnerCompanyAlias() {
        return ownerCompanyAlias;
    }

    public void setOwnerCompanyAlias(String ownerCompanyAlias) {
        this.ownerCompanyAlias = ownerCompanyAlias;
    }

    public String getOwnerOrgName() {
        return ownerOrgName;
    }

    public void setOwnerOrgName(String ownerOrgName) {
        this.ownerOrgName = ownerOrgName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerFullName() {
        return ownerFullName;
    }

    public void setOwnerFullName(String ownerFullName) {
        this.ownerFullName = ownerFullName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getCurrencyDesc() {
        return currencyDesc;
    }

    public void setCurrencyDesc(String currencyDesc) {
        this.currencyDesc = currencyDesc;
    }

    public Date getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(Date startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public List<String> getCompanyIdList() {
        return companyIdList;
    }

    public void setCompanyIdList(List<String> companyIdList) {
        this.companyIdList = companyIdList;
    }

    public List<String> getOwnerCompanyIdList() {
        return ownerCompanyIdList;
    }

    public void setOwnerCompanyIdList(List<String> ownerCompanyIdList) {
        this.ownerCompanyIdList = ownerCompanyIdList;
    }

    public List<String> getLocationIdList() {
        return locationIdList;
    }

    public void setLocationIdList(List<String> locationIdList) {
        this.locationIdList = locationIdList;
    }

    public List<String> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<String> currencyList) {
        this.currencyList = currencyList;
    }

    public BigDecimal getMinForeignCurrencyAmount() {
        return minForeignCurrencyAmount;
    }

    public void setMinForeignCurrencyAmount(BigDecimal minForeignCurrencyAmount) {
        this.minForeignCurrencyAmount = minForeignCurrencyAmount;
    }

    public BigDecimal getMaxForeignCurrencyAmount() {
        return maxForeignCurrencyAmount;
    }

    public void setMaxForeignCurrencyAmount(BigDecimal maxForeignCurrencyAmount) {
        this.maxForeignCurrencyAmount = maxForeignCurrencyAmount;
    }

    public BigDecimal getMinForeignCurrencyPrice() {
        return minForeignCurrencyPrice;
    }

    public void setMinForeignCurrencyPrice(BigDecimal minForeignCurrencyPrice) {
        this.minForeignCurrencyPrice = minForeignCurrencyPrice;
    }

    public BigDecimal getMaxForeignCurrencyPrice() {
        return maxForeignCurrencyPrice;
    }

    public void setMaxForeignCurrencyPrice(BigDecimal maxForeignCurrencyPrice) {
        this.maxForeignCurrencyPrice = maxForeignCurrencyPrice;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public BigDecimal getMinExchangeRate() {
        return minExchangeRate;
    }

    public void setMinExchangeRate(BigDecimal minExchangeRate) {
        this.minExchangeRate = minExchangeRate;
    }

    public BigDecimal getMaxExchangeRate() {
        return maxExchangeRate;
    }

    public void setMaxExchangeRate(BigDecimal maxExchangeRate) {
        this.maxExchangeRate = maxExchangeRate;
    }

    public BigDecimal getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(BigDecimal minQuantity) {
        this.minQuantity = minQuantity;
    }

    public BigDecimal getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(BigDecimal maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public BigDecimal getAmountAfterBW() {
        return amountAfterBW;
    }

    public void setAmountAfterBW(BigDecimal amountAfterBW) {
        this.amountAfterBW = amountAfterBW;
    }

    public BigDecimal getForeignAmountAfterBW() {
        return foreignAmountAfterBW;
    }

    public void setForeignAmountAfterBW(BigDecimal foreignAmountAfterBW) {
        this.foreignAmountAfterBW = foreignAmountAfterBW;
    }

    public BigDecimal getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(BigDecimal originalAmount) {
        this.originalAmount = originalAmount;
    }

    public BigDecimal getArAmount() {
        return arAmount;
    }

    public void setArAmount(BigDecimal arAmount) {
        this.arAmount = arAmount;
    }

    public BigDecimal getArForeignAmount() {
        return arForeignAmount;
    }

    public void setArForeignAmount(BigDecimal arForeignAmount) {
        this.arForeignAmount = arForeignAmount;
    }

    public Date getExpecteDate() {
        return expecteDate;
    }

    public void setExpecteDate(Date expecteDate) {
        this.expecteDate = expecteDate;
    }

    public Date getRefBillBookDate() {
        return refBillBookDate;
    }

    public void setRefBillBookDate(Date refBillBookDate) {
        this.refBillBookDate = refBillBookDate;
    }

    public String getCaType() {
        return caType;
    }

    public void setCaType(String caType) {
        this.caType = caType;
    }

    public String getCaSubType() {
        return caSubType;
    }

    public void setCaSubType(String caSubType) {
        this.caSubType = caSubType;
    }

    public String getCaSubTypeName() {
        return caSubTypeName;
    }

    public void setCaSubTypeName(String caSubTypeName) {
        this.caSubTypeName = caSubTypeName;
    }

    public String getAcExtInfo() {
        return acExtInfo;
    }

    public void setAcExtInfo(String acExtInfo) {
        this.acExtInfo = acExtInfo;
    }

    public BigDecimal getOriginalForeignAmount() {
        return originalForeignAmount;
    }

    public void setOriginalForeignAmount(BigDecimal originalForeignAmount) {
        this.originalForeignAmount = originalForeignAmount;
    }

    public BillDetailFeeExt getFeeExt() {
        return feeExt;
    }

    public void setFeeExt(BillDetailFeeExt feeExt) {
        this.feeExt = feeExt;
    }

    public String getSupplyCustId() {
        return supplyCustId;
    }

    public void setSupplyCustId(String supplyCustId) {
        this.supplyCustId = supplyCustId;
    }

    public String getSupplyCustName() {
        return supplyCustName;
    }

    public void setSupplyCustName(String supplyCustName) {
        this.supplyCustName = supplyCustName;
    }

    public String getPayeeCustName() {
        return payeeCustName;
    }

    public void setPayeeCustName(String payeeCustName) {
        this.payeeCustName = payeeCustName;
    }

    public String getSupplyContactId() {
        return supplyContactId;
    }

    public void setSupplyContactId(String supplyContactId) {
        this.supplyContactId = supplyContactId;
    }

    public String getSupplyContactName() {
        return supplyContactName;
    }

    public void setSupplyContactName(String supplyContactName) {
        this.supplyContactName = supplyContactName;
    }

    public BigDecimal getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(BigDecimal stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public BigDecimal getStockCostPre() {
        return stockCostPre;
    }

    public void setStockCostPre(BigDecimal stockCostPre) {
        this.stockCostPre = stockCostPre;
    }

    public BigDecimal getStockCostNew() {
        return stockCostNew;
    }

    public void setStockCostNew(BigDecimal stockCostNew) {
        this.stockCostNew = stockCostNew;
    }

    public BigDecimal getStockCostDiff() {
        return stockCostDiff;
    }

    public void setStockCostDiff(BigDecimal stockCostDiff) {
        this.stockCostDiff = stockCostDiff;
    }


    public BigDecimal getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(BigDecimal billAmount) {
        this.billAmount = billAmount;
    }

    public BigDecimal getCurrentBwAmount() {
        return currentBwAmount;
    }

    public void setCurrentBwAmount(BigDecimal currentBwAmount) {
        this.currentBwAmount = currentBwAmount;
    }

    public String getPiBillId() {
        return piBillId;
    }

    public void setPiBillId(String piBillId) {
        this.piBillId = piBillId;
    }

    public String getPiBillNo() {
        return piBillNo;
    }

    public void setPiBillNo(String piBillNo) {
        this.piBillNo = piBillNo;
    }

    public String getPoBillId() {
        return poBillId;
    }

    public void setPoBillId(String poBillId) {
        this.poBillId = poBillId;
    }

    public String getPoBillNo() {
        return poBillNo;
    }

    public void setPoBillNo(String poBillNo) {
        this.poBillNo = poBillNo;
    }

    public String getSimpleConfigInfo() {
        return simpleConfigInfo;
    }

    public void setSimpleConfigInfo(String simpleConfigInfo) {
        this.simpleConfigInfo = simpleConfigInfo;
    }

    public String getRefPoMoneyType() {
        return refPoMoneyType;
    }

    public void setRefPoMoneyType(String refPoMoneyType) {
        this.refPoMoneyType = refPoMoneyType;
    }
}
