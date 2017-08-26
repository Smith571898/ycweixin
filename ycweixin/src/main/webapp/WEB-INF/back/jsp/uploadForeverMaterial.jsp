<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
String path = request.getContextPath();   // /bbs
//http ://                         localhost     :      8080                /bbs/
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath %>">

<link href="css/lanrenzhijia.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="diyUpload/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="diyUpload/css/diyUpload.css">

</head>
<style>
#box{ margin:50px auto; width:540px; min-height:400px; background:#FF9;display:block;}

</style>

<body>
	<ul id="tabs">
		<li><a href="#" title="tab1">上传图片</a></li>
		<li><a href="#" title="tab2">上传视频</a></li>
		<li><a href="#" title="tab3">上传音频</a></li>

	</ul>
	
	<div id="content">
		<div id="tab1">
				<iframe src="touploadforeverpic.action" style="overflow:hidden;height:600px;width:1680px"></iframe>
				
				
		</div>
		<div id="tab2">
			<iframe src="touploadForevervideo.action" style="overflow:hidden;height:600px;width:1550px"></iframe>
		</div>
		<div id="tab3">
				<iframe src="touploadForeveraudio.action" style="overflow:hidden;height:600px;width:1550px"></iframe>

		</div>
	</div>
	<br>
	<br>

<script src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#content div").hide(); // Initially hide all content
		$("#tabs li:first").attr("id", "current"); // Activate first tab
		$("#content div:first").fadeIn(); // Show first tab content

		$('#tabs a').click(function(e) {
			e.preventDefault();
			$("#content div").hide(); //Hide all content
			$("#tabs li").attr("id", ""); //Reset id's
			$(this).parent().attr("id", "current"); // Activate this
			$('#' + $(this).attr('title')).fadeIn(); // Show content for current tab
		});

	});

</script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#content div").hide(); // Initially hide all content
		$("#tabs li:first").attr("id", "current"); // Activate first tab
		$("#content div:first").fadeIn(); // Show first tab content

		$('#tabs a').click(function(e) {
			e.preventDefault();
			$("#content div").hide(); //Hide all content
			$("#tabs li").attr("id", ""); //Reset id's
			$(this).parent().attr("id", "current"); // Activate this
			$('#' + $(this).attr('title')).fadeIn(); // Show content for current tab
		});

	});

</script>

</body>
</html>