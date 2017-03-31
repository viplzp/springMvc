<html>  
<head><title>ViralPatel.net - FreeMarker Spring MVC Hello World</title>  
<body>  
<div id="header">  
<H2>  
    <#--  
    <a href="http://viralpatel.net"><img height="37" width="236" border="0px" src="" align="center"/></a>  
    -->  
    FreeMarker Spring MVC Hello World  
</H2>  
</div>  
  
<div id="content">  
      freeMarker啦啦啦啦
  <fieldset>  
    <legend>Add User</legend>  
  <form name="user" action="add.do" method="post">  
    Firstname: <input type="text" name="firstName" /> <br/>  
    LastName: <input type="text" name="lastName" />   <br/>  
    <input type="submit" value="   Save   " />  
  </form>  
  </fieldset>  
  <br/>  
  <table class="datatable">  
    <tr>  
        <th>FirstName</th>  <th>LastName</th>  
    </tr>  
    <#list model["userList"] as user>  
    <tr>  
        <td>${user.firstName}</td> <td>${user.lastName}</td>  
    </tr>  
    </#list>  
  </table>  
  
</div>    
</body>  
</html>   