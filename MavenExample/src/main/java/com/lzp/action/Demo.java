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

package com.lzp.action;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 类名: Demo
 * 类描述: TODO (用一句话描述这个类做什么).
 * 创建日期: 2017-3-29 下午12:55:17
 * @author lzp
 * @version
 * @since JDK 1.6
 * @see
 */
/**
 * 类名: Demo 类描述: TODO (用一句话描述这个类做什么). 创建日期: 2017-3-29 下午12:55:17
 * 
 * @author lzp
 * @version
 * @since JDK 1.6
 */

public class Demo {

	/**
	 * Creates a new instance of Demo.
	 * 
	 */

	public Demo() {
		// TODO Auto-generated constructor stub

	}
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	public static void main(String[] args) {
		System.out.println("hello world");

		String reqMessage = "";
		Date date = new Date();
		String Cstno="100002768518";
		String bindSerialNo="PP201611171150390005953691";
		String AccNo="6210193310200461076";
		String messageid = sdf.format(date) + String.valueOf(System.currentTimeMillis() - 70000);
//		reqMessage = "<Finance><Message id='" + messageid + "'><UFBReq id='UFBReq'><version>1.0.1</version><instId>ZBD</instId><certId>0006</certId><date>" + sdf.format(date) + "</date><ProjectId>ZBD17030228160899</ProjectId><bindSerialNo>PP201610101228290005679390</bindSerialNo><oriSerialNo>PP1703020338672</oriSerialNo><InvestmentAmount>3000000</InvestmentAmount><currency>156</currency>" + "<extension></extension></UFBReq></Message></Finance>"; // 解冻投资
//		reqMessage = "<Finance><Message id='" + messageid + "'><TSQReq id='TSQReq'><version>1.0.1</version><instId>ZBD</instId><certId>0006</certId><date>" + sdf.format(date) + "</date><orderNo>TZ16101814156123</orderNo><type>7</type><currency>156</currency>" + "<extension></extension></TSQReq></Message></Finance>";// 单笔交易查询
		/*
		 * 1充值2提现3冻结4解冻5投资 6还款7转让 8分账9批量扣款 10批量还款 11流标解冻12平台资金行内划拨
		 */

//		reqMessage = "<Finance><Message id='" + messageid + "'><QZReq id='QZReq'><version>1.0.1</version><instId>ZBD</instId><certId>0006</certId><date>" + sdf.format(date) + "</date><bindSerialNo></bindSerialNo>" + "<extension></extension></QZReq></Message></Finance>";// 增金宝信息查询

//		reqMessage = "<Finance><Message id='" + messageid + "'><AQReq id='AQReq'><version>1.0.1</version><instId>ZBD</instId><certId>0006</certId><date>" + sdf.format(date) + "</date><bindSerialNo>"+bindSerialNo+"</bindSerialNo><AccNo>"+AccNo+"</AccNo>" + "<extension></extension></AQReq></Message></Finance>";// 账户余额查询

//		reqMessage = "<Finance><Message id='" + messageid + "'><QADReq id='QADReq'><version>1.0.1</version><instId>ZBD</instId><certId>0006</certId><date>" + sdf.format(date) + "</date><payerAcc></payerAcc><transDate>20170315</transDate><amount></amount><flag>1</flag><type></type>" + "<extension></extension></QADReq></Message></Finance>";// 内部户来账明细查询

//		reqMessage = "<Finance><Message id='" + messageid + "'><QSSReq id='QSSReq'><version>1.0.1</version><instId>ZBD</instId><certId>0006</certId><date>" + sdf.format(date) + "</date><qingsuanDate>20170314</qingsuanDate>" + "<extension></extension></QSSReq></Message></Finance>";// 6.26
																																																																							// 清算状态查询
//		reqMessage = "<Finance><Message id='" + messageid + "'><QPIReq id='QPIReq'><version>1.0.1</version><instId>ZBD</instId><certId>0006</certId><date>" + sdf.format(date) + "</date><ProjectId>ZBD17030314272768</ProjectId>" + "<extension></extension></QPIReq></Message></Finance>";// 6.30
		//查询开户信息
		reqMessage = "<Finance><Message id='" + messageid + "'><QCReq id='QCReq'><Cstno>"+Cstno+"</Cstno><Custtype>1</Custtype><certType>1</certType><certNo>450921198811063712</certNo><date>" + sdf1.format(date) + "</date><version>1.0.1</version><instId>ZBD</instId><certId>0006</certId></QCReq></Message></Finance>";// 6.30
		
//		reqMessage="<Finance><Message id='" + messageid + "'><AQReq id='AQReq'><bindSerialNo>"+bindSerialNo+"</bindSerialNo><AccNo></AccNo><date>" + sdf.format(date) + "</date><version>1.0.1</version><instId>ZBD</instId><certId>0006</certId></AQReq></Message></Finance>";
		
		System.out.println("原始报文=: java -jar DPSQRY.jar \"" + reqMessage + "\" QCReq");
	}

}
