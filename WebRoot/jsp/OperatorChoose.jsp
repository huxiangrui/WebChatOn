<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'OperatorChoose.jsp' starting page</title>
  </head>
  <body>
  <form action="OperatorChoose" method="get">
     添加提供者<input type="radio" value="option1" name="option" id="option" checked><br/>
     删除提供者<input type="radio" value="option2" name="option" id="option"><br/>
     修改提供者<input type="radio" value="option3" name="option" id="option"><br/>
     查看提供者<input type="radio" value="option4" name="option" id="option"><br/><br/>
     添加会员<input type="radio" value="option5" name="option" id="option"><br/>
     删除会员<input type="radio" value="option6" name="option" id="option"><br/>
     修改会员<input type="radio" value="option7" name="option" id="option"><br/>
     查看会员<input type="radio" value="option8" name="option" id="option"><br/><br/>
     添加服务<input type="radio" value="option9" name="option" id="option"><br/>
     删除服务<input type="radio" value="option10" name="option" id="option"><br/>
     修改服务<input type="radio" value="option11" name="option" id="option"><br/>
     查看服务<input type="radio" value="option12" name="option" id="option"><br/>
  <input type="submit" value="确认">
  <input type="submit" formaction="jsp/index.jsp" value="返回">
  </form>
  </body>
</html>
