package com.sjh.project.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sjh.project.model.EasyUiTree;
import com.sjh.project.model.Gi;
import com.sjh.project.service.GiService;
import com.sjh.util.ObjConvert;

@Controller
@Scope("prototype")
public class GiAction extends BaseAction<Gi> {

	private static final long serialVersionUID = 3630141927485922386L;
	
	private List<EasyUiTree> elist;
	private List<Gi> giTree;
	private Integer parentId;
	
	@Autowired
	GiService giService;
	
	public String toGiPage(){
		return "toGiPage";
	}
	
	public String giTree(){
		System.out.println(model.getId());
		giTree = giService.getGiTree(model.getId());
		elist = new ArrayList<>();
		
		if(giTree.size() > 0){
			for(Gi gi : giTree){
				EasyUiTree e = ObjConvert.giToEasyUiTree(gi);
				int count = giService.giChildrenCount(gi.getId());
				if(count > 0){
					e.setState("closed");
				}
				elist.add(e);
			}
		}
		return "getTree";
	}
	
	public String appendGi(){
		Gi gi =  giService.appendGi(model,parentId);
		elist = new ArrayList<>();
		EasyUiTree e = ObjConvert.giToEasyUiTree(gi);
		int count = giService.giChildrenCount(gi.getId());
		if(count > 0){
			e.setState("closed");
		}
		elist.add(e);
		return "getTree";
	}
	
	public String removeNode(){
		giService.removeNodeWithChildren(model);
		return "getTree";
	}
	
	public String disUpdateNode(){
		Gi gi = giService.getEntity(model.getId());
		elist = new ArrayList<>();
		EasyUiTree e = ObjConvert.giToEasyUiTree(gi);
		elist.add(e);
		return "getTree";
	}
	
	public String updateGi(){
		Gi gi = giService.getEntity(model.getId());
		gi.setText(model.getText());
		gi.setType(model.getType());
		gi.setCode(model.getCode());
		gi.setRemark(model.getRemark());
		gi.setLatitude(model.getLatitude());
		gi.setLongitude(model.getLongitude());
		giService.updateEntity(gi);
		elist = new ArrayList<>();
		EasyUiTree e = ObjConvert.giToEasyUiTree(gi);
		elist.add(e);
		return "getTree";
	}

	public List<EasyUiTree> getElist() {
		return elist;
	}

	public void setElist(List<EasyUiTree> elist) {
		this.elist = elist;
	}

	public List<Gi> getGiTree() {
		return giTree;
	}

	public void setGiTree(List<Gi> giTree) {
		this.giTree = giTree;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}


}
