package com.deshine.huishu.app.customerInvite.model.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述:物流产品
 *
 * @author shaozhen
 */
public class ExpressGoods implements Serializable {

    public ExpressGoods() {
    }

    public ExpressGoods(String logisticsId, String code, String name) {
        this.logisticsId = logisticsId;
        this.code = code;
        this.name = name;
    }

    public ExpressGoods(String id, String logisticsId, String code, String name, Date createTime) {
        this.id = id;
        this.logisticsId = logisticsId;
        this.code = code;
        this.name = name;
        this.createTime = createTime;
    }

    //id
    private String id;
    //物流商
    private String logisticsId;
    //产品代码
    private String code;
    //产品名称
    private String name;
    //创建时间
    private Date createTime;
    //是否默认-非持久化字段
    private int isDefault = 0;
    private String deliveryUserId;//送货人

    public String getDeliveryUserId() {
        return deliveryUserId;
    }

    public void setDeliveryUserId(String deliveryUserId) {
        this.deliveryUserId = deliveryUserId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(String logisticsId) {
        this.logisticsId = logisticsId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }
}
