<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'ProviderVerify.jsp' starting page</title>
    <script type="text/javascript" src=<%=basePath+"js/jquery-3.2.1.min.js" %>></script>
  </head>
  
  <body>
  <% 
  Object erroro=session.getAttribute("error");
  if (erroro!=null) { 
	  session.removeAttribute("error");
	  String error=erroro.toString();%>
  <script type="text/javascript">
  $(document).ready(function(){
      alert("提供者编号不存在!");
  });
  </script>
  <% }%>
  <div>
  <form name="form" action="ProviderVerify" method="get">
    <label>提供者编号：</label><br/>
    <input type="text" name="providerNumber" id="providerNumber" pattern="^[0-9]{9}$"><br/>
    <input type="submit" value="确认">
    <input type="submit" formaction="jsp/index.jsp" value="返回">
  </form>
  </div>
  </body>
</html>
