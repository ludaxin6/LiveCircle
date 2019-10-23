package com.deshine.huishu.app.customerInvite.model.bean;


import com.deshine.huishu.app.commonAffix.bean.CommonAffix;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author shaozhen
 */
public class FinanceBillDto extends FinanceBill implements Serializable {
    private static final long serialVersionUID = 1L;
    //客商
    private String customerName;
    //联系人
    private String contactName;
    //业务公司
    private String companyName;
    //经手公司
    private String ownerCompanyName;
    //经手部门
    private String ownerOrgName;
    //经手人
    private String ownerName;
    private String ownerPhone;
    //操作人
    private String operatorName;
    //创建人
    private String creatorName;
    //交付日期
    private Timestamp deliveryDate;
    //寄票信息ID
    private String sendTicketInfoId;
    //交付地址ID
    private String deliveryAddressId;
    //库房名称
    private String locationName;
    //发票类型描述
    private String invoiceTypeDesc;

    //交付备注
    private String deliverRemark;
    //前一个业务流程的billNO
    private String refBillNo;
    //币种描述
    private String currencyDesc;
    //科目名称
    private String accountName;

    /**
     * 子类型名称
     */
    private String subBillTypeName;
    //============================================
    private String consigneeCompanyName;//收货公司
    private String consigneeName;//收货联系人
    private String consigneePhone;//收货联系电话
    private String consigneeAddress;//收货地址
    private String consigneeMobilePhone;//收货联系电话
    //带合同(0:否 1:是)
    private String bringContractOrder;
    //带发票(0:否 1:是)
    private String bringInvoice;
    //收发票(0:否 1:是)
    private String collectCheque;

    //查询用
    //起始创建时间
    private Date startCreateTime;
    //结束创建时间
    private Date endCreateTime;

    //起始记账时间
    private Date startBookTime;
    //结束记账时间
    private Date endBookTime;
    private Date startTradeTime;
    private Date endTradeTime;

    //起始审批时间
    private Date startApproveTime;
    //结束审批时间
    private Date endApproveTime;

    private List<String> companyIdList;//业务公司
    private List<String> ownerCompanyIdList;//经手公司
    private List<String> locationIdList;//库房名称
    private List<String> statusList;//状态
    private List<String> currencyList;//币种描述
    private List<String> invoiceTypeList;//发票类型

    //外币金额
    private BigDecimal minForeignCurrencyAmount;
    private BigDecimal maxForeignCurrencyAmount;
    //金额
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    //汇率
    private BigDecimal minExchangeRate;

    private BigDecimal maxExchangeRate;

    private List<CommonAffix> affixList;

    private List<CommonAffix> idCardAffixList;
    //票号
    private String chequeNo;
    //票面日期
    private Date chequeDate;


    /**
     * 收支类型-收款单、付款单使用
     */
    private String methodCode;


    /**
     * 往来账子类型
     */
    private String caSubType;

    /**
     * 往来账子类型
     */
    private String caSubTypeName;
    /**
     * 采购订单
     * 1:我方
     * 2：对方
     */
    private int coTemplet;

    /**
     * 状态显示
     */
    private String statusLabel;

    /**
     * 待处理人
     */
    private String todoExecutors;

    /**
     * 销售运费-扩展信息
     */
    private TransFeeExt transFeeExt;

    /**
     * 单据子类型-参数列表
     */
    private List<String> subBillTypeCodeList;

    //产品型号描述
    private String goodsDesc;
    //费用单 - 单据来源类型 0 普通 1财务
    private Integer fsBillSourceType;

    //收款账户
    private String receiptAccountName;

    //付款账户
    private String paymentAccountName;

    //付款账户集合
    private List<String> paymentAccountList;
    //收款账户集合
    private List<String> receiptAccountList;
    //收款账户
    private BigDecimal receiptAccountAmount;

    //付款账户
    private BigDecimal paymentAccountAmount;
    /**
     * 付款回执单
     */
    private String pyReceiptOrder;
    private String pyReceiptBank;// 付款单收款银行
    private String pyReceiptBankAccountName;// 付款单收款账户名称
    private String pyReceiptbankAccount;// 付款单收款账号

    //排款日期
    private Date dischargeDate;

    //1寄付 2到付
    private Integer transFeeFlag;

    /*佣金提取形式*/
    private int crPayTypeCode;
    /*费用单-寄票地址*/
    private String fsTicketAddress;
    /*费用单-快递单号*/
    private String fsMailNo;
    /*费用单-是否需要快递*/
    private Integer fsNeedMail;

    //费用单-待核销金额
    private BigDecimal bwLeftAmount;

    /*法人实体-税号*/
    private String companyTaxNo;

    /*调价单-扩展字段*/
    /*调价方向 0 调低 1调高*/
    private Integer paDirection;

    /*凭证号码*/
    private String bankCreditNo;
    /*银行流水号*/
    private String bankTranFlow;
    /*活存账户明细号*/
    private String detNo;
    /*审核人*/
    private String checkName;
    /*附言*/
    private String addComment;
    private String postscript;
    /*付款业务群组id*/
    private String payGroupId;
    /*付款业务群组名称*/
    private String payGroupName;
    /*大写金额*/
    private String amountUpper;
    private String mailDate;
    /*商品编码*/
    private String partsNo;
    /*出库单是否可补打签收单*/
    private int printOsSignOrder = 0;


    //App 按钮
    private CommandExecDto commandExecDto;
    private Integer signOrderTotal;//签收单张数
    private Integer signOrderIndex;//签收单编号

    //关联的生产单、移库单
    private List<Map<String,String>> refBillList;


    public CommandExecDto getCommandExecDto() {
        return commandExecDto;
    }

    public void setCommandExecDto(CommandExecDto commandExecDto) {
        this.commandExecDto = commandExecDto;
    }

    public Integer getSignOrderTotal() {
        return signOrderTotal;
    }

    public void setSignOrderTotal(Integer signOrderTotal) {
        this.signOrderTotal = signOrderTotal;
    }

    public Integer getSignOrderIndex() {
        return signOrderIndex;
    }

    public void setSignOrderIndex(Integer signOrderIndex) {
        this.signOrderIndex = signOrderIndex;
    }

    public List<Map<String, String>> getRefBillList() {
        return refBillList;
    }

    public void setRefBillList(List<Map<String, String>> refBillList) {
        this.refBillList = refBillList;
    }

    public int getPrintOsSignOrder() {
        return printOsSignOrder;
    }

    public void setPrintOsSignOrder(int printOsSignOrder) {
        this.printOsSignOrder = printOsSignOrder;
    }

    public String getMailDate() {
        return mailDate;
    }

    public void setMailDate(String mailDate) {
        this.mailDate = mailDate;
    }

    public String getPostscript() {
        return postscript;
    }

    public void setPostscript(String postscript) {
        this.postscript = postscript;
    }

    public String getAmountUpper() {
        return amountUpper;
    }

    public void setAmountUpper(String amountUpper) {
        this.amountUpper = amountUpper;
    }

    public String getCurrencyDesc() {
        return currencyDesc;
    }

    public void setCurrencyDesc(String currencyDesc) {
        this.currencyDesc = currencyDesc;
    }

    public String getRefBillNo() {
        return refBillNo;
    }

    public void setRefBillNo(String refBillNo) {
        this.refBillNo = refBillNo;
    }

    public String getDeliverRemark() {
        return deliverRemark;
    }

    public void setDeliverRemark(String deliverRemark) {
        this.deliverRemark = deliverRemark;
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

    public Date getStartBookTime() {
        return startBookTime;
    }

    public void setStartBookTime(Date startBookTime) {
        this.startBookTime = startBookTime;
    }

    public Date getEndBookTime() {
        return endBookTime;
    }

    public void setEndBookTime(Date endBookTime) {
        this.endBookTime = endBookTime;
    }

    public Date getStartTradeTime() {
        return startTradeTime;
    }

    public void setStartTradeTime(Date startTradeTime) {
        this.startTradeTime = startTradeTime;
    }

    public Date getEndTradeTime() {
        return endTradeTime;
    }

    public void setEndTradeTime(Date endTradeTime) {
        this.endTradeTime = endTradeTime;
    }

    public Date getStartApproveTime() {
        return startApproveTime;
    }

    public void setStartApproveTime(Date startApproveTime) {
        this.startApproveTime = startApproveTime;
    }

    public Date getEndApproveTime() {
        return endApproveTime;
    }

    public void setEndApproveTime(Date endApproveTime) {
        this.endApproveTime = endApproveTime;
    }

    public String getInvoiceTypeDesc() {
        return invoiceTypeDesc;
    }

    public void setInvoiceTypeDesc(String invoiceTypeDesc) {
        this.invoiceTypeDesc = invoiceTypeDesc;
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

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Timestamp getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Timestamp deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getSendTicketInfoId() {
        return sendTicketInfoId;
    }

    public void setSendTicketInfoId(String sendTicketInfoId) {
        this.sendTicketInfoId = sendTicketInfoId;
    }

    public String getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(String deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

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


    public String getConsigneeMobilePhone() {
        return consigneeMobilePhone;
    }

    public void setConsigneeMobilePhone(String consigneeMobilePhone) {
        this.consigneeMobilePhone = consigneeMobilePhone;
    }

    public String getBringContractOrder() {
        return bringContractOrder;
    }

    public void setBringContractOrder(String bringContractOrder) {
        this.bringContractOrder = bringContractOrder;
    }

    public String getBringInvoice() {
        return bringInvoice;
    }

    public void setBringInvoice(String bringInvoice) {
        this.bringInvoice = bringInvoice;
    }

    public String getCollectCheque() {
        return collectCheque;
    }

    public void setCollectCheque(String collectCheque) {
        this.collectCheque = collectCheque;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getSubBillTypeName() {
        return subBillTypeName;
    }

    public void setSubBillTypeName(String subBillTypeName) {
        this.subBillTypeName = subBillTypeName;
    }

    public List<CommonAffix> getAffixList() {
        return affixList;
    }

    public void setAffixList(List<CommonAffix> affixList) {
        this.affixList = affixList;
    }

    public List<CommonAffix> getIdCardAffixList() {
        return idCardAffixList;
    }

    public void setIdCardAffixList(List<CommonAffix> idCardAffixList) {
        this.idCardAffixList = idCardAffixList;
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

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public List<String> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<String> currencyList) {
        this.currencyList = currencyList;
    }

    public List<String> getInvoiceTypeList() {
        return invoiceTypeList;
    }

    public void setInvoiceTypeList(List<String> invoiceTypeList) {
        this.invoiceTypeList = invoiceTypeList;
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

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public Date getChequeDate() {
        return chequeDate;
    }

    public void setChequeDate(Date chequeDate) {
        this.chequeDate = chequeDate;
    }

    public String getMethodCode() {
        return methodCode;
    }

    public void setMethodCode(String methodCode) {
        this.methodCode = methodCode;
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

    public String getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(String statusLabel) {
        this.statusLabel = statusLabel;
    }

    public String getTodoExecutors() {
        return todoExecutors;
    }

    public void setTodoExecutors(String todoExecutors) {
        this.todoExecutors = todoExecutors;
    }

    public TransFeeExt getTransFeeExt() {
        return transFeeExt;
    }

    public void setTransFeeExt(TransFeeExt transFeeExt) {
        this.transFeeExt = transFeeExt;
    }

    public List<String> getSubBillTypeCodeList() {
        return subBillTypeCodeList;
    }

    public void setSubBillTypeCodeList(List<String> subBillTypeCodeList) {
        this.subBillTypeCodeList = subBillTypeCodeList;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public Integer getFsBillSourceType() {
        return fsBillSourceType;
    }

    public void setFsBillSourceType(Integer fsBillSourceType) {
        this.fsBillSourceType = fsBillSourceType;
    }

    public String getReceiptAccountName() {
        return receiptAccountName;
    }

    public void setReceiptAccountName(String receiptAccountName) {
        this.receiptAccountName = receiptAccountName;
    }

    public String getPaymentAccountName() {
        return paymentAccountName;
    }

    public void setPaymentAccountName(String paymentAccountName) {
        this.paymentAccountName = paymentAccountName;
    }


    public List<String> getPaymentAccountList() {
        return paymentAccountList;
    }

    public void setPaymentAccountList(List<String> paymentAccountList) {
        this.paymentAccountList = paymentAccountList;
    }

    public List<String> getReceiptAccountList() {
        return receiptAccountList;
    }

    public void setReceiptAccountList(List<String> receiptAccountList) {
        this.receiptAccountList = receiptAccountList;
    }

    public BigDecimal getReceiptAccountAmount() {
        return receiptAccountAmount;
    }

    public void setReceiptAccountAmount(BigDecimal receiptAccountAmount) {
        this.receiptAccountAmount = receiptAccountAmount;
    }

    public BigDecimal getPaymentAccountAmount() {
        return paymentAccountAmount;
    }

    public void setPaymentAccountAmount(BigDecimal paymentAccountAmount) {
        this.paymentAccountAmount = paymentAccountAmount;
    }

    public String getPyReceiptOrder() {
        return pyReceiptOrder;
    }

    public void setPyReceiptOrder(String pyReceiptOrder) {
        this.pyReceiptOrder = pyReceiptOrder;
    }

    public String getPyReceiptBank() {
        return pyReceiptBank;
    }

    public void setPyReceiptBank(String pyReceiptBank) {
        this.pyReceiptBank = pyReceiptBank;
    }

    public String getPyReceiptBankAccountName() {
        return pyReceiptBankAccountName;
    }

    public void setPyReceiptBankAccountName(String pyReceiptBankAccountName) {
        this.pyReceiptBankAccountName = pyReceiptBankAccountName;
    }

    public String getPyReceiptbankAccount() {
        return pyReceiptbankAccount;
    }

    public void setPyReceiptbankAccount(String pyReceiptbankAccount) {
        this.pyReceiptbankAccount = pyReceiptbankAccount;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public int getCrPayTypeCode() {
        return crPayTypeCode;
    }

    public void setCrPayTypeCode(int crPayTypeCode) {
        this.crPayTypeCode = crPayTypeCode;
    }

    public String getFsTicketAddress() {
        return fsTicketAddress;
    }

    public void setFsTicketAddress(String fsTicketAddress) {
        this.fsTicketAddress = fsTicketAddress;
    }

    public String getFsMailNo() {
        return fsMailNo;
    }

    public void setFsMailNo(String fsMailNo) {
        this.fsMailNo = fsMailNo;
    }

    public Integer getFsNeedMail() {
        return fsNeedMail;
    }

    public void setFsNeedMail(Integer fsNeedMail) {
        this.fsNeedMail = fsNeedMail;
    }

    public BigDecimal getBwLeftAmount() {
        return bwLeftAmount;
    }

    public void setBwLeftAmount(BigDecimal bwLeftAmount) {
        this.bwLeftAmount = bwLeftAmount;
    }

    public String getCompanyTaxNo() {
        return companyTaxNo;
    }

    public void setCompanyTaxNo(String companyTaxNo) {
        this.companyTaxNo = companyTaxNo;
    }


    public Integer getPaDirection() {
        return paDirection;
    }

    public void setPaDirection(Integer paDirection) {
        this.paDirection = paDirection;
    }

    public String getBankTranFlow() {
        return bankTranFlow;
    }

    public void setBankTranFlow(String bankTranFlow) {
        this.bankTranFlow = bankTranFlow;
    }

    public String getBankCreditNo() {
        return bankCreditNo;
    }

    public void setBankCreditNo(String bankCreditNo) {
        this.bankCreditNo = bankCreditNo;
    }

    public String getDetNo() {
        return detNo;
    }

    public void setDetNo(String detNo) {
        this.detNo = detNo;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getAddComment() {
        return addComment;
    }

    public void setAddComment(String addComment) {
        this.addComment = addComment;
    }

    public String getPayGroupId() {
        return payGroupId;
    }

    public void setPayGroupId(String payGroupId) {
        this.payGroupId = payGroupId;
    }

    public String getPayGroupName() {
        return payGroupName;
    }

    public void setPayGroupName(String payGroupName) {
        this.payGroupName = payGroupName;
    }

    public String getPartsNo() {
        return partsNo;
    }

    public void setPartsNo(String partsNo) {
        this.partsNo = partsNo;
    }
}
