/*************************************************************************************
 * Copyright (C) 2015 Shenzhen Zhubaodai Internet Financial Services Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳市珠宝贷互联网金融服务股份有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件。
 *************************************************************************************/
 /*************************************************************************************
 * Copyright (C) 2015 Shenzhen Zhubaodai Internet Financial Services Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳市珠宝贷互联网金融服务股份有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件。
 *************************************************************************************/
	

 package com.lzp.util;
 /**
 * 类名: VoteTest
 * 类描述: TODO (用一句话描述这个类做什么).
 * 创建日期: 2017-12-8 上午11:05:06
 * @author lzp
 * @version V1.0
 * @since JDK 1.6
 * @see
 */
/**
 * @ClassName: VoteTest
 * @Description: TODO (用一句话描述这个类做什么).
 * @date: 2017-12-8 上午11:05:06
 * @author lzp
 * @version V1.0
 * @since JDK 1.6
 */

 import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.PrintWriter;  
import java.net.URL;  
import java.net.URLConnection;  
import java.util.List;  
import java.util.Map;  

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
   
 public class HttpRequest {  
   
     public static void main(String[] args) {  
         // 如果不设置，只要代理IP和代理端口正确,此项不设置也可以  
    	 //callbackDoVoteHandler({"code":1});
//         System.getProperties().setProperty("http.proxyHost", "114.7.4.82");  
//         System.getProperties().setProperty("http.proxyPort", "8080");  
         // 判断代理是否设置成功  
         // 发送 GET 请求  
//         String result = sendGet("http://vote.yssai.com/vote/vote/workvote", "callbackDoVoteHandler=jsonp1512697145066&_=1512697217225&cpid=nxRSlI4&crid=e5cd1ac6de87431d88aa63d2e2827742&cgid=S9gdlI4");
//         System.out.println(result);  
    	String ret=" callbackDoVoteHandler({\"code\":1000,\"msg\":\"不能重复投票\"})";
    	JSONObject jsonStr = JSON.parseObject(ret);
         // 发送 POST 请求  
         System.out.println(jsonStr);
     }  
   
     /** 
      * 向指定URL发送GET方法的请求 
      *  
      * @param url 
      *            发送请求的URL 
      * @param param 
      *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。 
      * @return URL 所代表远程资源的响应结果 
      */  
     public static String sendGet(String url, String param) {  
         String result = "";  
         BufferedReader in = null;  
         try {  
             String urlNameString = url + "?" + param;  
             URL realUrl = new URL(urlNameString);  
             // 打开和URL之间的连接  
             URLConnection connection = realUrl.openConnection();  
             // 设置通用的请求属性  
             connection.setRequestProperty("accept", "*/*");  
             connection.setRequestProperty("connection", "Keep-Alive");  
             connection.setRequestProperty("user-agent",  
                     "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
             // 建立实际的连接  
             connection.connect();  
             // 获取所有响应头字段  
             Map<String, List<String>> map = connection.getHeaderFields();  
             // 遍历所有的响应头字段  
             for (String key : map.keySet()) {  
//                 System.out.println(key + "--->" + map.get(key));  
             }  
             // 定义 BufferedReader输入流来读取URL的响应  
             in = new BufferedReader(new InputStreamReader(  
                     connection.getInputStream()));  
             String line;  
             while ((line = in.readLine()) != null) {  
                 result += line;  
             }  
         } catch (Exception e) {  
             System.out.println("发送GET请求出现异常！" + e);  
             e.printStackTrace();  
         }  
         // 使用finally块来关闭输入流  
         finally {  
             try {  
                 if (in != null) {  
                     in.close();  
                 }  
             } catch (Exception e2) {  
                 e2.printStackTrace();  
             }  
         }  
         return result;  
     }  
   
     /**  
      * 向指定 URL 发送POST方法的请求  
      *   
      * @param url  
      *            发送请求的 URL  
      * @param param  
      *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。  
      * @return 所代表远程资源的响应结果  
      */  
     public static String sendPost(String url, String param) {  
         PrintWriter out = null;  
         BufferedReader in = null;  
         String result = "";  
         try {  
             URL realUrl = new URL(url);  
             // 打开和URL之间的连接  
             URLConnection conn = realUrl.openConnection();  
             // 设置通用的请求属性  
             conn.setRequestProperty("accept", "*/*");  
             conn.setRequestProperty("connection", "Keep-Alive");  
             conn.setRequestProperty("user-agent",  
                     "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
             // 发送POST请求必须设置如下两行  
             conn.setDoOutput(true);  
             conn.setDoInput(true);  
             // 获取URLConnection对象对应的输出流  
             out = new PrintWriter(conn.getOutputStream());  
             // 发送请求参数  
             out.print(param);  
             // flush输出流的缓冲  
             out.flush();  
             // 定义BufferedReader输入流来读取URL的响应  
             in = new BufferedReader(  
                     new InputStreamReader(conn.getInputStream()));  
             String line;  
             while ((line = in.readLine()) != null) {  
                 result += line;  
             }  
         } catch (Exception e) {  
             System.out.println("发送 POST 请求出现异常！" + e);  
             e.printStackTrace();  
         }  
         // 使用finally块来关闭输出流、输入流  
         finally {  
             try {  
                 if (out != null) {  
                     out.close();  
                 }  
                 if (in != null) {  
                     in.close();  
                 }  
             } catch (IOException ex) {  
                 ex.printStackTrace();  
             }  
         }  
         return result;  
     }  
     

 }  

	