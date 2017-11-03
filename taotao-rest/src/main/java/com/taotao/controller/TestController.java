package com.taotao.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.common.utils.ObjectUtils;
import com.taotao.pojo.TestModel;
import com.taotao.redis.RedisUtils;
import com.taotao.service.RedisService;

import redis.clients.jedis.Jedis;

@Controller
public class TestController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 302972023362156778L;

	@Autowired
	private RedisService redisService;
	
	@Autowired
	private RedisUtils redisUtils;
	
	@RequestMapping("/search")
	public String test(){
		try{
			Jedis jedis = redisUtils.getJedis();
			for(int i=0;i<10;i++){
				TestModel t=new TestModel();
				t.setName(i+"");
				jedis.lpush("testname2".getBytes(), ObjectUtils.poTobyte(t));
			}
			
			getValue(jedis);
			
			getValue(jedis);
			
		}catch(Exception e){
			System.out.println(e);
		}
		return "";
	}
	
	public void getValue(Jedis jedis) throws Exception{
		for(int i=0; i<10; i++) {
			byte[] key = jedis.rpop("testname2".getBytes());
			TestModel byteTopo = (TestModel)ObjectUtils.byteTopo(key);
			System.out.println("弹出：" + byteTopo.getName());//byte数组转换为Object对象的方法，这里省略

		}
	}
}
