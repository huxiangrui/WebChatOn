<%@ page language="java" import="java.util.*" import="report.ProviderReport" import="reportservice.ProviderService" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
  </head>
  <body>
    <%
    Object oprovider=session.getAttribute("providerReport");
    ProviderReport providerReport=(ProviderReport)oprovider;
    ArrayList<ProviderService> providerServiceList=providerReport.getProviderServices();
    String name=providerReport.getName();
    String number=providerReport.getNumber();
    String address=providerReport.getAddress();
    String city=providerReport.getCity();
    String country=providerReport.getCountry();
    String zip=providerReport.getZip();
    String reportDateString=providerReport.getReportDateString();
    int totalCount=providerReport.getTotalCount();
    double totalFee=providerReport.getTotalFee();
    %>
    
    <table border="1">
    <tr>
      <th colspan="6">提供者报告</th>
    </tr>
    <tr>
      <td colspan="3">报告时间</td>
      <td colspan="3"><%=reportDateString %></td>
    </tr>
    <tr>
      <td colspan="3">提供者姓名</td>
      <td colspan="3"><%=name %></td>
    </tr>
    <tr>
      <td colspan="3">提供者编号</td>
      <td colspan="3"><%=number %></td>
    </tr>
    <tr>
      <td colspan="3">提供者街道地址</td>
      <td colspan="3"><%=address %></td>
    </tr>
    <tr>
      <td colspan="3">提供者城市</td>
      <td colspan="3"><%=city %></td>
    </tr>
    <tr>
      <td colspan="3">提供者国家</td>
      <td colspan="3"><%=country %></td>
    </tr>
    <tr>
      <td colspan="3">提供者zip码</td>
      <td colspan="3"><%=zip %></td>
    </tr>
    <tr>
      <td>服务日期</td>
      <td>提交日期</td>
      <td>会员姓名</td>
      <td>会员编号</td>
      <td>服务代码</td>
      <td>需要付的费用</td>
    </tr>
    <% for(int i=0;i<providerServiceList.size();i++){
    	ProviderService providerService=providerServiceList.get(i); %>
    <tr>
      <td><%=providerService.getServiceDateString() %></td>
      <td><%=providerService.getCurrentDateString() %></td>
      <td><%=providerService.getMemberName() %></td>
      <td><%=providerService.getMemberNumber() %></td>
      <td><%=providerService.getServiceCode() %></td>
      <td><%=providerService.getFee() %></td>
    </tr>
    <% } %>
    <tr>
      <td colspan="3">会员咨询总数</td>
      <td colspan="3"><%=totalCount %></td>
    </tr>
    <tr>
      <td colspan="3">一周总费用</td>
      <td colspan="3"><%=totalFee %></td>
    </tr>
    </table>
    
    <form action="jsp/ManagerChoose.jsp" method="get">
      <input type="submit" value="确认">
    </form>
  </body>
</html>
