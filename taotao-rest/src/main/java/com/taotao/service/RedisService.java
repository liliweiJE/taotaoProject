package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;

public interface RedisService {

	TaotaoResult syncContent(long contentCid);
	
	String getKey(String key);
}
