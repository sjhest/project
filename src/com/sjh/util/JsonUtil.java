package com.sjh.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

public class JsonUtil {

	public static String converToJson(Object o) throws IOException  {   
		HttpServletResponse response = ServletActionContext.getResponse();
	    HttpServletRequest request = ServletActionContext.getRequest();
		return null;
	    
	
	}

}
