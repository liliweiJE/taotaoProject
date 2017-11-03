package test;

import redis.clients.jedis.Jedis;
import testbean.SayHello;

public class Test {
	public static void main(String[] args) {
		CglibProxy cp=new CglibProxy();
		SayHello proxy = (SayHello)cp.getProxy(SayHello.class);
		proxy.say();
	}
}
