package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.ItemParamItemMapper;
import com.taotao.mapper.ItemParamMapper;
import com.taotao.pojo.ItemParam;
import com.taotao.pojo.ItemParamExample;
import com.taotao.service.ItemParamService;

import ezUI.EasyUIResult;

@Service
public class ItemParamServiceImpl implements ItemParamService {
	
	@Autowired
	private ItemParamMapper itemParamMapper;
	
	@Autowired
	private ItemParamItemMapper itemParamItemMapper;

	@Override
	public EasyUIResult selectItemParam(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		ItemParamExample ipe=new ItemParamExample();
		PageHelper.startPage(page, rows);
		List<ItemParam> list = itemParamMapper.selectByExample(ipe);
		PageInfo<ItemParam> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		EasyUIResult er=new EasyUIResult(total, list);
		return er;
	}

}
