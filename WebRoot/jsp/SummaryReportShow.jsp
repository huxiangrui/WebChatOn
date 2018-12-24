<%@ page language="java" import="java.util.*" import="report.SummaryReport" import="reportservice.SummaryService" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'SummaryReportShow.jsp' starting page</title>
  </head>
  <body>
    <%
    Object osummary=session.getAttribute("summaryReport");
    SummaryReport summaryReport;
    if(osummary==null){
    	summaryReport=new SummaryReport();
    }
    else{
    	summaryReport=(SummaryReport)osummary;
    }
    ArrayList<SummaryService> summaryServiceList=summaryReport.getSummaryServiceList();
    String reportDateString=summaryReport.getReportDateString();
    int providerCount=summaryReport.getProviderCount();
    int consultNoCount=summaryReport.getConsultNoCount();
    double sumFee=summaryReport.getSumFee();
    %>
    <table border="1">
    <tr>
      <th colspan="4">概要报告</th>
    </tr>
    <tr>
      <td colspan="2">报告时间</td>
      <td colspan="2"><%=reportDateString %></td>
    </tr>
    <tr>
      <td>提供者姓名</td>
      <td>提供者编号</td>
      <td>咨询总数</td>
      <td>该周总酬金</td>
    </tr>
    <% for(int i=0;i<summaryServiceList.size();i++){
    	SummaryService summaryService=summaryServiceList.get(i); %>
    <tr>
      <td><%=summaryService.getProviderName() %></td>
      <td><%=summaryService.getProviderNumber() %></td>
      <td><%=summaryService.getConsultCount() %></td>
      <td><%=summaryService.getSumFee() %></td>
    </tr>
    <% } %>
    <tr>
      <td colspan="2">提供者总数</td>
      <td colspan="2"><%=providerCount %></td>
    </tr>
    <tr>
      <td colspan="2">咨询总数</td>
      <td colspan="2"><%=consultNoCount %></td>
    </tr>
    <tr>
      <td colspan="2">总支付费用</td>
      <td colspan="2"><%=sumFee %></td>
    </tr>
    </table><br/>
    
    <form action="jsp/ManagerChoose.jsp" method="get">
      <input type="submit" value="确认">
    </form>
  </body>
</html>
