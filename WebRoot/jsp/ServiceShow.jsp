<%@ page language="java" import="java.util.*" import="object.Service" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'ServiceShow.jsp' starting page</title>
    <script type="text/javascript" src=<%=basePath+"js/jquery-3.2.1.min.js" %>></script>
  </head>
  
  <body>
  <%
  Object oserviceList=session.getAttribute("serviceList");
  ArrayList<Service> serviceList=new ArrayList<Service>();
  if(oserviceList!=null){
	  serviceList=(ArrayList<Service>)oserviceList;
  }
  %>
    <table id="table" border="1">
    <tr>
      <th>服务代码</th>
      <th>服务名称</th>
      <th>服务费用</th>
    </tr>
    <% for(int i=0;i<serviceList.size();i++){ 
    	Service service=serviceList.get(i); %>
    	<tr>
    	  <td><%=service.getCode() %></td>
    	  <td><%=service.getName() %></td>
    	  <td><%=service.getFee() %></td>
    	</tr>
    <% } %>
    </table>
    
    <form action="jsp/OperatorChoose.jsp" method="get">
    <input type="submit" value="确认">
    </form>
  </body>
</html>
