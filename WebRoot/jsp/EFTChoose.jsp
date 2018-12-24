<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'EFTChoose.jsp' starting page</title>

  </head>
  <body>
  <form action="EFTChoose" method="get">
      <input type="radio" name="option" id="option" value="option1" checked>打印当日EFT报告<br/>
      <input type="radio" name="option" id="option" value="option2" checked>打印之前EFT报告<br/>
      <input type="submit" value="确认" >
      <input type="submit" formaction="index.jsp" value="返回" >
  </form>
  </body>
</html>
