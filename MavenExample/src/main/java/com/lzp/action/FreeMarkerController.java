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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lzp.model.User;
 /**
 * 类名: FreeMarkerController
 * 类描述: TODO (用一句话描述这个类做什么).
 * 创建日期: 2017-3-30 下午5:49:15
 * @author lzp
 * @version
 * @since JDK 1.6
 * @see
 */
/**
 * 类名: FreeMarkerController
 * 类描述: TODO (用一句话描述这个类做什么).
 * 创建日期: 2017-3-30 下午5:49:15
 *
 * @author lzp
 * @version 
 * @since JDK 1.6
 */

 @Controller
 @RequestMapping("/home")
 public class FreeMarkerController {

     @RequestMapping("index1")
     public ModelAndView Add(HttpServletRequest request, HttpServletResponse response) {
         ModelAndView mav = new ModelAndView();
         mav.addObject("title", "网站标题");
         User user = new User();
         user.setUserName("<h2>小明=========================================================></h2>");
         user.setPassWord("<a href='https://www.hao123.com/'>百度</a>");
         List<User> users = new ArrayList<User>();
         users.add(user);
         mav.addObject("users", users);
         mav.setViewName("index");
         return mav;
     }

     @RequestMapping("index2")
     public ModelAndView index() {
         ModelAndView mav = new ModelAndView("index");
         mav.addObject("title", "网站标题");
         //说明：在这里可以控制不生成静态htm
         User user = new User();
         user.setUserName("<h2>小明=========================================================></h2>");
         user.setPassWord("<a href='https://www.hao123.com/'>百度</a>");
         List<User> users = new ArrayList<User>();
         users.add(user);
         mav.addObject("users", users);
         mav.addObject("CREATE_HTML", false);
         return mav;
     }
 }
	