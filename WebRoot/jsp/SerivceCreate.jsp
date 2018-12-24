<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>My JSP 'SerivceCreate.jsp' starting page</title>
    <script type="text/javascript" src=<%=basePath+"js/jquery-3.2.1.min.js" %>></script>
  </head>
  <body>
  <%
  Object oserviceCodeList=session.getAttribute("serviceCodeList");
  ArrayList<String> serviceCodeList=new ArrayList<String>();
  if(oserviceCodeList!=null){
	  serviceCodeList=(ArrayList<String>)oserviceCodeList;
  }
  %>
  
  <form id="addform" name="addform" action="SerivceCreate" method="post">
    <fieldset>
    <legend>添加服务</legend>
    <label for="code">服务代码</label>
    <input type="number"  id="code" name="code" pattern="^[0-9]{6}$" required><br/>
    <label for="name">服务名称</label>
    <input type="text" id="name" name="name" maxLength="20" required><br/>
    <label for="fee">服务费</label>
    <input type="number" id="fee" name="fee" max=1000 required><br/>
    <input type="submit" id="addFinish" value="完成">
    <input type="submit" formaction="jsp/OperatorChoose.jsp" value="返回">
    </fieldset>
  </form>
  <script type="text/javascript">
    $(document).on("change","#code",function(){
    	var serviceCode=$(this).val();
        var serviceCodeList=new Array();
		<% for(int i=0;i<serviceCodeList.size();i++){ %>
		serviceCodeList[<%=i %>]=<%=serviceCodeList.get(i) %>;
		<% }%>
		for(var index=0;index<serviceCodeList.length;index++){
			if(serviceCode==serviceCodeList[index]){
			    alert(serviceCode+"已存在"); 
				return;
			}  			
		} 
    });
  </script>
  </body>
</html>
