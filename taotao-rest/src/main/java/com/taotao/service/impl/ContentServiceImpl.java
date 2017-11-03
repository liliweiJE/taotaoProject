package com.taotao.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.fastdfs.JsonUtils;
import com.taotao.dao.JedisClient;
import com.taotao.mapper.ContentMapper;
import com.taotao.pojo.Content;
import com.taotao.pojo.ContentExample;
import com.taotao.redis.RedisUtils;
import com.taotao.redis.RedisUtils.Hash;
import com.taotao.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private ContentMapper contentMapper;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	@Autowired
	private JedisClient jedisClient;

	@Override
	public List<Content> getListContent(Long contentCid) {
		//先从缓存中取数据
		String json = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentCid+"");
		if(StringUtils.isNotEmpty(json)){
			List<Content> list = JsonUtils.jsonToList(json, Content.class);
			return list;
		}
		
		// TODO Auto-generated method stub
		//没有走数据库查
		ContentExample con=new ContentExample();
		con.createCriteria().andCategoryIdEqualTo(contentCid);
		List<Content> list = contentMapper.selectByExample(con);
		
		//再插入数据库
		String conJson = JsonUtils.objectToJson(list);
		jedisClient.hset(INDEX_CONTENT_REDIS_KEY, contentCid+"", conJson);
		
		return list;
		
	}
}
