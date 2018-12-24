<%@ page language="java" import="java.util.*" import="object.Service" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <title>My JSP 'Claim.jsp' starting page</title>
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
 
  Object oserviceList=session.getAttribute("serviceList");
  ArrayList<Service> serviceList=new ArrayList<Service>();
  if(oserviceList!=null){
	  serviceList=(ArrayList<Service>)oserviceList;
  }
  %>
  <p>提供者编号: <%=providerNumber %></p>
  <p>会员编号:  <%=memberNumber %></p>
  
  <%
  session.setAttribute("providerNumber", providerNumber);
  session.setAttribute("memberNumber",  memberNumber);
  %>
  <form action="jsp/ShowClaim.jsp" method="get">
            提供服务的日期(格式：MM-DD-YYYY)<input type="text" id="serviceDate" name="serviceDate"/><br/>
            服务代码<select name="select" id="select">
            <%for(int i=0;i<serviceList.size();i++){ 
            	String serviceCode=serviceList.get(i).getCode();%>
            	<option value=<%=serviceCode %>><%=serviceCode %></option>
            <%}%>
            </select><br/>
            注释<input type="text" id="note" name="note"/><br/>
     <input type="submit" value="确认"/>
     <input type="submit" formaction="jsp/ProviderChoose.jsp" value="返回">
  </form>
  </body>
</html>
