<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>My JSP  starting page</title>
    <script type="text/javascript" src=<%=basePath+"js/jquery-3.2.1.min.js" %>></script>
  </head>
  <body>
   <% 
  Object erroro=session.getAttribute("error");
  if (erroro!=null) { 
	  session.removeAttribute("error");
	  String error=erroro.toString();
	  if(error.equals("Suspended!")){%>
	   <script type="text/javascript">
	   $(document).ready(function(){
	   alert("会员已暂停!");
	   });
       </script>
       <%}else{ %>
        <script type="text/javascript">
	   $(document).ready(function(){
	   alert("会员不存在!");
	   });
       </script>
  <% } }%>
  
  <%
  Object oproviderNumber=session.getAttribute("providerNumber");
  String providerNumber="";
  if(oproviderNumber!=null){
	  providerNumber=oproviderNumber.toString();%>
	  <p>提供者： <%=providerNumber %></p>
  <%
  } 
  session.setAttribute("providerNumber", providerNumber);
  %>
  
  <form name="form" action="MemberCheck" method="get">
         会员编号：<input type="text" name="memberNumber" id="memberNumber" pattern="^[0-9]{9}$"/>
    <input type="submit" value="确认"/>
    <input type="submit" formaction="jsp/ProviderChoose.jsp" value="返回">
  </form>
  </body>
</html>