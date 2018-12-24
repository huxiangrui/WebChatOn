<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head><title>My JSP 'MemberReportCreate.jsp' starting page</title>
  </head>
  <body>
    <%
    Object omember=session.getAttribute("memberNumberList");
    ArrayList<String> memberNumberList=new ArrayList<String>();
    if(omember!=null){
    	memberNumberList=(ArrayList<String>)omember;
    }
    %>
    
    <form action="MemberReportCreate" method="get">
    <label>请输入要创建报告的会员编号</label><br/>
    <% for(int i=0;i<memberNumberList.size();i++){ 
         String memberNumber=memberNumberList.get(i); %>
    	<input type="radio" name="radio" value=<%=memberNumber %>><%=memberNumber %><br>
    <% } %>
    <input type="submit" value="确认"/>
    <input type="submit" formaction="jsp/ManagerChoose.jsp" value="返回">
    </form>
  </body>
</html>
