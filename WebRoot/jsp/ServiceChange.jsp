<%@ page language="java" import="java.util.*" import="object.Service" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'ServiceChange.jsp' starting page</title>
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
    
    <form id="form" action="ServiceChange" method="post">
    <label>请选择更改的服务的服务代码</label><br/>
    <% for(int i=0;i<serviceList.size();i++){ 
    	Service service=serviceList.get(i); %>
    	<input type="radio" name="radio" value=<%=service.getCode() %>><%=service.getCode() %><br>
    <% } %>
        <label for="name">服务名称</label>
    <input type="text" id="name" name="name" maxLength="20" required><br/>
    <label for="fee">服务费</label>
    <input type="number" id="fee" name="fee" max=1000 required><br/>
    <input type="submit" value="确认">
    <input type="submit" formaction="jsp/OperatorChoose.jsp" value="返回">
    </form>
  </body>
</html>
