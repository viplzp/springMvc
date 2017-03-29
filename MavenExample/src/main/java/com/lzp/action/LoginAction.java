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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 /**
 * 类名: LoginAction
 * 类描述: TODO (用一句话描述这个类做什么).
 * 创建日期: 2017-3-28 下午6:05:18
 * @author lzp
 * @version
 * @since JDK 1.6
 * @see
 */
/**
 * 类名: LoginAction
 * 类描述: TODO (用一句话描述这个类做什么).
 * 创建日期: 2017-3-28 下午6:05:18
 *
 * @author lzp
 * @version 
 * @since JDK 1.6
 */
@Controller
public class LoginAction {
	Logger logger=Logger.getLogger(LoginAction.class);
	/**
	 * Creates a new instance of LoginAction.
	 *
	 */
	@RequestMapping(value="/login")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response,String name) {
		logger.info("登录页面");
		ModelAndView mav=new ModelAndView();
		mav.addObject("name",name);
		mav.setViewName("login");
		return mav;
	}
	 @RequestMapping("/index")
	    public String index(){
	        return "login";
	    }

}

	