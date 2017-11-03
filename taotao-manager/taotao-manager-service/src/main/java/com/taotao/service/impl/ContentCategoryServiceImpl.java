package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.ContentCategoryMapper;
import com.taotao.pojo.ContentCategory;
import com.taotao.pojo.ContentCategoryExample;
import com.taotao.pojo.ContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService{

	@Autowired
	private ContentCategoryMapper contentCategoryMapper;

	@Override
	public List<EUTreeNode> getCategoryList(long parentId) {
		// TODO Auto-generated method stub
		ContentCategoryExample cc=new ContentCategoryExample();
		Criteria criteria = cc.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		List<ContentCategory> list = contentCategoryMapper.selectByExample(cc);
		
		List<EUTreeNode> eut=new ArrayList<EUTreeNode>();
		
		for (ContentCategory contentCategory : list) {
			EUTreeNode et=new EUTreeNode();
			
			et.setId(contentCategory.getId());
			et.setText(contentCategory.getName());
			et.setState(contentCategory.getIsParent()?"closed":"open");
			
			eut.add(et);
		}
		return eut;
	}

	@Override
	public TaotaoResult insertContentCategory(long parentId, String name) {
		// TODO Auto-generated method stub
	    ContentCategory contentCategory = new ContentCategory();
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		//'状态。可选值:1(正常),2(删除)',
		contentCategory.setStatus(1);
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		
		contentCategoryMapper.insert(contentCategory);
		
		ContentCategory category = contentCategoryMapper.selectByPrimaryKey(parentId);
		
		if(!category.getIsParent()){
			category.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(category);
		}
		return TaotaoResult.ok(contentCategory);
	}

	@Override
	public TaotaoResult deleteContentCategory(long parentId, long id) {
		// TODO Auto-generated method stub
		int i = contentCategoryMapper.deleteByPrimaryKey(id);
		if(i>0){
			ContentCategoryExample ce=new ContentCategoryExample();
			ce.createCriteria().andParentIdEqualTo(parentId);
			List<ContentCategory> list = contentCategoryMapper.selectByExample(ce);
			if(list==null&&list.size()<=0){
				ContentCategory key = contentCategoryMapper.selectByPrimaryKey(parentId);
				key.setIsParent(false);
				contentCategoryMapper.updateByPrimaryKey(key);
			}
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult updateContentCategory(long id, String name) {
		// TODO Auto-generated method stub
		ContentCategory primaryKey = contentCategoryMapper.selectByPrimaryKey(id);
		primaryKey.setName(name);
		contentCategoryMapper.updateByPrimaryKey(primaryKey);
		return TaotaoResult.ok(primaryKey);
	}
	
	
}
