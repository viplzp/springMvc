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
	

 package com.zbd.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;
 /**
 * 类名: SysnDpsInfoTest
 * 类描述: TODO (用一句话描述这个类做什么).
 * 创建日期: 2017-10-26 上午11:54:43
 * @author lzp
 * @version
 * @since JDK 1.6
 * @see
 */
/**
 * 类名: SysnDpsInfoTest
 * 类描述: TODO (用一句话描述这个类做什么).
 * 创建日期: 2017-10-26 上午11:54:43
 *
 * @author lzp
 * @version 
 * @since JDK 1.6
 */

public class SysnDpsInfoTest {
	private static String charset="UTF-8";
	private static String serverName="https://secure.zhubaodai.com";
//	private static String serverName="http://192.168.1.12";
	private static final int BUFFER_SIZE = 1024;
	

	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		System.out.println("开始处理！");///
		String path=System.getProperty("user.dir")+"\\";
		String file=path+"src\\test\\java\\com\\zbd\\test\\dps.txt";;
		boolean flg=true;
		try {
//			String fileStr=getFileStr(file, "UTF-8");
			
			String fileStr = "100002306398";
			String[] ciNos=replaceBlank(fileStr).split(",");
			SysnDpsInfoTest.updDPSInfo(ciNos,flg);
		} catch (Exception e) {			
			e.printStackTrace();
				
		}

	}

	/**
	 * Creates a new instance of SysnDpsInfoTest.
	 *
	 */

	public SysnDpsInfoTest() {
		// TODO Auto-generated constructor stub

	}

	public static void updDPSInfo(String[] ciNos,boolean flg){
		try {			
			Map<String,Object>failMap=new HashMap<String, Object>();
			Map<String,Object>succMap=new HashMap<String, Object>();
			int index=1;
			//运算代码
			long begintime = System.currentTimeMillis();
			for (String ciNo : ciNos) {
				String strUrl=serverName+"/member/memberCenter/modifyBankNo.do?cino="+ciNo;
				long endtime=System.currentTimeMillis();
				long costTime = (endtime - begintime)/1000;
				if(flg){
					String result=sendGet(strUrl, charset);
					
					List<Message> listMsg= JSONObject.parseArray(result, Message.class);
					if(listMsg!=null&&listMsg.size()>0){					
						for (Message msg : listMsg) {
							Map<String, Object> msgMap = (Map<String,Object>)msg.getMessage();
							ResponseMsg header = JSONObject.parseObject(msgMap.get("Header").toString(), ResponseMsg.class);
							String errorMsg=header.getRetMsg();
							if(MsgConstant.HPSSUCCEED.equals(msg.getMsgCode())){
								System.out.println(index+"、成功！编号为-"+ciNo+"的返回结果："+errorMsg+";消耗时间(秒)："+costTime);
								succMap.put(ciNo, errorMsg);
							}else{
								failMap.put(ciNo, errorMsg);
								System.out.println(index+"、失败！编号为-"+ciNo+"的返回结果："+errorMsg+";消耗时间(秒)："+costTime);
								
							}
							
						}
					}
					
				}else{
					sendGetNo(strUrl, charset);
					System.out.println(index+"、编号为-"+ciNo+";消耗时间(秒)："+costTime);
				}
				index++;
		

			}
			System.out.println("===============");
			System.out.println("录入总数："+ciNos.length);
			System.out.println("成功总数："+succMap.size());
			System.out.println("失败总数："+failMap.size());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("异常啊："+e);
		}
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
               
               httpConn
   					.setRequestProperty(
   							"Accept",
   							"image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*");
               httpConn.setRequestProperty("Accept-Language", "zh-cn");
               httpConn.setRequestProperty("UA-CPU", "x86");
               httpConn.setConnectTimeout(6 * 1000);
               httpConn.setReadTimeout(6 * 1000);
               httpConn.setRequestProperty("Charset", "utf-8");
               
               httpConn.connect();
               returnStr = inputStreamToString(httpConn.getInputStream(), charset);

                // 字节流 读取全部内容 包括换行符
          } catch (Exception e) {
        	  return returnStr;
          } finally {
                if (httpConn != null)
                    httpConn.disconnect();
          }
           return returnStr;
    }
	/**
     * post请求
     * @param strUrl
     * @param content
     * @param charset
     * @return
     */
    public static void sendGetNo(String strUrl, String charset)  {
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
               
               httpConn
   					.setRequestProperty(
   							"Accept",
   							"image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*");
               httpConn.setRequestProperty("Accept-Language", "zh-cn");
               httpConn.setRequestProperty("UA-CPU", "x86");
               httpConn.setConnectTimeout(6 * 1000);
               httpConn.setReadTimeout(6 * 1000);
               httpConn.setRequestProperty("Charset", "utf-8");
               
               httpConn.connect();
                // 字节流 读取全部内容 包括换行符
               httpConn.getInputStream();
          } catch (Exception e) {
          } finally {
                if (httpConn != null)
                    httpConn.disconnect();
          }
    }
	/**
     * post请求读取返回值
     * @param InputStream in
     * @param charset
     * @return find . -maxdepth 1 -type f | xargs grep -l '0104138'  |xargs grep -n "100000422888"|more
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

	public static String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	/**
	 * 将文件解析为String
	 * @param fullPath
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String getFileStr(String fullPath, String encoding) throws Exception{
		
		File file = new File(fullPath);
		if(!file.exists()){
			throw new Exception("解析文件 [" + fullPath + "] 不存在");
		}
		
		InputStream in = null;
		ByteArrayOutputStream out = null;
		try{
			in = new FileInputStream(file);
			out = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int r;
			while((r=in.read(buffer))!=-1){
				out.write(buffer,0,r);
				out.flush();
			}
			
			return new String(out.toByteArray(),encoding);
		}
		catch(Exception e){
	//		logger.error("将文件解析为String失败：" + e.getMessage(), e);
			throw e;
		}
		finally{
			if(in!=null){
				in.close();
			}
			if(out!=null){
				out.close();
			}
		}
	}


}

	