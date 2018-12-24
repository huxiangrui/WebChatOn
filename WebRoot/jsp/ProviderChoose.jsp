<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Provider.jsp' starting page</title>

  </head>
  <body>
  <%
  Object oproviderNumber=request.getParameter("providerNumber");
  String providerNumber="";
  if(oproviderNumber!=null){
	  providerNumber=oproviderNumber.toString();%>
	  <p>提供者： <%=providerNumber %></p>
  <%
  } 
  session.setAttribute("providerNumber", providerNumber);
  %>
  
    <form id="form" action="ProviderChoose" method="get">
      <input type="radio" name="option" id="option" value="option1" checked/>判断会员状态/更新会员记录
      <input type="radio" name="option" id="option" value="option2" />查询提供者目录
      <input type="submit" value="确认"/>
      <input type="submit" formaction="jsp/ProviderVerify.jsp" value="返回">
    </form>   

  </body>
</html>
