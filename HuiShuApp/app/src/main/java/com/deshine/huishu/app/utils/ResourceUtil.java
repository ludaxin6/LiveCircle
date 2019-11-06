package com.deshine.huishu.app.utils;

import com.deshine.huishu.app.R;

import java.lang.reflect.Field;

public class ResourceUtil {
    public static int getDrawableResourceId(String fieldName) {
        try {
            Field field = R.drawable.class.getField(fieldName);
            return Integer.parseInt(field.get(null).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static int getStringResuorceId(String fieldName) {
        try {
            Field field = R.string.class.getField(fieldName);
            return Integer.parseInt(field.get(null).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static int getColorResuorceId(String fieldName) {
        try {
            Field field = R.color.class.getField(fieldName);
            return Integer.parseInt(field.get(null).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
