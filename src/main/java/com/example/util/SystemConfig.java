package com.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取某配置文件里的配置
 * SystemConfig
 * 创建人:yzk 
 * 时间：2018年10月8日-下午4:53:55 
 * @version 1.0.0
 *
 */
public class SystemConfig {
	
	private static final String CONFIG_PROPERTIES="ossConfig.properties";//自定义配置文件

	public static String getConfigResource(String key) throws IOException{
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties properties=new Properties();
		InputStream in=loader.getResourceAsStream(CONFIG_PROPERTIES);
		properties.load(in);
		String value=properties.getProperty(key);
		// 编码转换，从ISO-8859-1转向指定编码  
		value=new String(value.getBytes("ISO-8859-1"),"UTF-8");
		in.close();
		return value;
	}

}
