<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html> 
<head> 
　　<title>演示${title}</title> 
　　<meta http-equiv="pragma" content="no-cache"> 
　　<meta http-equiv="cache-control" content="no-cache"> 
　　<meta http-equiv="expires" content="0"> 
　　<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"> 
　　<meta http-equiv="description" content="This is my page"> 
　　<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
</head> 
<body> 
　　<#list users as user> 
　　测试..............username : ${user.userName} 
　　测试..............password : ${user.passWord} 
　　</#list>


</body> 
</html>