package com.deshine.huishu.app.workbench.model.bean;

import java.io.Serializable;

public class Workbench implements Serializable {
    private String id;
    private int index;
    private String name;
    private int imgRes;
    private boolean selected = false;
    public Workbench(){}
    public Workbench(String id, String name, int index, int imgRes){
        this.id = id;
        this.name = name;
        this.index = index;
        this.imgRes = imgRes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
