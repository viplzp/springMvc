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

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lzp.model.User;
 /**
 * 类名: UserController
 * 类描述: TODO (用一句话描述这个类做什么).
 * 创建日期: 2017-3-31 上午10:31:58
 * @author lzp
 * @version
 * @since JDK 1.6
 * @see
 */
/**
 * 类名: UserController
 * 类描述: TODO (用一句话描述这个类做什么).
 * 创建日期: 2017-3-31 上午10:31:58
 *
 * @author lzp
 * @version 
 * @since JDK 1.6
 */

 @Controller  
 public class UserController {  
   
     /** 
      * Static list of users to simulate Database 
      */  
     private static List<User> userList = new ArrayList<User>();  
   
     // Initialize the list with some data for index screen  
     static {  
         userList.add(new User("Bill", "Gates"));  
         userList.add(new User("Steve", "Jobs"));  
         userList.add(new User("Larry", "Page"));  
         userList.add(new User("Sergey", "Brin"));  
         userList.add(new User("Larry", "Ellison"));  
     }  
   
     /** 
      * Saves the static list of users in model and renders it via freemarker 
      * template. 
      *  
      * @param model 
      * @return The index view (FTL) 
      */  
     @RequestMapping(value = "/users", method = RequestMethod.GET)  
     public String index(@ModelAttribute("model") ModelMap model) {  
   
         model.addAttribute("userList", userList);  
   
         return "users";  
     }  
   
     /** 
      * Add a new user into static user lists and display the same into FTL via 
      * redirect 
      *  
      * @param user 
      * @return Redirect to /index page to display user list 
      */  
     @RequestMapping(value = "/add", method = RequestMethod.POST)  
     public String add(@ModelAttribute("user") User user) {  
   
         if (null != user && null != user.getFirstName()  
                 && null != user.getLastName() && !user.getFirstName().isEmpty()  
                 && !user.getLastName().isEmpty()) {  
   
             synchronized (userList) {  
                 userList.add(user);  
             }  
   
         }  
   
         return "redirect:users.html";  
     }  
   
 }  
	