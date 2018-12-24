<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ManagerChoose.jsp' starting page</title>
  </head>
  <body>
  <form action="ManagerChoose" method="get">
      <input type="radio" name="option" id="option" value="option1" checked>打印提供者报告<br/>
      <input type="radio" name="option" id="option" value="option2" >打印会员报告<br/>
      <input type="radio" name="option" id="option" value="option3" >打印概要报告<br/>
      <input type="submit" value="确认">
      <input type="submit" formaction="jsp/index.jsp" value="返回">
  </form>
  </body>
</html>
