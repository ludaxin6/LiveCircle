package com.deshine.huishu.app.utils;

import java.util.UUID;

public class CodeUtil {
	
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString().replaceAll("-", "");
		return uuidStr;
	}

	public static String getFixedUUID(String billTypeCode){
		ParamUtils.validateNotEmpty(billTypeCode, "billTypeCode");
		return billTypeCode + "88888888888888888888888888888888";
	}
}
