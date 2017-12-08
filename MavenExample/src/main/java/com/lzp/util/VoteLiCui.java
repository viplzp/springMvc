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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.SystemUtils;
import org.dom4j.DocumentException;

/**
 * 类名: VoteLiCui
 * 类描述: TODO (用一句话描述这个类做什么).
 * 创建日期: 2017-12-8 下午4:00:46
 * @author lzp
 * @version V1.0
 * @since JDK 1.6
 * @see
 */
/**
 * @ClassName: VoteLiCui
 * @Description: TODO (用一句话描述这个类做什么).
 * @date: 2017-12-8 下午4:00:46
 * @author lzp
 * @version V1.0
 * @since JDK 1.6
 */

public class VoteLiCui {
	
	private static String voteUrl="http://vote.yssai.com/vote/vote/workvote?callbackDoVoteHandler=jsonp1512697145066&_=1512697217225&cpid=nxRSlI4&crid=e5cd1ac6de87431d88aa63d2e2827742&cgid=S9gdlI4";
	private static final int BUFFER_SIZE = 1024;
//	private static String path =  new File(VoteLiCui.class.getResource("/").getPath()).getPath()+File.separatorChar;
	private static String path = SystemUtils.getUserDir().getPath()+ File.separatorChar;

	public static void main(String[] args) {
		try {
			String ipstrs = FileUtil.getFileStr(path  + "votelinesip.txt", "gbk");
			voteTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	public static void voteTest() throws Exception {

		// 打码配置信息开始
		String user = "zhans2008";
		String pass = "zhans2008";
		String softId = "18653";// 软件ID,作者帐号后台添加. 此值仅为演示效果
		String softKey = "ZwkDbiLDAjv1bZn2"; // //软件key,作者帐号后台添加. 此值仅为演示效果
		// 打码配置信息结束

		// 设置刷票数量
		int num = 300;
		// String info = JGetUserInfo(user, pass);
		// System.out.println("剩余点数:"+info);
		Random random = new Random();
		int successnum = 0;
		int failnum = 0;
		String ipstat = "14.153.55.65";
		List lines = null;
		int start = -20;
		int end = 0;
		String linestrs = "";
		String ipstrs = "";
		linestrs = FileUtil.getFileStr(path+"votelines2017.txt", "gbk");
		ipstrs = FileUtil.getFileStr(path+"votelinesip.txt", "gbk");
		System.out.println("linestrs:" + linestrs);
		System.out.println("ipstrs:" + ipstrs);
		for (int k = 0; k < 200; k++) {
			if (lines != null && lines.size() > 0) {
				for (int i = 0; i < lines.size(); i++) {
					Map linemap = (Map) lines.get(i);
					String linename = linemap.get("@name").toString().trim();
					if (linename.indexOf("电信") == -1) {
						continue;
					}
//					System.out.println("linename:" + linename);
					String lineurl = "http://127.0.0.1:8222/connect/?linename=" + URLEncoder.encode(linename, "utf-8") + "&linktype=1";
					String connectR = RequestUtil.sendGet(lineurl, "utf-8");
					Thread.sleep(1000);
					String stateR = RequestUtil.sendGet("http://127.0.0.1:8222/getstate/", "utf-8");
//					System.out.println("stateR:" + stateR);
					int cnn = 0;
					while (stateR.indexOf("已连接") == -1) {
						Thread.sleep(1000);
						stateR = RequestUtil.sendGet("http://127.0.0.1:8222/getstate/", "utf-8");
						cnn++;
						if (cnn >= 8) {
							break;
						}
					}
					if (cnn >= 8) {
						String disconnectR = RequestUtil.sendGet("http://127.0.0.1:8222/disconnect/", "utf-8");
						System.out.println("等待间隔:" + 1000);
						Thread.sleep(1000);
//						System.out.println("disconnectR:" + disconnectR);
						stateR = RequestUtil.sendGet("http://127.0.0.1:8222/getstate/", "utf-8");
//						System.out.println("stateR:" + stateR);
						int bkn = 0;
						while (stateR.indexOf("已断开") == -1) {
							Thread.sleep(1300);
							stateR = RequestUtil.sendGet("http://127.0.0.1:8222/getstate/", "utf-8");
							bkn++;
							if (bkn > 6) {
								break;
							}
						}
						continue;
					}
					Map statemap = getMap(stateR);
					String ip = "";
					ip = ((Map) statemap.get("root")).get("ip").toString();
					System.out.println("ip:" + ip);
					if (ip == null || ip.length() == 0) {
						String disconnectR = RequestUtil.sendGet("http://127.0.0.1:8222/disconnect/", "utf-8");
						System.out.println("等待间隔:" + 1000);
						Thread.sleep(2000);
//						System.out.println("disconnectR:" + disconnectR);
						stateR = RequestUtil.sendGet("http://127.0.0.1:8222/getstate/", "utf-8");
//						System.out.println("stateR:" + stateR);
						int bkn = 0;
						while (stateR.indexOf("已断开") == -1) {
							Thread.sleep(1300);
							stateR = RequestUtil.sendGet("http://127.0.0.1:8222/getstate/", "utf-8");
							bkn++;
							if (bkn > 6) {
								break;
							}
						}
						continue;
					} else {
						if (ipstrs.indexOf(ip) != -1) {
							String disconnectR = RequestUtil.sendGet("http://127.0.0.1:8222/disconnect/", "utf-8");
							System.out.println("等待间隔:" + 1000);
							Thread.sleep(1000);
							stateR = RequestUtil.sendGet("http://127.0.0.1:8222/getstate/", "utf-8");
//							System.out.println("stateR:" + stateR);
							int bkn = 0;
							while (stateR.indexOf("已断开") == -1) {
								Thread.sleep(2000);
								stateR = RequestUtil.sendGet("http://127.0.0.1:8222/getstate/", "utf-8");
								bkn++;
								if (bkn > 4) {
									break;
								}
							}
							continue;
						} else {
							ipstrs += ip + ";";
						}
					}
					FileUtil.WriterFileAppend(ip + ";", "D:/hexin/", "votelinesip.txt");

					int bnum327 = 0;
					int b327 = voteFor327(user, pass, softId);
					while (b327 == 1) {
						Thread.sleep(400);
						b327 = voteFor327(user, pass, softId);
						bnum327++;
						if (bnum327 >= 3) {
							System.out.println("老是投不上，老子不玩了！！！");
							b327 = 2;
							break;
						}
					}
					System.out.println("****************************开始新一轮*******************************\r\n****************************开始新一轮*******************************\r\n" + "****************************开始新一轮*******************************\r\n****************************开始新一轮*******************************\r\n");
					String disconnectR = RequestUtil.sendGet("http://127.0.0.1:8222/disconnect/", "utf-8");
					System.out.println("等待间隔:" + 1000);
					Thread.sleep(1000);
//					System.out.println("disconnectR:" + disconnectR);
					stateR = RequestUtil.sendGet("http://127.0.0.1:8222/getstate/", "utf-8");
//					System.out.println("stateR:" + stateR);
					int bkn = 0;
					while (stateR.indexOf("已断开") == -1) {
						Thread.sleep(2000);
						stateR = RequestUtil.sendGet("http://127.0.0.1:8222/getstate/", "utf-8");
//						System.out.println("stateR:" + stateR);
						bkn++;
						if (bkn > 4) {
							break;
						}
					}
					FileUtil.WriterFileAppend(linename + ";", "D:/hexin/", "votelines2017.txt");

				}
				start = start + 20;
				end = end + 20;
				lines = getLine(start, end);
			} else {
				start = start + 20;
				end = end + 20;
				lines = getLine(start, end);
			}

		}
	}
	public static int voteFor327(String user, String pass1, String softId) throws IOException {
		String imgurl = "http://zt4.oeeee.com/www/Index/verify";
		byte[] img = null;// toByteArrayFromUrl(imgurl);
		String vCokie = "";
		String vCode = "";
		String[] results = new String[]{""};
		InputStream inStream = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PostMethod method = null;
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@开始进行327  327 ");

		System.out.println("vCokie:" + vCokie);
		String returnStr = "";
		InputStream inaa = null;
		try {
			URLConnection urlCon = null;
			URL url = new URL(voteUrl);
			urlCon = url.openConnection();
			urlCon.addRequestProperty("Cookie", vCokie);
			urlCon.setConnectTimeout((int) 100000);
			urlCon.setReadTimeout((int) 100000);
			urlCon.setRequestProperty("Host", "zt4.oeeee.com");
			urlCon.setRequestProperty("Referer", "http://zt4.oeeee.com/www/Index/index/nav_id/327");
			urlCon.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.62 Safari/537.36");
			urlCon.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
			urlCon.setRequestProperty("Connection", "keep-alive");
			urlCon.setRequestProperty("Origin", "http://zt4.oeeee.com/");
			inaa = urlCon.getInputStream();
			returnStr = inputStreamToString(inaa, "utf-8");
		} catch (Exception e) {

		} finally {
			if (inaa != null) {
				try {
					inaa.close();
				} catch (Exception e) {
				}// end try
			}
		}
		System.out.println("--------------------------------------returnStr:" + returnStr);

		return 0;
	}
	/**
	 * post请求读取返回值
	 * 
	 * @param InputStream
	 *            in
	 * @param charset
	 * @return
	 */
	public static String inputStreamToString(InputStream in, String encoding) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);
		in.close();
		data = null;
		return new String(outStream.toByteArray(), encoding);
	}
	public static List getLine(int start, int end) throws DocumentException {
		List list = null;
		System.out.println("start:" + start + ";end:" + end);
		String strUrl = "http://127.0.0.1:8222/getlines/?start=" + start + "&end=" + end + "";
		String resultXml = RequestUtil.sendGet(strUrl, "utf-8");
		Map resultMap = XmlUtil.xml2mapWithAttr(resultXml, true);
		if (resultMap != null && resultMap.get("root") != null) {
			Map temp = (Map) resultMap.get("root");
			if (temp != null) {
				Map temp2 = (Map) temp.get("lines");
				if (temp2 != null) {
					list = (List) temp2.get("line");
				}
			}

		}
		return list;
	}
	public static Map getMap(String xml) throws DocumentException {
		Map resultMap = XmlUtil.xml2mapWithAttr(xml, true);
		return resultMap;
	}

}
