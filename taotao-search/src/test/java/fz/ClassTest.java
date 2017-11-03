package fz;

import java.lang.reflect.Method;

public class ClassTest {
	
	public static void main(String[] args) {
		try {
			Class<?> forName = Class.forName("fz.ClassTest");
			Method[] declaredMethods = forName.getDeclaredMethods();
			Object instance = forName.newInstance();
			declaredMethods[1].setAccessible(true);
			declaredMethods[1].invoke("liliwei", instance);
			for (Method method : declaredMethods) {
				System.out.println(method.getName());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void te(String name){
		System.out.println("hello:"+name);
	}

}
