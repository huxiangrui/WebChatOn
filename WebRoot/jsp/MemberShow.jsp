<%@ page language="java" import="java.util.*"  import="object.Member" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MemberShow.jsp' starting page</title>
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
    <form action="jsp/OperatorChoose.jsp" method="get">
    <input type="submit" value="确认">
    </form>
  </body>
</html>