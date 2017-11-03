package com.taotao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.UserMapper;
import com.taotao.pojo.User;
import com.taotao.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	public User findById(Long id){
		return userMapper.selectByPrimaryKey(id);
	}
}
