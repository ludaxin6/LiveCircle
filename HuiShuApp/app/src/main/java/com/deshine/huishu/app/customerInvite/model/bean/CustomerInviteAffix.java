package com.deshine.huishu.app.customerInvite.model.bean;

import com.deshine.huishu.app.commonAffix.bean.CommonAffix;

import java.util.List;

public class CustomerInviteAffix{
    public CustomerInviteAffix(){}
    public CustomerInviteAffix(List<CommonAffix> idCardAffixList,List<CommonAffix> signOrderAffixList){
        this.idCardAffixList= idCardAffixList;
        this.signOrderAffixList = signOrderAffixList;
    }
    private List<CommonAffix> idCardAffixList;
    private List<CommonAffix> signOrderAffixList;

    public List<CommonAffix> getIdCardAffixList() {
        return idCardAffixList;
    }

    public void setIdCardAffixList(List<CommonAffix> idCardAffixList) {
        this.idCardAffixList = idCardAffixList;
    }

    public List<CommonAffix> getSignOrderAffixList() {
        return signOrderAffixList;
    }

    public void setSignOrderAffixList(List<CommonAffix> signOrderAffixList) {
        this.signOrderAffixList = signOrderAffixList;
    }
}
