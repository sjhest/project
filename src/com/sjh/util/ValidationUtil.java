package com.sjh.util;

import java.util.Collection;

/**
 * 校验工具类
 * @author sunjiahui
 *
 */
public class ValidationUtil {

	public static boolean isValid(String src){
		return !(src == null || "".equals(src.trim()));
	}
	
	public static boolean isValid(Collection c){
		if(c == null || c.isEmpty()){
			return false;
		}
		return true;
	}
}
