package com.sjh.project.service;

import java.util.List;

import com.sjh.project.model.Gi;

public interface GiService extends BaseService<Gi>{

	List<Gi> getGiTree(Integer id);

	int giChildrenCount(int id);

	Gi appendGi(Gi model, Integer parentId);

	void removeNodeWithChildren(Gi model);

}
