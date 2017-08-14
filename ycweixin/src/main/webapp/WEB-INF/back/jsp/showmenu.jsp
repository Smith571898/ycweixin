<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>菜单栏</title>
<%
	String path = request.getContextPath(); // /bbs
	//http ://                         localhost     :      8080                /bbs/
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="css/menu.css" media="all">
</head>

<body>
	<div>
		<div style="width: 1100px; height: 680px; float: left;">
			请创建一级菜单<input type="text"><input type="button" value="确认创建"><br /> 请选择一级菜单<select type="text" style="width: 100px;"></select><br />
			请创建二级菜单<input type="text"> <br />
		</div>

		<div style="width: 560px; float: left; height: 680px;">


		</div>




	</div>
</body>
</html>
