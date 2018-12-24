<%@ page language="java" import="java.util.*" import="object.Member" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>My JSP 'MemberChange.jsp' starting page</title>
    <script type="text/javascript" src=<%=basePath+"js/jquery-3.2.1.min.js" %>></script>
  </head>
  <body>
  <%
  Object omemberList=session.getAttribute("memberList");
  ArrayList<Member> memberList=new ArrayList<Member>();
  if(omemberList!=null){
	  memberList=(ArrayList<Member>)omemberList;
  }
  %>
    
    <table id="table" border="1">
    <tr>
      <th>会员编号</th>
      <th>会员姓名</th>
      <th>会员地址</th>
      <th>会员城市</th>
      <th>会员国家</th>
      <th>会员zip码</th>
      <th>会员邮箱</th>
      <th>会员状态</th>
    </tr>
    <% for(int i=0;i<memberList.size();i++){ 
    	Member member=memberList.get(i); %>
    	<tr>
    	  <td><%=member.getNumber() %></td>
    	  <td><%=member.getName() %></td>
    	  <td><%=member.getAddress() %></td>
    	  <td><%=member.getCity() %> </td>
    	  <td><%=member.getCountry() %> </td>
    	  <td><%=member.getZip() %></td>
    	  <td><%=member.getEmail() %></td>
    	  <td><%=member.getStatus() %> </td>
    	</tr>
    <% } %>
    </table>
     
    <form id="form" action="MemberDelete" method="post">
    <label>请选择更改的会员编号</label><br/>
    <% for(int i=0;i<memberList.size();i++){ 
    	Member member=memberList.get(i); %>
    	<input type="radio" name="radio" value=<%=member.getNumber() %>><%=member.getNumber() %><br>
    <% } %>
    <label for="name">会员姓名</label>
    <input type="text" id="name" name="name" maxLength="25" required><br/>
    <label for="address">会员地址</label>
    <input type="text" id="address" name="address" maxLength="25" required><br/>
    <label for="city">会员城市</label>
    <input type="text" id="city" name="city" maxLength="14" required><br/>
    <label for="country">会员国家</label>
    <input type="text" id="country" name="country" maxLength="2" required><br/>
    <label for="zip">会员zip码</label>
    <input type="number" id="zip" name="zip" pattern="^[0-9]{5}$" required><br/>
    <label for="email">会员邮箱</label>
    <input type="email" id="email" name="email" required><br/>
    <label for="status">会员状态</label>
    <select id="status" name="status">
      <option value="A">Active</option>
      <option value="S">Suspended</option>
    </select>
    <input type="submit" value="确认">
    <input type="submit" formaction="jsp/OperatorChoose.jsp" value="返回">
    </form>
  </body>
</html>
