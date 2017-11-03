package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.fastdfs.JsonUtils;
import com.taotao.pojo.CatResult;
import com.taotao.service.ItemCatService;

@Controller
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping(value="/itemcat/list",
			produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback){
		CatResult catList = itemCatService.getItemCatList();
		String json = JsonUtils.objectToJson(catList);
		//拼装返回值
		String result = callback + "(" + json + ");";
		return result;
	} 
	
}
