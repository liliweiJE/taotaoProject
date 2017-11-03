package com.taotao.service;

import java.util.List;

import com.taotao.pojo.CatResult;

public interface ItemCatService {

	public CatResult getItemCatList();
	
	public List<?> getCatList(long parentId);
	
}
