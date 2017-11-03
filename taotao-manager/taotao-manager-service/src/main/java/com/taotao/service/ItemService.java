package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.Item;

import ezUI.EasyUIResult;

public interface ItemService {
	
	EasyUIResult getItemList(Integer page,Integer rows);

	TaotaoResult createItem(Item item, String desc);

}
