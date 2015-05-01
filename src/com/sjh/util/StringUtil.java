package com.sjh.util;

public class StringUtil {

	public static String[] stringToArr(String src, String regx){
		if(ValidationUtil.isValid(src))
			return src.split(regx);
		return null;
	}
}
