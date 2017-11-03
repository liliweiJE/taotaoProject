package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.ItemCatMapper;
import com.taotao.pojo.ItemCat;
import com.taotao.pojo.ItemCatExample;
import com.taotao.service.ItemCatService;


@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private ItemCatMapper itemCatMapper;
	
	@Override
	public List<EasyUITreeNode> getItemCatList(long parentId) {
		// TODO Auto-generated method stub
		ItemCatExample ic=new ItemCatExample();
		ic.createCriteria().andParentIdEqualTo(parentId);
		List<ItemCat> list = itemCatMapper.selectByExample(ic);
		List<EasyUITreeNode> result=new ArrayList<EasyUITreeNode>();
		
		if(null!=list&&list.size()>0){
			for (ItemCat itemcat : list) {
				EasyUITreeNode eui=new EasyUITreeNode();
				eui.setId(itemcat.getId());
				eui.setText(itemcat.getName());
				eui.setState(itemcat.getIsParent()?"closed":"open");
				result.add(eui);
			}
		}
		return result;
	}

}
