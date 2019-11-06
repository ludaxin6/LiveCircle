package com.deshine.huishu.app.workbench.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 方法名称 : com.deshine.business.permissions.entity.Menu
 * 作   者 : wj
 * 创建时间 : 2018/2/22 14:00
 * 方法描述 :
 * <p>
 * 修改作者 :
 * 修改时间 :
 * 修改描述 :
 */

public class Menu implements Serializable {

	private String menuId;

	private String name;

	private String path;

	private String className;

	private String supMenuId;

	private String remark;

	private String sort;

	private String type;

	private List<Menu> menuChild;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Menu> getMenuChild() {
		return menuChild;
	}

	public void setMenuChild(List<Menu> menuChild) {
		this.menuChild = menuChild;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSupMenuId() {
		return supMenuId;
	}

	public void setSupMenuId(String supMenuId) {
		this.supMenuId = supMenuId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
}
