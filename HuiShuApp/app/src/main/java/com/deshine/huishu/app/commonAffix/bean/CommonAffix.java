package com.deshine.huishu.app.commonAffix.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CommonAffix implements Serializable {

	private static final long serialVersionUID = 1L;

	public CommonAffix(){}

	public CommonAffix(String bizId)
	{
		this.bizId = bizId;
	}

	public CommonAffix(String affixId, String bizType, String bizId,
					   String affixUrl, String suffixName, String suffix,Integer size,
					   String createBy,String updateBy, Date createDt, Date updateDt) {
		this.affixId = affixId;
		this.bizType = bizType;
		this.bizId = bizId;
		this.affixUrl = affixUrl;
		this.suffixName = suffixName;
		this.suffix = suffix;
		this.size = size;
		this.createBy = createBy;
		this.createDt = createDt;
		this.updateBy = updateBy;
		this.updateDt = updateDt;
	}

	private String  affixId;

	private String bizType;

	private String bizId;

	private String affixUrl;

	private String suffixName;

	private String suffix;

	private Integer size;

	private String createBy;

	/**
	 * 创建人名称
	 */
	private String userName;

	private Date createDt;

	private String updateBy;

	private Date updateDt;


	private List<String> affixIds;

	private List<String> bizIds;

	public List<String> getAffixIds() {
		return affixIds;
	}

	public void setAffixIds(List<String> affixIds) {
		this.affixIds = affixIds;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public String getAffixId() {
		return affixId;
	}

	public void setAffixId(String affixId) {
		this.affixId = affixId;
	}

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

	public String getAffixUrl() {
		return affixUrl;
	}

	public void setAffixUrl(String affixUrl) {
		this.affixUrl = affixUrl;
	}

	public String getSuffixName() {
		return suffixName;
	}

	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public List<String> getBizIds() {
		return bizIds;
	}

	public void setBizIds(List<String> bizIds) {
		this.bizIds = bizIds;
	}


}