<%@ page language="java" import="java.util.*" import="report.MemberReport" import="reportservice.MemberService" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'MemberReportShow.jsp' starting page</title>
  </head>
  <body>
    <%
    Object omember=session.getAttribute("memberReport");
    MemberReport memberReport=(MemberReport)omember;
    ArrayList<MemberService> memberServiceList=memberReport.getMemberServices();
    String name=memberReport.getName();
    String number=memberReport.getNumber();
    String address=memberReport.getAddress();
    String city=memberReport.getCity();
    String country=memberReport.getCountry();
    String zip=memberReport.getZip();
    String reportDateString=memberReport.getReportDateString();
    %>
       <table border="1">
    <tr>
      <th colspan="6">会员报告</th>
    </tr>
    <tr>
      <td colspan="3">报告时间</td>
      <td colspan="3"><%=reportDateString %></td>
    </tr>
    <tr>
      <td colspan="3">会员姓名</td>
      <td colspan="3"><%=name %></td>
    </tr>
    <tr>
      <td colspan="3">会员编号</td>
      <td colspan="3"><%=number %></td>
    </tr>
    <tr>
      <td colspan="3">会员街道地址</td>
      <td colspan="3"><%=address %></td>
    </tr>
    <tr>
      <td colspan="3">会员城市</td>
      <td colspan="3"><%=city %></td>
    </tr>
    <tr>
      <td colspan="3">会员国家</td>
      <td colspan="3"><%=country %></td>
    </tr>
    <tr>
      <td colspan="3">会员zip码</td>
      <td colspan="3"><%=zip %></td>
    </tr>
    <tr>
      <td colspan="2">服务日期</td>
      <td colspan="2">提供者姓名</td>
      <td colspan="2">服务名称</td>
    </tr>
    <% for(int i=0;i<memberServiceList.size();i++){
    	MemberService memberService=memberServiceList.get(i); %>
    <tr>
      <td colspan="2"><%=memberService.getServiceDateString() %></td>
      <td colspan="2"><%=memberService.getProviderName() %></td>
      <td colspan="2"><%=memberService.getServiceName() %></td>
    </tr>
    <% } %>
    </table>
    
    <form action="jsp/ManagerChoose.jsp" method="get">
      <input type="submit" value="确认">
    </form>
  </body>
</html>
