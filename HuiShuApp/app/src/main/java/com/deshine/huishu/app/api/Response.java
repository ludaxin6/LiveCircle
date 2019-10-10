package com.deshine.huishu.app.api;

import java.io.Serializable;

/**
 * 接口响应结果。
 * @author acer
 *
 */
public abstract class Response implements Serializable
{
	
    private static final long serialVersionUID = 1L;

	/**
	 * 返回码
	 * 默认000000成功
	 */
	private String resultCode=ResultCode.SUCCESS;
	
	/**
	 * 返回码描述
	 */
	private String resultDesc = "Success";

	/**
	 * 总条数
	 */
	private int total;
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}


	public String getResultCode()
	{
		return resultCode;
	}

	public void setResultCode(String resultCode)
	{
		this.resultCode = resultCode;
	}

	public String getResultDesc()
	{
		return resultDesc;
	}

	public void setResultDesc(String resultDesc)
	{
		this.resultDesc = resultDesc;
	}
	
}
