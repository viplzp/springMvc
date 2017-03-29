package com.lzp.util;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * Copyright 2012 CHNBS. All right reserved.
 * 
 * @Description 
 * 读取spring注入的 hponline.properties文件
 * @version 
 * @auther pengzhifu
 * @email
 * @CreationDate Feb 22, 2012 2:33:12 PM
 * @ModificationHistory
 * 
 */
public class SystemGlobals extends PropertyPlaceholderConfigurer {  
	private static final Logger log = Logger.getLogger(SystemGlobals.class);
	      private static Map<String ,String> propertiesMap;
	      
	      /**
	       * 取得properties中的值
	       * @param name
	       * @return
	       */
	       public static String getProperty(String name) {  
	           return propertiesMap.get(name);  
	       }
	       
	       /**
	        * 替换properties中变量
	        * @param key
	        * @param map
	        * @return
	        */
	       public static String getProperty(String key ,Map<String,String> map){
	    	   String rstr = propertiesMap.get(key);
	    	   for(Map.Entry entry  : map.entrySet()){
	    		   rstr = rstr.replace(entry.getKey().toString(), entry.getValue().toString());
	    	   } 	   
	    	   return rstr;
	       }
	      
	      
	      @Override  
	      protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {  
	    	  log.info("初始化");
	          super.processProperties(beanFactory, props);  
	           propertiesMap = new HashMap<String, String>();  
	           String s;
	          for (Object key : props.keySet()) {  

	               String keyStr = key.toString(); 
	               try {
					 s= new String(props.getProperty(keyStr).getBytes("UTF-8"),"UTF-8");
					propertiesMap.put(keyStr, s);  
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
	               
	           }  

	       }  
}