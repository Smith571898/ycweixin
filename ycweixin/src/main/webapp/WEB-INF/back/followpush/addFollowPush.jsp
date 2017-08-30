<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>源辰微信后台管理</title>
<%
	String path = request.getContextPath(); // /bbs
	//http ://                         localhost     :      8080                /bbs/
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/JavaScript" src="js/jquery-form.js"></script>
<script type="text/javascript" src="easyui15/jquery.min.js"></script>
<script type="text/javascript" src="easyui15/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui15/jquery.edatagrid.js"></script>
<link rel="stylesheet" type="text/css" href="easyui15/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui15/themes/icon.css">
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
</head>
<script type="text/javascript">
	function xmTanUploadImg(obj) {
		var file = obj.files[0];

		console.log(obj);
		console.log(file);
		console.log("file.size = " + file.size); //file.size 单位为byte

		var reader = new FileReader();

		reader.onload = function(e) {
			console.log("成功读取....");

			var img = document.getElementById("showpic");
			img.src = e.target.result;

		}
		reader.readAsDataURL(file)
	}

	function AddFollowPush() {

		$("#addFollowPushForm").submit()
		$.message.show()
	}

	/* 
	 var formData = new FormData(document.getElementById("addFollowPushForm"));
			
	$.ajax({
			url:"doupdateFollowPush.action",
			data:formData,
			type:"post",
			dataType:'JSON',
			contentType: false,//必须false才会自动加上正确的Content-Type
			processData: false,//必须false才会避开jQuery对 formdata 的默认处理XMLHttpRequest会对 formdata 进行正确的处理
			success:function(data){
				if(data.code==1){
					alert('修改成功')
				}else{
					alert('修改失败')
				}
			}
			
		})	 */
</script>
<form action="doAddFollowPush.action" id="addFollowPushForm" name="addFollowPushForm" method="post" enctype="multipart/form-data">
	文章 标题
	<input type="text" name="ftitle" id="ftitle" />
	<br /> <br /> 设为关注时推送:
	<input type="radio" id="isfollowyes" name="ss" value="是">
	&nbsp;&nbsp;
	<input id="isfollowno" type="radio" name="ss" value="否">
	<p>
		图片上传前预览：
		<input type="file" name="fpic" id="xdaTanFileImg" onchange="xmTanUploadImg(this)" accept="image/*" />
		<input type="button" value="隐藏图片" onclick="document.getElementById('showpic').style.display = 'none';" />
		<input type="button" value="显示图片" onclick="document.getElementById('showpic').style.display = 'block';" />
		<input type="button" value="移除图片"
			onclick="document.getElementById('showpic').style.display = 'none';document.getElementById('xdaTanFileImg').value='';document.getElementById('showpic').src=''"
		/>
	</p>
	<img id="showpic" src="" width="200px" height="150px" style="display: block" />
	<br /> 文章内容:
	<textarea class="ckeditor" name="fcontent"></textarea>
		<script type="text/javascript">
		CKEDITOR.replace('fcontent', {
			filebrowserImageUploadUrl : 'uploadImg.action',
			language : 'zh-cn',
		});
	</script>
	<div style="margin: 0px auto; text-align: center; margin-top: 10px">
		<input type="button" id="btn" value="确认添加" onclick="AddFollowPush()">
	</div>
</form>
