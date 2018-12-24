<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'MemberCreate.jsp' starting page</title>
    <script type="text/javascript" src=<%=basePath+"js/jquery-3.2.1.min.js" %>></script>
  </head>
    <%
  Object omemberNumberList=session.getAttribute("memberNumberList");
  ArrayList<String> memberNumberList=new ArrayList<String>();
  if(omemberNumberList!=null){
	  memberNumberList=(ArrayList<String>)omemberNumberList;
  }
  %>
  <body>
    <form id="addform" name="addform" action="MemberCreate" method="post">
    <fieldset>
    <legend>添加会员</legend>
    <label for="number">会员编号</label>
    <input type="number"  id="number" name="number" pattern="^[0-9]{9}$" required><br/>
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
    <input type="submit" id="addFinish" value="完成">
    <input type="submit" formaction="jsp/OperatorChoose.jsp" value="返回">
    </fieldset>
    </form>
    
    <script type="text/javascript">
    $(document).on("change","#number",function(){
    	var memberNumber=$(this).val();
        var numberList=new Array();
		<% for(int i=0;i<memberNumberList.size();i++){ %>
		   numberList[<%=i %>]=<%=memberNumberList.get(i) %>;
		<% }%>
		for(var index=0;index<numberList.length;index++){
			if(memberNumber==numberList[index]){
			    alert(memberNumber+"已存在"); 
				return;
			}  			
		} 
    });
    </script>
  </body>
</html>
