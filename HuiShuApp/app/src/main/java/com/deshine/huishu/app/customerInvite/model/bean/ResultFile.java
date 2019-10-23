package com.deshine.huishu.app.customerInvite.model.bean;

/**
 * 方法名称 : com.deshine.file.entity.UeditorFile
 * 作   者 : ludx
 * 创建时间 : 2017/4/14 11:04
 * 方法描述 : ueditor 文件上传回传对象
 * <p>
 * 修改作者 :
 * 修改时间 :
 * 修改描述 :
 */

public class ResultFile {
    private String original;
    private String name;
    private String url;
    private Long size;
    private String type;
    private String state = "SUCCESS";

    private String suffix;
    private String scaleImgUrl;

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getScaleImgUrl() {
        return scaleImgUrl;
    }

    public void setScaleImgUrl(String scaleImgUrl) {
        this.scaleImgUrl = scaleImgUrl;
    }
}
