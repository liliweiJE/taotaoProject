package com.taotao.sso.controller.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.fastdfs.JsonUtils;
import com.taotao.mapper.UserMapper;
import com.taotao.pojo.User;
import com.taotao.pojo.UserExample;
import com.taotao.pojo.UserExample.Criteria;
import com.taotao.sso.controller.service.UserService;
import com.taotao.sso.dao.JedisClient;

@Service
public class UserServiceImpl implements UserService {
	
	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;
	
	@Value("${SSO_SESSION_EXPIRE}")
	private String SSO_SESSION_EXPIRE;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private JedisClient jedisClient;

	@Override
	public TaotaoResult checkData(String content, Integer type) {
		// TODO Auto-generated method stub
		UserExample user=new UserExample();
		Criteria criteria = user.createCriteria();
		
		if (1 == type) {
			criteria.andUsernameEqualTo(content);
		//电话校验
		} else if ( 2 == type) {
			criteria.andPhoneEqualTo(content);
		//email校验
		} else {
			criteria.andEmailEqualTo(content);
		}
		
		List<User> list = userMapper.selectByExample(user);
		if(list!=null&&list.size()>0){
			return TaotaoResult.ok(true);
		}
		return TaotaoResult.ok(false);
	}

	@Override
	public TaotaoResult createUser(User user) {
		// TODO Auto-generated method stub
		user.setUpdated(new Date());
		user.setCreated(new Date());
		
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		
		userMapper.insert(user);
		
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult userLogin(String username, String password) {
		// TODO Auto-generated method stub
		UserExample user=new UserExample();
		Criteria criteria = user.createCriteria();
		criteria.andUsernameEqualTo(username);
		
		List<User> list = userMapper.selectByExample(user);
		if(null==list&&list.size()<=0){
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		
		User us = list.get(0);
		if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(us.getPassword())){
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		
		UUID token = UUID.randomUUID();
		
		//保存用户之前，把用户对象中的密码清空。
		us.setPassword(null);
		//把用户信息写入redis
		jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
		//设置session的过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, Integer.parseInt(SSO_SESSION_EXPIRE));
		//返回token
		return TaotaoResult.ok(token);
		
	}

	@Override
	public TaotaoResult getUserByToken(String token) {
		// TODO Auto-generated method stub
		String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
		
		if(StringUtils.isEmpty(json)){
			return TaotaoResult.build(400, "此session已经过期，请重新登录");
		}
		
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, Integer.parseInt(SSO_SESSION_EXPIRE));
		
		return TaotaoResult.ok(JsonUtils.jsonToPojo(json, User.class));
	}

}
