<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>My JSP 'ProviderReportCreate.jsp' starting page</title>
  </head>
  <body>
    <%
    Object oprovider=session.getAttribute("providerNumberList");
    ArrayList<String> providerNumberList=new ArrayList<String>();
    if(oprovider!=null){
    	providerNumberList=(ArrayList<String>)oprovider;
    }
    %>
    <form action="ProviderReportCreate" method="get">
    <label>请输入要创建报告的提供者编号</label><br/>
    <% for(int i=0;i<providerNumberList.size();i++){ 
         String providerNumber=providerNumberList.get(i); %>
    	<input type="radio" name="radio" value=<%=providerNumber %>><%=providerNumber %><br>
    <% } %>
    <input type="submit" value="确认"/>
    <input type="submit" formaction="jsp/ManagerChoose.jsp" value="返回">
    </form>
  </body>
</html>
