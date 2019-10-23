package com.deshine.huishu.app.customerInvite.model.bean;

import java.io.Serializable;

/**
 * 行为方案-命令
 *
 * @author tanf
 * @Date 2017-09-28 14:17:51
 */
public class TaskDefinitionCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    //ID
    private String tdCommandId;

    //任务定义ID
    private String tdId;

    //名称
    private String name;

    //任务链接信息ID
    private String urlId;

    //默认(0:否1:是)
    private String isDefault;

    //回复要求
    private String replyDemand;

    //允许追回(0:否1:是)
    private Integer isAllowRecover;

    //退回操作(0:否1:是)
    private Integer isReturnOpt;

    //处理不可逆(0:否1:是)
    private Integer irreversible;

    //是否有效(0:无效1:有效)
    private String isEnable;

    //排序
    private Integer sort;

    //===============非表字段=========================

    //任务链接信息
    private String urlName;
    //任务链接地址
    private String url;
    /**
     * 按钮级别按钮-用于控制颜色 邵振添加
     */

    private String level = "COMMON";

    //任务定义属性
    private String tdProperty;

    public void setTdCommandId(String tdCommandId) {
        this.tdCommandId = tdCommandId;
    }

    public String getTdCommandId() {
        return tdCommandId;
    }

    public void setTdId(String tdId) {
        this.tdId = tdId;
    }

    public String getTdId() {
        return tdId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }

    public String getUrlId() {
        return urlId;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
        return sort;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getReplyDemand() {
        return replyDemand;
    }

    public void setReplyDemand(String replyDemand) {
        this.replyDemand = replyDemand;
    }

    public Integer getIsAllowRecover() {
        return isAllowRecover;
    }

    public void setIsAllowRecover(Integer isAllowRecover) {
        this.isAllowRecover = isAllowRecover;
    }

    public Integer getIsReturnOpt() {
        return isReturnOpt;
    }

    public void setIsReturnOpt(Integer isReturnOpt) {
        this.isReturnOpt = isReturnOpt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTdProperty() {
        return tdProperty;
    }

    public void setTdProperty(String tdProperty) {
        this.tdProperty = tdProperty;
    }

    public Integer getIrreversible() {
        return irreversible;
    }

    public void setIrreversible(Integer irreversible) {
        this.irreversible = irreversible;
    }
}
