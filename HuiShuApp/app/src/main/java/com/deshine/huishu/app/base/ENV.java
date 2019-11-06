package com.deshine.huishu.app.base;

public enum ENV {

    DEV("dev"), //开发环境
    TEST("test"), //测试环境
    PROD("prod") //正式环境
    ;
    private String value;

    private ENV(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}
