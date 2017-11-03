package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.ItemCatMapper;
import com.taotao.pojo.CatNode;
import com.taotao.pojo.CatResult;
import com.taotao.pojo.ItemCat;
import com.taotao.pojo.ItemCatExample;
import com.taotao.pojo.ItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private ItemCatMapper itemCatMapper;

	@Override
	public CatResult getItemCatList() {
		// TODO Auto-generated method stub
		CatResult cr=new CatResult();
		getCatList(0);
		return cr;
	}

	@Override
	public List<?> getCatList(long parentId) {
		// TODO Auto-generated method stub
		ItemCatExample ice=new ItemCatExample();
		Criteria criteria = ice.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<ItemCat> list = itemCatMapper.selectByExample(ice);
		//返回值list
				List resultList = new ArrayList<>();
				//向list中添加节点
				for (ItemCat tbItemCat : list) {
					//判断是否为父节点
					if (tbItemCat.getIsParent()) {
						CatNode catNode = new CatNode();
						if (parentId == 0) {
							catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
						} else {
							catNode.setName(tbItemCat.getName());
						}
						catNode.setUrl("/products/"+tbItemCat.getId()+".html");
						catNode.setItem(getCatList(tbItemCat.getId()));
						
						resultList.add(catNode);
					//如果是叶子节点
					} else {
						resultList.add("/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName());
					}
				}
				return resultList;
	}
	

}
