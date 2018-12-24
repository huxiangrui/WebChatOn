<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'EFTSearch.jsp' starting page</title>

  </head>
  <body>
  <%
  Object oreportDateList=session.getAttribute("reportDateList");
  ArrayList<String> reportDateList=new ArrayList<String>();
  if(oreportDateList!=null){
	  reportDateList=(ArrayList<String>)oreportDateList;
  }
  %>
  
  <form id="form" action="EFTSearch" method="get">
    <label>请选择时间</label><br/>
    <% for(int i=0;i<reportDateList.size();i++){ 
    	String reportDate=reportDateList.get(i); %>
    	<input type="radio" name="radio" value=<%=reportDate %> checked><%=reportDate %><br>
    <% } %>
    <input type="submit" value="确认">
    <input type="submit" formaction="jsp/OperatorChoose.jsp" value="返回">
  </form>
  </body>
</html>
