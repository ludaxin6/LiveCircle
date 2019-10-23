package com.deshine.huishu.app.customerInvite.model.bean;

import com.deshine.huishu.app.api.Response;

/**
 * @author shaozhen
 */
public class FinanceBillResponse extends Response {

    private FinanceBillDto financeBill = new FinanceBillDto();

    public FinanceBillDto getFinanceBill() {
        return financeBill;
    }

    public void setFinanceBill(FinanceBillDto financeBill) {
        this.financeBill = financeBill;
    }


}
