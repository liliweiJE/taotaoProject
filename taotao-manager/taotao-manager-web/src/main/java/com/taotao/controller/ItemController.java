package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.Item;
import com.taotao.service.ItemService;

import ezUI.EasyUIResult;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIResult itemList(@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="30")Integer rows){
		
		EasyUIResult itemList = itemService.getItemList(page, rows);
		
		return itemList;
		
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult saveItem(Item item,String desc){
		TaotaoResult result=itemService.createItem(item,desc);
		return result;
	}

}
