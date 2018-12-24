<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
  </head>
  
  <body>
    <form action="UserInterface" method="post">
      <label>选择一种身份进入</label><br/>
      <input type="radio" name="option" id="option" value="option1" checked>提供者<br/>
      <input type="radio" name="option" id="option" value="option2" >操作员<br/>
      <input type="radio" name="option" id="option" value="option3" >管理员<br/>
      <input type="radio" name="option" id="option" value="option4" >EFT数据<br/>
      <input type="radio" name="option" id="option" value="option5" >退出<br/>
      <input type="submit" value="确认"/>
    </form>
  </body>
</html>
