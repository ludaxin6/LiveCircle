package com.deshine.huishu.app.base.request;

import com.deshine.huishu.app.api.Request;
import com.deshine.huishu.app.customerInvite.model.bean.CommandExecDto;

/**
 * 基础请求类，用于controller层和service层
 * Created by tf on 2017/5/24.
 */
public class BaseRequest<T> extends Request {

    private static final long serialVersionUID = 1L;
    //实体
    private T entity;

    //关键字查询
    private String keyword;

    /**
     * 流程执行dto
     */
    CommandExecDto commandExecDto;

    //高级查询json
    private String advancedSearch;

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? keyword : keyword.trim();
    }

    public CommandExecDto getCommandExecDto() {
        return commandExecDto;
    }

    public void setCommandExecDto(CommandExecDto commandExecDto) {
        this.commandExecDto = commandExecDto;
    }

    public String getAdvancedSearch() {
        return advancedSearch;
    }

    public void setAdvancedSearch(String advancedSearch) {
        this.advancedSearch = advancedSearch;
    }
}
