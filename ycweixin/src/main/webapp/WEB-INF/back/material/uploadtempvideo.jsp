<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath(); // /bbs
	//http ://                         localhost     :      8080                /bbs/
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<script src="js/jquery-1.11.0.min.js"></script>
</head>
<body>
	<form action="douploadTempVideobat.action" method="post" enctype="multipart/form-data">
		<p>
			图片上传前预览：
			<input id="fvideos" type="file" name="fvideos" onchange="javascript:void(0)" accept=".avi,.mp4,.rmvb,.RM,.WMV,.MOV,.MPEG*" multiple="multiple" />
		</p>
		<div id="preview"></div>
		<br />
		<input type="submit" value="确认上传" />
	</form>
	<p>考虑到大家可能需要上传视频录制工具录制的教程视频，请先将视频转成.exe之外的其他视频格式</p>
</body>
