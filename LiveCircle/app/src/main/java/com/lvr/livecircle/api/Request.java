package com.lvr.livecircle.api;

import java.io.Serializable;

/**
 * 业务层传输基类
 * 
 * @ClassName: Request
 * @Description: 所有的业务实体类都需要继承这个类
 * @author wangjie
 * @date 2017年3月14日 下午6:52:10
 *
 */
public class Request extends Pagination implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userId;

	/**
	 *
	 * @param pageSize 每页显示数量
	 * @param pageNum 当前页面
	 * @param orderParam 排序字段
	 * @param orderType 排序方式
	 */
	public Request(Integer pageSize, Integer pageNum, String orderParam, String orderType) {
		super(pageSize,pageNum,orderParam,orderType);
	}

	public Request() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
