package test;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {

	 private Enhancer enhancer = new Enhancer();
	 
	 public Object getProxy(Class clazz){
		 enhancer.setSuperclass(clazz);
		 enhancer.setCallback(this);
		 return enhancer.create();
	 }
	
	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("前置代理");
		Object res = arg3.invokeSuper(arg0, arg2);
		System.out.println("后置代理");
		return res;
	}

}
