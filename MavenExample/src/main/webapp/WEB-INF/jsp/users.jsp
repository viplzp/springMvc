<%@ page language="java" contentType="text/html; charset=UTF-8"    
    pageEncoding="UTF-8"%>   
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<html>  
<head><title>ViralPatel.net - FreeMarker Spring MVC Hello World</title>  
<body>  
<div id="header">  
<H2>  
    FreeMarker Spring MVC Hello World  
</H2>  
</div>  
  
<div id="content">  
      
  <fieldset>  
    <legend>Add User</legend>  
  <form name="user" action="add.do" method="post">  
    Firstname: <input type="text" name="firstName" /> <br/>  
    Lastname: <input type="text" name="lastName" />   <br/>  
    <input type="submit" value="   Save   " />  
  </form>  
  </fieldset>  
  <br/>  
  <table class="datatable">  
    <tr>  
        <th>Firstname</th>  <th>Lastname</th>  
    </tr>  
    <c:forEach items="${userList}" var="user">  
        <tr>  
        <td>${user.firstName}</td> <td>${user.lastnNme}</td>  
    </tr>  
    </c:forEach>  
     
  </table>  
model["userList"]  
</div>    
</body>  
</html>