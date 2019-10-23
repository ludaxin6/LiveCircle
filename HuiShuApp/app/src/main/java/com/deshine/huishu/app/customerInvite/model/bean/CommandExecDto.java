package com.deshine.huishu.app.customerInvite.model.bean;

import com.deshine.huishu.app.commonAffix.bean.CommonAffix;

import java.util.List;

/**
 * 描述:按钮执行dto
 *
 * @author shaozhen
 */
public class CommandExecDto extends TaskDefinitionCommand {

    public CommandExecDto() {
    }
    public CommandExecDto( String processId, String bizType, String bizId, String executor) {
        this.processId = processId;
        this.bizType = bizType;
        this.bizId = bizId;
        this.executor = executor;
    }

    public CommandExecDto(String remark, String processId, String bizType, String bizId, String executor, List<CommonAffix> affixList, int isRecover) {
        this.remark = remark;
        this.processId = processId;
        this.bizType = bizType;
        this.bizId = bizId;
        this.executor = executor;
        this.affixList = affixList;
        this.isRecover = isRecover;
    }

    //备注
    private String remark;
    //
    private String processId;
    //业务类型
    private String bizType;
    //业务id
    private String bizId;
    //执行者id
    private String executor;

    //附件
    private List<CommonAffix> affixList;

    //是否追回按钮
    private int isRecover = 0;


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

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public List<CommonAffix> getAffixList() {
        return affixList;
    }

    public void setAffixList(List<CommonAffix> affixList) {
        this.affixList = affixList;
    }

    public int getIsRecover() {
        return isRecover;
    }

    public void setIsRecover(int isRecover) {
        this.isRecover = isRecover;
    }
}
