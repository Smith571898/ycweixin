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
	<script type="text/javascript">
		//下面用于多图片上传预览功能
		function setImagePreviews(value) {
			var docObj = document.getElementById("fpics");
			var dd = document.getElementById("preview");
			dd.innerHTML = "";
			var fileList = docObj.files;
			for (var i = 0; i < fileList.length; i++) {
				dd.innerHTML += "<div style='float:left' > <img id='img" + i + "' /> </div>";
				var imgObjPreview = document.getElementById("img" + i);
				if (docObj.files && docObj.files[i]) {
					//火狐下，直接设img属性
					imgObjPreview.style.display = 'block';
					//控制缩略图大小
					imgObjPreview.style.width = '70px';
					imgObjPreview.style.height = '70px';
					//imgObjPreview.src = docObj.files[0].getAsDataURL();
					//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
					imgObjPreview.src = window.URL
							.createObjectURL(docObj.files[i]);
				} else {
					//IE下，使用滤镜
					docObj.select();
					var imgSrc = document.selection.createRange().text;
					alert(imgSrc)
					var localImagId = document.getElementById("img" + i);
					//必须设置初始大小
					localImagId.style.width = "250px";
					localImagId.style.height = "250px";
					//图片异常的捕捉，防止用户修改后缀来伪造图片
					try {
						localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
						localImagId.filters
								.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
					} catch (e) {
						alert("您上传的图片格式不正确，请重新选择!");
						return false;
					}
					imgObjPreview.style.display = 'none';
					document.selection.empty();
				}
			}
			return true;
		}
	</script>
	<form action="douploadTemppicbat.action" method="post" enctype="multipart/form-data">
		<p>
			图片上传前预览：
			<input id="fpics" type="file" name="fpics" onchange="javascript:setImagePreviews(this)" accept="image/*" multiple="multiple" />
		</p>
		<div id="preview"></div>
		<br />
		<input type="submit" value="确认上传" />
	</form>
	<p>请尝试在浏览文件时选取一个以上的文件。</p>
</body>
