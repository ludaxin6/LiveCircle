package com.deshine.huishu.app.scan.bean;

import android.app.Activity;

import java.io.Serializable;

public class ScanEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    private String scanValue;
    private String targetActivityName;
    public ScanEvent(){};
    public ScanEvent(String scanValue, String targetActivityName) {
        this.scanValue = scanValue;
        this.targetActivityName = targetActivityName;
    }

    public String getTargetActivityName() {
        return targetActivityName;
    }

    public void setTargetActivityName(String targetActivityName) {
        this.targetActivityName = targetActivityName;
    }

    public String getScanValue() {
        return scanValue;
    }

    public void setScanValue(String scanValue) {
        this.scanValue = scanValue;
    }
}
