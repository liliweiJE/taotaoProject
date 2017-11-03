package com.taotao.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.mapper.ContentMapper;
import com.taotao.pojo.Content;
import com.taotao.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private ContentMapper contentMapper;
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;
	
	@Override
	public TaotaoResult saveContent(Content con) {
		// TODO Auto-generated method stub
		con.setCreated(new Date());
		con.setUpdated(new Date());
		contentMapper.insert(con);
		
		HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_SYNC_URL);
		
		return TaotaoResult.ok();
	}
}
