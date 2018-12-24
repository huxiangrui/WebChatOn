<%@ page language="java" import="java.util.*" import="java.text.SimpleDateFormat" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ShowClaim.jsp' starting page</title>
  </head>
  <body>
  <%
  Object oproviderNumber=session.getAttribute("providerNumber");
  String providerNumber="";
  if(oproviderNumber!=null){
	  providerNumber=oproviderNumber.toString();
  } 
 
  Object omemberNumber=session.getAttribute("memberNumber");
  String memberNumber="";
  if(omemberNumber!=null){
	  memberNumber=omemberNumber.toString();
  } 
  
  String serviceDateString=request.getParameter("serviceDate");
  String serviceCode=request.getParameter("select");
  String note=request.getParameter("note");
  
  
  
  Date date=new Date();
  String DATE_TIME_FORMAT = "MM-dd-yyyy HH-mm-ss";	
  SimpleDateFormat dateFormatter = new SimpleDateFormat();
  dateFormatter.applyPattern(DATE_TIME_FORMAT);
  String currentDateString = dateFormatter.format(date);
  %>
  
  <%
  session.setAttribute("providerNumber", providerNumber);
  session.setAttribute("memberNumber",  memberNumber);
  session.setAttribute("serviceDateString", serviceDateString);
  session.setAttribute("serviceCode",  serviceCode);
  session.setAttribute("note", note);
  session.setAttribute("currentDateString",currentDateString);
  %>
  <form action="AddClaim" method="get">
  <table border="1">
  <tr><th>记录</th></tr>
  <tr><td>当前日期</td><td><%=currentDateString %></td></tr>
  <tr><td>服务日期</td><td><%=serviceDateString %></td></tr>
  <tr><td>提供者号码</td><td><%=providerNumber %></td></tr>
  <tr><td>会员号码</td><td><%=memberNumber %></td></tr>
  <tr><td>服务代码</td><td><%=serviceCode %></td></tr>
  <tr><td>注释</td><td><%=note %></td></tr>
  </table><br/>
  <input type="submit" value="确认"/>
  </form>
  </body>
</html>
