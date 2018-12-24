<%@ page language="java" import="java.util.*" import="object.Provider" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>My JSP 'ProviderChange.jsp' starting page</title>
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

   <form id="addform" name="addform" action="ProviderChange" method="post">
    <fieldset>
    <legend>更改提供者</legend>
    <label>请输入要更改的提供者编号</label><br/>
    <% for(int i=0;i<providerList.size();i++){ 
    	Provider provider=providerList.get(i); %>
    	<input type="radio" name="radio" value=<%=provider.getNumber() %>><%=provider.getNumber() %><br>
    <% } %>
    <label for="name">提供者姓名</label>
    <input type="text" id="name" name="name" maxLength="25" required><br/>
    <label for="address">提供者地址</label>
    <input type="text" id="address" name="address" maxLength="25" required><br/>
    <label for="city">提供者城市</label>
    <input type="text" id="city" name="city" maxLength="14" required><br/>
    <label for="country">提供者国家</label>
    <input type="text" id="country" name="country" maxLength="2" required><br/>
    <label for="zip">提供者zip码</label>
    <input type="number" id="zip" name="zip" pattern="^[0-9]{5}$" required><br/>
    <label for="email">提供者邮箱</label>
    <input class="text" type="email" id="email" name="email" required><br/>
    <label for="type">提供者服务类型</label>
    <select id="type" name="type">
      <option value="D">Dietitian</option>
      <option value="I">Internist</option>
      <option value="E">Exercise Specialist</option>
    </select>
    <input type="submit" id="addFinish" value="完成">
    <input type="submit" formaction="jsp/OperatorChoose.jsp" value="返回">
    </fieldset>
    </form> 
  </body>
</html>
