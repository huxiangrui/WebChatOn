<%@ page language="java" import="java.util.*" import="object.Provider" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>My JSP 'ProviderManager.jsp' starting page</title>
    <script type="text/javascript" src=<%=basePath+"js/jquery-3.2.1.min.js" %>></script>
  </head>
  
  <body>
  <%
  Object oproviderList=session.getAttribute("providerList");
  ArrayList<Provider> providerList=new ArrayList<Provider>();
  if(oproviderList!=null){
	  providerList=(ArrayList<Provider>)oproviderList;
  }
  %>
    
    <table id="table" border="1">
    <tr>
      <th>提供者编号</th>
      <th>提供者姓名</th>
      <th>提供者地址</th>
      <th>提供者城市</th>
      <th>提供者国家</th>
      <th>提供者zip码</th>
      <th>提供者邮箱</th>
      <th>提供者服务类型</th>
    </tr>
    <% for(int i=0;i<providerList.size();i++){ 
    	Provider provider=providerList.get(i); %>
    	<tr>
    	  <td><%=provider.getNumber() %></td>
    	  <td><%=provider.getName() %></td>
    	  <td><%=provider.getAddress() %></td>
    	  <td><%=provider.getCity() %> </td>
    	  <td><%=provider.getCountry() %> </td>
    	  <td><%=provider.getZip() %></td>
    	  <td><%=provider.getEmail() %></td>
    	  <td><%=provider.getType() %> </td>
    	</tr>
    <% } %>
    </table>
     
    <form id="form" action="ProviderDelete" method="post">
    <label>请选择删除的提供者编号</label><br/>
    <% for(int i=0;i<providerList.size();i++){ 
    	Provider provider=providerList.get(i); %>
    	<input type="checkbox" name="checkbox" value=<%=provider.getNumber() %>><%=provider.getNumber() %><br>
    <% } %>
    <input type="submit" value="确认">
    <input type="submit" formaction="jsp/OperatorChoose.jsp" value="返回">
    </form>
    </body>
</html>
