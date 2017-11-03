package com.taotao.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * @author Administrator
 *对象，byte[]的相互转换
 */
public class ObjectUtils {
	
	/**
	 * 对象转byte[]
	 * @throws IOException 
	 */
	
	public static byte[] poTobyte(Object o) throws IOException{
		
		ByteArrayOutputStream bs=new ByteArrayOutputStream();
		ObjectOutputStream ob=new ObjectOutputStream(bs);
		ob.writeObject(o);
		byte[] array = bs.toByteArray();
		bs.close();
		ob.close();
		return array;
		
	} 
	
	/**
	 * byte[]转对象
	 * @return
	 * @throws IOException 
	 */
	public static Object byteTopo(byte[] array) throws Exception{
		ByteArrayInputStream oo=new ByteArrayInputStream(array);
		ObjectInputStream os=new ObjectInputStream(oo);
		return os.readObject();
	}

}
