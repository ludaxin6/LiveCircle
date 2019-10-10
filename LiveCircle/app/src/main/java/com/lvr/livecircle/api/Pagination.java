package com.lvr.livecircle.api;

import java.io.Serializable;

/**
 * 方法名称 : com.deshine.api.Pagination 作 者 : ludx 创建时间 : 2017/4/26 10:15 方法描述 : 分页
 * <p>
 * 修改作者 : 修改时间 : 修改描述 :
 */

public class Pagination implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**
	 * 每页显示数量
	 */
	private int pageSize = 20;

	/**
	 * 当前页面
	 */
	private int pageNum = 1;

	/**
	 * 分页开始位置
	 */
	private int startSize = 0;

	/**
	 * 排序字段
	 */
	private String orderParam;

	/**
	 * 排序方式
	 */
	private String orderType = "asc";

	public Pagination(Integer pageSize, Integer pageNum)
	{
		if (pageSize != null)
		{
			this.pageSize = pageSize;
		}
		if (pageNum != null)
		{
			this.pageNum = pageNum;
		}
	}

	/**
	 *
	 * @param pageSize
	 *            每页显示数量
	 * @param pageNum
	 *            当前页面
	 * @param orderParam
	 *            排序字段
	 * @param orderType
	 *            排序方式
	 */
	public Pagination(Integer pageSize, Integer pageNum, String orderParam, String orderType)
	{
		if (pageSize != null)
		{
			this.pageSize = pageSize;
		}
		if (pageNum != null)
		{
			this.pageNum = pageNum;
		}
		this.orderParam = orderParam;
		this.orderType = orderType;
	}

	public Pagination()
	{

	}

	public String getOrderParam()
	{
		return orderParam;
	}

	public void setOrderParam(String orderParam)
	{
		this.orderParam = orderParam;
	}

	public String getOrderType()
	{
		return orderType;
	}

	public void setOrderType(String orderType)
	{
		this.orderType = orderType;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public int getPageNum()
	{
		return pageNum;
	}

	public void setPageNum(int pageNum)
	{
		this.pageNum = pageNum;
	}

	public int getStartSize()
	{
		if (pageNum >= 1)
		{
			startSize = (pageNum - 1) * pageSize;
		}
		return startSize;
	}

	public void setStartSize(int startSize)
	{
		this.startSize = startSize;
	}
}
