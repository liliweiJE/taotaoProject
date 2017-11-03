package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.ItemDescMapper;
import com.taotao.mapper.ItemMapper;
import com.taotao.pojo.Item;
import com.taotao.pojo.ItemDesc;
import com.taotao.pojo.ItemExample;
import com.taotao.service.ItemService;

import ezUI.EasyUIResult;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ItemDescMapper itemDescMapper;

	@Override
	public EasyUIResult getItemList(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		ItemExample ie=new ItemExample();
		PageHelper.startPage(page, rows);
		List<Item> list = itemMapper.selectByExample(ie);
		
		//取分页信息
		PageInfo<Item> pageInfo = new PageInfo<>(list);
		
		long total = pageInfo.getTotal();
		
		EasyUIResult er=new EasyUIResult(total, list);
		return er;
	}

	@Override
	public TaotaoResult createItem(Item item, String desc) {
		// TODO Auto-generated method stub
		
		long itemId = IDUtils.genItemId();
		
		Date dt=new Date();
		item.setId(itemId);
		item.setCreated(dt);
		item.setUpdated(dt);
		item.setStatus((byte)1);
		
		itemMapper.insert(item);
		
		ItemDesc itemDesc=new ItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(dt);
		itemDesc.setUpdated(dt);
		
		itemDescMapper.insert(itemDesc);
		
		return TaotaoResult.ok();
	}

}
