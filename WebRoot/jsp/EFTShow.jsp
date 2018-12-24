<%@ page language="java" import="java.util.*" import="object.EFT" import="reportservice.SummaryService" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>My JSP 'EFTShow.jsp' starting page</title>
 </head>
  
  <body>
  <%
  Object oEFT=session.getAttribute("EFT");
  EFT eft=(EFT)oEFT;
  String reportDateString=eft.getReportDateString();
  ArrayList<SummaryService> summaryServices=eft.getSummaryServices();
  %>
  <table border="1">
  <tr><th colspan="6">EFT数据报告</th></tr>
  <tr>
    <td colspan="3">报告时间</td>
    <td colspan="3"><%=reportDateString %></td>
  </tr>
  <tr>
    <td colspan="2">提供者姓名</td>
    <td colspan="2">提供者编号</td>
    <td colspan="2">转移支付数量</td>
  </tr>
  <% for(int i=0;i<summaryServices.size();i++){ 
         SummaryService summaryService=summaryServices.get(i);
         String providerName=summaryService.getProviderName();
         String providerNumber=summaryService.getProviderNumber();
         double sumFee=summaryService.getSumFee(); %>
         <tr>
          <td colspan="2"><%=providerName %></td>
          <td colspan="2"><%=providerNumber %></td>
          <td colspan="2"><%=sumFee %></td>
         </tr>
  <% } %>
  </table>
  
  <form action="EFTChoose.jsp" method="get">
    <input type="submit" value="确认">
  </form>
  </body>
</html>
