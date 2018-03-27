<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
<script type="text/javascript">
	function test(){
		var nrir=document.getElementById('nr').toString().toLowerCase();
		alert(nrir);
	}
</script>    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my <b>check_workflow</b> JSP page. <br>
    
    <iframe src="http://tool.bitefu.net/jiari/vip.php?d=2015&apikey=123456&type=3" name="rd" id="nr"></iframe>
    <input type="button" value="点击提示" id="dj" onclick="test()">
  </body>
</html>
