package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.ItemParam;
import com.taotao.service.ItemParamService;

import ezUI.EasyUIResult;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIResult selectItemParam(Integer page, Integer rows){
		EasyUIResult list=itemParamService.selectItemParam(page,rows);
		return list;
	}
	
}
