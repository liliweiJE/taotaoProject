package com.taotao.sso.controller.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.User;

public interface UserService {
	
	public TaotaoResult checkData(String content, Integer type);
	
	public TaotaoResult createUser(User user);
	
	public TaotaoResult userLogin(String username, String password);
	
	public TaotaoResult getUserByToken(String token);

}
