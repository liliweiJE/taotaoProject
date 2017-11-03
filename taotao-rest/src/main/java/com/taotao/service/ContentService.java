package com.taotao.service;

import java.util.List;

import com.taotao.pojo.Content;

public interface ContentService {
	
	public List<Content> getListContent(Long contentCid);

}
