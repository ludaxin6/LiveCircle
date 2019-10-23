package com.deshine.huishu.app.customerInvite.model.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 描述:物流商
 *
 * @author shaozhen
 */
public class Logistics implements Serializable {

    public Logistics() {
    }

    public Logistics(String shortName, String code) {
        this.shortName = shortName;
        this.code = code;
    }

    public Logistics(String id, String shortName, String code, Date createTime) {
        this.id = id;
        this.shortName = shortName;
        this.code = code;
        this.createTime = createTime;
    }

    //id
    private String id;
    //简称
    private String shortName;
    //物流商代码
    private String code;

    /**
     * 电话
     */
    private String phone;

    private String webSite;

    /**
     * 电子面单接口
     * 1已开通 、 0 未开通
     */
    private Integer eorderApi;


    /**
     * 套打模板
     * chromatography printing 套打
     * 1 已配置  0 未配置
     */
    private Integer cpTemplet;

    /**
     * 创建人
     */
    private String creator;

    private  String creatorName;
    //创建时间
    private Date createTime;
    private int sort;
    private List<ExpressGoods> goodsList;


    //是否默认-非持久化字段
    private int isDefault = 0;
    private int hasFreight = 0;//是否有运费

		public int getHasFreight() {
			return hasFreight;
		}

		public void setHasFreight(int hasFreight) {
			this.hasFreight = hasFreight;
		}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public Integer getEorderApi() {
        return eorderApi;
    }

    public void setEorderApi(Integer eorderApi) {
        this.eorderApi = eorderApi;
    }

    public Integer getCpTemplet() {
        return cpTemplet;
    }

    public void setCpTemplet(Integer cpTemplet) {
        this.cpTemplet = cpTemplet;
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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public List<ExpressGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<ExpressGoods> goodsList) {
        this.goodsList = goodsList;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
