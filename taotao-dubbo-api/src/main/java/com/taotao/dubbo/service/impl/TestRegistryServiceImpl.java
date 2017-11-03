package com.taotao.dubbo.service.impl;

import org.springframework.stereotype.Service;

import com.taotao.dubbo.service.TestRegistryService;

@Service("testRegistryService")
public class TestRegistryServiceImpl implements TestRegistryService {

	@Override
	public String hello(String name) {
		// TODO Auto-generated method stub
		return "hello"+name;
	}

}
