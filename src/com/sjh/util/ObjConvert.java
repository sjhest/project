package com.sjh.util;

import java.util.HashMap;
import java.util.Map;

import com.sjh.project.model.EasyUiTree;
import com.sjh.project.model.Gi;

public class ObjConvert {

	public static EasyUiTree giToEasyUiTree(Gi gi){
		EasyUiTree e = new EasyUiTree();
		
		e.setId(gi.getId());
		e.setText(gi.getText());
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("type", gi.getType());
		attrs.put("code", gi.getCode());
		attrs.put("remark", gi.getRemark());
		attrs.put("longitude", gi.getLongitude());
		attrs.put("Latitude", gi.getLatitude());
		e.setAttributes(attrs);
		
		return e;
	}
}
