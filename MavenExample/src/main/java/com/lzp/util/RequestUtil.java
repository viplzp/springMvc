package com.lzp.util;



import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


/**
 * @ClassName RequestUtil
 * @Description 处理REQUEST的工具类
 * @author liming
 * @date Jun 26, 2014 2:10:14 PM
 */
public class RequestUtil{
	private static final int BUFFER_SIZE = 1024;
	/**
	 * (慎用)调用request.getParameter的时候java容器会自动对word进行一次解码
	 * @Description: 根据key获取rquest参数中的值
	 * @param @param request
	 * @param @param key
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String stringValue(HttpServletRequest request,String key){
		String value = null;
		try {
			value=java.net.URLDecoder.decode(request.getParameter(key), "utf-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("request获取参数异常");
		}
		return value;
	}
	
	
	public static String encode(String url){
		String val=null;
		try {
			val=java.net.URLEncoder.encode(url,"utf-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("request获取参数异常");
		}
		return val;
	}
	
	public static String decode(String url){
		String value = null;
		try {
			value=java.net.URLDecoder.decode(url,"utf-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("request获取参数异常");
		}
		return value;
	}
	
	/**
     * post请求
     * @param strUrl
     * @param content
     * @param charset
     * @return
     */
    public static String sendPost(String strUrl, String content, String charset)  {
          URL httpurl = null;
          HttpURLConnection httpConn = null;
          String returnStr = "";
          PrintWriter outs = null;
           try {
               httpurl = new URL(strUrl);
               httpConn = (HttpURLConnection) httpurl.openConnection();
               httpConn.setRequestMethod("POST"); // 默认是post
                // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在 http正文内，因此需要设为true, 默认情况下是false;  
               httpConn.setDoOutput( true);
                // 设置是否从httpUrlConnection读入，默认情况下是true;
               httpConn.setDoInput( true);
       //        httpConn.setRequestProperty( "Content-Type", "text/html;charset=utf-8");
       //        httpConn.setRequestProperty( "Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
               httpConn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
               
               httpConn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
               httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
               httpConn.setRequestProperty("Referer", "http://iof.hexun.com/2015/p2pfyb/"); 
               httpConn.setRequestProperty("Accept-Encoding", "gzip, deflate"); 
               httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:42.0) Gecko/20100101 Firefox/42.0"); 
               httpConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"); 
               httpConn.setRequestProperty("Connection", "Keep-Alive"); 
               httpConn.setRequestProperty("Cache-Control", "no-cache"); 
               
            //   httpConn.setRequestProperty("Charset", "UTF-8");
               outs = new PrintWriter(httpConn.getOutputStream());
               outs.write(content);
               outs.flush();
               outs.close();
                // 字节流 读取全部内容 包括换行符
               returnStr = inputStreamToString(httpConn.getInputStream(), charset);
          } catch (Exception e) {
        	  System.out.println( "执行HTTP Post请求" + strUrl + "时，发生异常！" );
                if(outs != null){
                    outs.close();
                    outs = null;
               }
                return returnStr;
          } finally {
                if (httpConn != null)
                    httpConn.disconnect();
                if(outs != null){
                    outs.close();
                    outs = null;
               }
          }
           return returnStr;
    }
	/**
     * post请求读取返回值
     * @param InputStream in
     * @param charset
     * @return
     */
    public static String inputStreamToString(InputStream in,String encoding) throws Exception{ 
       ByteArrayOutputStream outStream = new ByteArrayOutputStream(); 
       byte[] data = new byte[BUFFER_SIZE]; 
       int count = -1; 
       while((count = in.read(data,0, BUFFER_SIZE)) != -1)
           outStream.write(data, 0, count);
       in.close();
       data = null; 
       return new String(outStream.toByteArray(),encoding); 
   } 
	/**
     * HttpClient  post请求
     * @param url
     * @param params
     * @param charset
     * @return
     */
    public static String sendPost(String url, Map<String, String> params,String charset) {
       StringBuffer response = new StringBuffer();
       HttpClient client = new HttpClient();
       PostMethod method = new PostMethod(url);
       method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");  
       method.addRequestHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
       method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
       method.setRequestHeader("Referer", "http://iof.hexun.com/2015/p2pfyb/"); 
       method.setRequestHeader("Accept-Encoding", "gzip, deflate"); 
       method.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:42.0) Gecko/20100101 Firefox/42.0"); 
       method.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"); 
       method.setRequestHeader("Connection", "Keep-Alive"); 
       method.setRequestHeader("Cache-Control", "no-cache"); 
      // method.setRequestHeader("Accept-Charset", "gbk"); 
       method.setRequestHeader("Host", "hxapp.hexun.com"); 
       Cookie cookie = new Cookie();
       client.getState().addCookie(new Cookie(".hexun.com", "vjuids", "-2bb83fbf.150cc5646d1.0.75f6cbd946ee7"));
       client.getState().addCookie(new Cookie(".hexun.com", "vjlast", "1446537218.1447722520.11"));
       client.getState().addCookie(new Cookie(".hexun.com", "HexunTrack", "SID=201511031554001469c77f1f63f0a492eaa77807c4b2fa464&CITY=4403&TOWN=440300"));
/*       method.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
       method.getParams().setContentCharset("UTF-8");

       method.setRequestHeader("Cookie", "special-cookie=value");*/
     //  method.setRequestHeader("Charset", "gbk2312");
       // 设置Http Post数据
       if (params != null) {
            HttpMethodParams p = new HttpMethodParams();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                 p.setParameter(entry.getKey(), entry.getValue());
            }
            method.setParams(p);
       }
       try {
            client.executeMethod(method);
            if (method.getStatusCode() == HttpStatus.SC_OK) {
                 BufferedReader reader = new BufferedReader(
                           new InputStreamReader(method.getResponseBodyAsStream(),
                                     charset));
                /* BufferedReader reader2 = new BufferedReader(
                         new InputStreamReader(method.getResponseBodyAsStream(),
                                   "GB2312"));
                 System.out.println("reader2" + reader2.readLine() + "！");
                 BufferedReader reader3 = new BufferedReader(
                         new InputStreamReader(method.getResponseBodyAsStream(),
                                   "UTF-8"));      
                 System.out.println("reader3" + reader3.readLine() + "！");
                 BufferedReader reader4 = new BufferedReader(
                         new InputStreamReader(method.getResponseBodyAsStream(),
                                   "gbk"));    
                 System.out.println("reader4" + reader4.readLine() + "！");*/
                 String line;
                 while ((line = reader.readLine()) != null) {
                      response.append(line);
                 }
                 reader.close();
            }
       } catch (IOException e) {
            System.out.println("执行HTTP Post请求" + url + "时，发生异常！");
       } finally {
            method.releaseConnection();
       }
       return response.toString();
  }
    /**
     * post请求
     * @param strUrl
     * @param content
     * @param charset
     * @return
     */
    public static String sendGet(String strUrl, String charset)  {
          URL httpurl = null;
          HttpURLConnection httpConn = null;
          String returnStr = "";
           try {
               httpurl = new URL(strUrl);
               httpConn = (HttpURLConnection) httpurl.openConnection();
               httpConn.setRequestMethod("GET"); // 默认是post
                // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在 http正文内，因此需要设为true, 默认情况下是false;  
               httpConn.setDoOutput( false);
                // 设置是否从httpUrlConnection读入，默认情况下是true;
               httpConn.setDoInput( true);
               httpConn.setIfModifiedSince(999999999);
               httpConn.setRequestProperty( "Content-Type", "text/xml");
               httpConn.setRequestProperty("User-Agent", "zbd");
               httpConn.setRequestProperty("Charset", "UTF-8");
               httpConn.connect();
                // 字节流 读取全部内容 包括换行符
               returnStr = inputStreamToString(httpConn.getInputStream(), charset);
          } catch (Exception e) {
        	  System.out.println( "执行HTTP Post请求" + strUrl + "时，发生异常！" );
                return returnStr;
          } finally {
                if (httpConn != null)
                    httpConn.disconnect();
          }
           return returnStr;
    }
    //保留指定的cookies
    public static void retainCookies( HttpClient client,String[] cookieNames) {
    	 Cookie[] cookies = client.getState().getCookies();
    	   ArrayList<Cookie> retainCookies = new ArrayList<Cookie>();
    	   for (Cookie cookie : cookies) {
            if (Arrays.binarySearch(cookieNames, cookie.getName()) >= 0) {
    	           retainCookies.add(cookie);
    	        }
      }
       client.getState().clearCookies();
     client.getState().addCookies(retainCookies.toArray(new Cookie[0]));
    } 
    //删除指定的cookies
    public static void removeCookies(HttpClient client,String[] cookieNames) {
    	    Cookie[] cookies = client.getState().getCookies();
    	   ArrayList<Cookie> retainCookies = new ArrayList<Cookie>();
    	  for (Cookie cookie : cookies) {
    	      if (Arrays.binarySearch(cookieNames, cookie.getName()) < 0) {
    	            retainCookies.add(cookie);
    	        }
    	    }
    	    client.getState().clearCookies();
    	    client.getState().addCookies(retainCookies.toArray(new Cookie[0]));
    	} 
    /**
     * @将POJO对象转成Map
     */

   public static Map testReflect(Object obj) {
     Map hashMap = new HashMap();
     try {
      Class c = obj.getClass();
      Method m[] = c.getDeclaredMethods();
      for (int i = 0; i < m.length; i++) {
       if (m[i].getName().indexOf("get")==0) {
         //System.out.println("方法名："+m[i].getName());
        // System.out.println("值："+ m[i].invoke(obj, new Object[0]));
        hashMap.put(m[i].getName(), m[i].invoke(obj, new Object[0]));
       }
      }
     } catch (Throwable e) {
      System.err.println(e);
     }
     return hashMap;
    }

   
  
   
  
  
   
   public static String getFirstUppCase(String s){
	   if(s==null&&s.length()==0)
		   return null;
	   char c = s.charAt(0);
	   if(s.length()>1){
		   s = String.valueOf(c).toUpperCase()+s.substring(1);
	   }else{
		   s = String.valueOf(c).toUpperCase();
	   }
	   return s;
   }
   

  
   
	public static void main(String[] args) throws Exception{
	
	}
	public static void savefile(String str){
		String file = "D:/test/test.txt";
		 FileOutputStream buffer = null;
	        try{
	        	buffer = new FileOutputStream(file);
	        	buffer.write(str.getBytes(), 0, str.length());
	        }catch(Exception e){
	        	
	        }finally{
	        	if(buffer!=null){
	        		try {
						buffer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        }
	}
}
