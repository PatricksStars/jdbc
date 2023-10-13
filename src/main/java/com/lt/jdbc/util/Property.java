package com.lt.jdbc.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 加载property 读取oracle连接池
 * @author luot
 * @date   2023年7月18日
 *
 *
 */
public class Property {
	
	private static Properties properties = new Properties();
	
	public static void loadProperty(String propertiesPath) {
		FileInputStream is = null;
		try{
            is = new FileInputStream(propertiesPath);
            properties.load(is);  //将配置信息加载到properties对象中
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        	try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
	
	static {
		loadProperty(Property.class.getResource("/application.properties").getPath());
	}
	
	public static String getDriver() {
		return (String) properties.get("spring.datasource.driver-class-name");
	}
	
	public static String getUrl() {
		return (String) properties.get("spring.datasource.url");
	}
	
	public static String getName() {
		return (String) properties.get("spring.datasource.username");
	}
	
	public static String getPassword() {
		return (String) properties.get("spring.datasource.password");
	}
	
	public static Properties getProperties() {
		return properties;
	}
}
