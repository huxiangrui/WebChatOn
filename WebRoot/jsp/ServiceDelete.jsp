<%@ page language="java" import="java.util.*" import="object.Service" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>My JSP 'ServiceDelete.jsp' starting page</title>
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
    
    <form id="form" action="ServiceDelete" method="post">
    <label>请选择删除的服务的服务代码</label><br/>
    <% for(int i=0;i<serviceList.size();i++){ 
    	Service service=serviceList.get(i); %>
    	<input type="checkbox" name="checkbox" value=<%=service.getCode() %>><%=service.getCode() %><br>
    <% } %>
    <input type="submit" value="确认">
    <input type="submit" formaction="jsp/OperatorChoose.jsp" value="返回">
    </form>
  </body>
</html>
