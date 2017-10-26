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
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private static String serverName="http://192.168.1.11";
	private static final int BUFFER_SIZE = 1024;

	/**
	 * Creates a new instance of SysnDpsInfoTest.
	 *
	 */

	public SysnDpsInfoTest() {
		// TODO Auto-generated constructor stub

	}
	
	public static void updDPSInfo(String[] ciNos){
		try {			
			Map<String,Object>failMap=new HashMap<String, Object>();
			Map<String,Object>succMap=new HashMap<String, Object>();
			int index=1;
			for (String ciNo : ciNos) {
				String strUrl=serverName+"/member/memberCenter/modifyBankNo.do?cino="+ciNo;
				String result=sendGet(strUrl, charset);
				List<Message> listMsg= JSONObject.parseArray(result, Message.class);
				for (Message msg : listMsg) {
					Map<String, Object> msgMap = (Map<String,Object>)msg.getMessage();
					ResponseMsg header = JSONObject.parseObject(msgMap.get("Header").toString(), ResponseMsg.class);
					String errorMsg=header.getRetMsg();
					if(MsgConstant.HPSSUCCEED.equals(msg.getMsgCode())){
						System.out.println(index+"、成功！编号为-"+ciNo+"的返回结果："+errorMsg);
						succMap.put(ciNo, errorMsg);
					}else{
						failMap.put(ciNo, errorMsg);
						System.out.println(index+"、失败！编号为-"+ciNo+"的返回结果："+errorMsg);
						
					}
					
				}
				index++;
			}
			System.out.println("===============");
			System.out.println("录入总数："+ciNos.length);
			System.out.println("成功总数："+succMap.size());
			System.out.println("失败总数："+failMap.size());
		} catch (Exception e) {
			// TODO: handle exception
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
                // 字节流 读取全部内容 包括换行符
               returnStr = inputStreamToString(httpConn.getInputStream(), charset);
          } catch (Exception e) {
                return returnStr;
          } finally {
                if (httpConn != null)
                    httpConn.disconnect();
          }
           return returnStr;
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
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		//select '"'||CI_NO||'",' AS CI_NO from DPSFNTACCINFO WHERE  BINDSERIALNO IS NOT NULL AND  VIRECARDNO IS NULL;
		System.out.println("开始处理！");
		String[] ciNos={"100000392438",
				"100000392678",
				"100000393128",
				"100000407778",
				"100000422728",
				"100000422848",
				"100000422878",
				"100000422888",
				"100000422898",
				"100000515918",
				"100000541718",
				"100000627528",
				"100000648568",
				"100000690438",
				"100001764568",
				"100001740558",
				"100001764248",
				"100001764258",
				"100001764308",
				"200000579728",
				"100001764388",
				"100001764808",
				"100001764408",
				"100001764418",
				"200001764448",
				"100001764488",
				"100000419538",
				"100001764758",
				"100001764778",
				"100001764978",
				"100001745298",
				"100001765188",
				"100001765408",
				"100001765558",
				"100001764478",
				"100001764628",
				"100001764638",
				"100001764648",
				"100001764828",
				"100001764888",
				"100001764588",
				"100001764498",
				"100001764668",
				"100001764458",
				"100001764548",
				"100001765418",
				"100000393428",
				"100000393708",
				"100000393718",
				"100000394218",
				"100000394308",
				"100000394328",
				"100000394388",
				"100000394438",
				"100000394458",
				"100000394478",
				"100000394488",
				"100000394498",
				"100000394538",
				"100000394548",
				"100001764298",
				"100001764338",
				"100001764348",
				"100001764358",
				"100000392448",
				"100000392528",
				"100000392538",
				"100000392708",
				"100000392758",
				"100000393008",
				"100001765358",
				"100001765468",
				"100001765378",
				"100000419028",
				"100000416008",
				"100000415988",
				"100000415998",
				"100000419238",
				"100000416688"};
		SysnDpsInfoTest.updDPSInfo(ciNos);

	}


}

	