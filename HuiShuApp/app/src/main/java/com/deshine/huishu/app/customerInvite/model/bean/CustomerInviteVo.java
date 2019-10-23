package com.deshine.huishu.app.customerInvite.model.bean;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class CustomerInviteVo {

    private FinanceBillDto psBill;
    private List<SoDetailVo> soDetailVoList = new ArrayList<>();
    //承运商列表
    private List<Logistics> logisticsList;
    //App 按钮
    private CommandExecDto commandExecDto;
    private Integer signOrderTotal;//签收单张数
    private Integer signOrderIndex;//签收单编号

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

    public CommandExecDto getCommandExecDto() {
        return commandExecDto;
    }

    public void setCommandExecDto(CommandExecDto commandExecDto) {
        this.commandExecDto = commandExecDto;
    }

    public FinanceBillDto getPsBill() {
        return psBill;
    }

    public void setPsBill(FinanceBillDto psBill) {
        this.psBill = psBill;
    }

    public List<SoDetailVo> getSoDetailVoList() {
        return soDetailVoList;
    }

    public void setSoDetailVoList(List<SoDetailVo> soDetailVoList) {
        this.soDetailVoList = soDetailVoList;
    }

    public List<Logistics> getLogisticsList() {
        return logisticsList;
    }

    public void setLogisticsList(List<Logistics> logisticsList) {
        this.logisticsList = logisticsList;
    }

}
