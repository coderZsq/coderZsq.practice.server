package cn.wolfcode;

import java.io.FileOutputStream;

import cn.wolfcode.service.impl.EmployeeServiceImpl;
import sun.misc.ProxyGenerator;

public class DynamicProxyClassGenerator {
	public static void main(String[] args) throws Exception {
		generateClassFile(EmployeeServiceImpl.class, "EmployeeServiceProxy");
	}

	//生成代理类的字节码文件-->Java反编译工具-->Java文件
	public static void generateClassFile(Class targetClass, String proxyName) throws Exception {
		//根据类信息和提供的代理类名称，生成字节码  
		byte[] classFile = ProxyGenerator.generateProxyClass(proxyName, targetClass.getInterfaces());
		String path = targetClass.getResource(".").getPath();
		System.out.println(path);
		FileOutputStream out = null;
		//保留到硬盘中  
		out = new FileOutputStream(path + proxyName + ".class");
		out.write(classFile);
		out.close();
	}

}
