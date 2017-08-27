<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%
	String path = request.getContextPath(); // /bbs
	//http ://                         localhost     :      8080                /bbs/
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="css/all.css">
<link rel="stylesheet" href="css/desktop_bootstrap.min.css">
<link rel="stylesheet" href="css/editor.css">
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/ajaxfileupload.js"></script>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
</head>
<body>
	<div id="wrap">
		<div id="sidebar">
			<div class="previewBox">
				<!-- <p style="margin:10px 14px 0 14px;"><span class="msg-date">2013-08-14</span></p>-->
				<div class="cover" onmouseout="removeCover(this);" onmouseover="showCover(this);">
					<h4 class="msg-t" style="width: 320px;" id="title2div0">
						标题
						<span class="i-title"></span>
					</h4>
					<img id="img" src="images/2.png" style="width: 320px;" />
					<ul class="abs tc sub-msg-opr" style="margin: 10px 10px; height: 150px; width: 320px;">
						<a class="th" href="javascript:void(0)" onclick="editDiv('div0')">
							<div style="width: 320px; height: 120px; font-size: 16px;">编辑</div>
						</a>
					</ul>
				</div>
			</div>
			<div class="sub-add">
				<a class="block tc sub-add-btn" href="javascript:void(0)" id="add">
					<span class="vm dib sub-add-icon"></span>
					增加一条
				</a>
			</div>
		</div>
		<div id="main">
			<div class="msg-editer" id="div0">
				<form method="POST" enctype="multipart/form-data" action="">
					<label class="block" for="">标题</label>
					<input type="text" name="title" value="第0个" id="titlediv0" onchange="setTitle('div0')" class="msg-input" />
					<label class="block" for="">
						作者<em class="mp_desc">（选填）</em>
					</label>
					<input type="text" name="author" value="" id="author" class="msg-input" />
					<label class="block" for="">
						<span class="upload-tip r" id="upload-tip">大图片建议尺寸：720像素 * 400像素</span>
						封面
					</label>
					<div class="cover-area" style="vertical-align: bottom; margin-bottom: 10px;">
						<input type="file" name="file" id="xdaTanFileImg" onchange="xmTanUploadImg(this,'')" accept="image/*" />
						<input type="button" value="隐藏图片" onclick="document.getElementById('showpic').style.display = 'none';" />
						<input type="button" value="显示图片" onclick="document.getElementById('showpic').style.display = 'block';" />
						<input type="button" value="移除图片"
							onclick="document.getElementById('showpic').style.display = 'none';document.getElementById('xdaTanFileImg').value='';document.getElementById('showpic').src='';document.getElementById('img').src=''"
						/>
						<img id="showpic" src="" width="200px" height="150px" style="display: block" />
					</div>
					<!--  <label class="block" for="">图文链接</label>
            		<input type="text" name="Message_URL" value="" id="url" class="msg-input">
					-->
					<label class="block" for="">正文</label>
					<textarea name="content" id="myEditor"></textarea>
					<div class="none" id="url-block" style="margin-top: 14px;">
						<label class="block" for="">
							原文链接<em class='mp_desc'>（选填）</em>
						</label>
						<input type="text" name="content_source_url" value="" id="surl" class="msg-input" />
						<br />
					</div>
				</form>
			</div>
		</div>
		<div style="clear: both; padding-top: 20px;">
			<div style="clear: both; text-align: center; padding-top: 20px; border-top: 1px solid #dddddd;">
				<input type="button" onclick='publishTemplate()' class="btn span2 btn-success" value="保存" />
				<input type="button" onclick="removeTemplate()" class="btn span2 btn-danger" value="删除" />
			</div>
		</div>
	</div>
	<script>
		function xmTanUploadImg(obj, imgid) {
			var file = obj.files[0];

			var reader = new FileReader();

			reader.onload = function(e) {
				var img = document.getElementById('showpic' + imgid);
				var img2 = document.getElementById('img' + imgid);
				img.src = e.target.result;
				img2.src = e.target.result;
			}
			reader.readAsDataURL(file)
		}

		var arr = [ 'div1', 'div2', 'div3', 'div4', 'div5', 'div6', 'div7' ];
		var arr2 = new Array();
		var showDiv = "div0";
		var option = {
			initialContent : '在编辑器中默认显示的内容',//初始化编辑器的内容

			initialFrameHeight : 340
		};
		var editor = CKEDITOR;
		editor.replace("myEditor", {
			filebrowserImageUploadUrl : 'uploadImg.action',
			language : 'zh-cn',
		});

		function showCover(obj) {
			$(obj).addClass("sub-msg-opr-show");
		}
		function removeCover(obj) {
			$(obj).removeClass("sub-msg-opr-show");
		}

		function editDiv(obj) {
			if (showDiv != obj) {
				$("#" + showDiv).hide();
				$("#" + obj).show();
				showDiv = obj;
			}
		}

		function removeDiv(obj) {
			$("#s" + obj).remove();
			$("#" + obj).remove();
			$("#rich" + obj).remove();
			arr.push(obj);
			arr2.splice($.inArray(obj, arr2), 1);
			if (arr2.length == 0) {
				showDiv = "div0";
				$("#" + showDiv).show();
			} else {
				if (obj == showDiv) {
					showDiv = arr2.pop();
					arr2.push(showDiv);
					$("#" + showDiv).show();
				} else {
					$("#" + showDiv).show();
					showDiv = arr2.pop();
					arr2.push(showDiv);
				}
			}
		}

		function setTitle(obj) {
			$("#title2" + obj).text($("#title" + obj).val());
		}

		$("#add")
				.click(
						function() {
							var msgDiv;
							//var msgDiv2;
							if (arr.length == 7) {
								$("#" + showDiv).hide();
								msgDiv = arr.pop();
								arr2.push(msgDiv);
								showDiv = msgDiv;
							} else if (arr.length == 0) {
								alert('最多添加8个图文信息');
								return;
							} else {
								msgDiv = arr.pop();
								//msgDiv2=arr2.pop();
								$("#" + showDiv).hide();
								//arr2.push(msgDiv2);
								arr2.push(msgDiv);
								showDiv = msgDiv;
							}
							$(".previewBox")
									.append(
											"<div class='cover' id='s"
													+ msgDiv
													+ "' style='border-top:1px solid #C6C6C6;height: 120px;' onmouseout='removeCover(this);'"
													+ " onmouseover='showCover(this);'><div> <div style='float:left;width: 250px; word-break:break-all;' id='title2"+msgDiv+"'>标题</div> <div style='float:right;'> "
													+ "<img id='img"+msgDiv+"' src='images/1.png' style='width: 80px;height: 80px;'/> </div> </div> <ul class='abs tc sub-msg-opr' style='margin-left: 0;'> <li><div style='width: 150px;"+
                        " height: 120px; font-size: 16px;'><a style='line-height:100px;' href='javascript:void(0)' onclick='editDiv(\""
													+ msgDiv
													+ "\");return false;'> 编辑</a> "
													+ "<a style='line-height:100px;' href='javascript:void(0)'"
													+ " onclick='removeDiv(\""
													+ msgDiv
													+ "\");return false;'> 删除 </a></div>  </li></ul> </div>");
							$("#main")
									.append(
											" <div class='msg-editer' id='"+msgDiv+"'> "
													+ "<form method='POST' enctype='multipart/form-data' action=''> <label class='block' for=''>标题</label>"
													+ " <input type='text' name='title' id='title"
													+ msgDiv
													+ "' onchange='setTitle(\""
													+ msgDiv
													+ "\")' class='msg-input'>"
													+ "<label class='block' for=''>作者<em class='mp_desc'>（选填）</em></label> <input type='text' name='author' value='' id='author' class='msg-input' />"
													+ "<label class='block' for=''><span class='upload-tip r'id='upload-tip'>大图片建议尺寸：720像素 * 400像素</span>封面</label>"
													+ "<div class='cover-area' style='vertical-align: bottom;margin-bottom: 10px;'><input type='file' name='file' id='xdaTanFileImg"
													+ msgDiv
													+ "'  onchange='xmTanUploadImg(this,\""
													+ msgDiv
													+ "\")' accept='image/*' />"
													+ "<input type='button' value='隐藏图片' onclick='document.getElementById(\"showpic"
													+ msgDiv
													+ "\").style.display = \"none\"' />"
													+ "<input type='button' value='显示图片' onclick='document.getElementById(\"showpic"
													+ msgDiv
													+ "\").style.display = \"block\";' />"
													+ "<input type='button' value='移除图片' onclick='document.getElementById(\"showpic"
													+ msgDiv
													+ "\").style.display = \"none\";document.getElementById(\"xdaTanFileImg"
													+ msgDiv
													+ "\").value=\"\";document.getElementById(\"showpic"
													+ msgDiv
													+ "\").src=\"\";document.getElementById(\"img"
													+ msgDiv
													+ "\").src=\"\"'/>"
													+ " <img id='showpic"+msgDiv+"' src='' width='200px' height='150px' style='display: block' />"
													+
													/* " <label class='block' for=''>图文链接</label>"+
													 "<input type='text' name='Message_URL' value='' id='url' class='msg-input'>"+*/
													"<label class='block' for=''>正文</label><textarea name='content' id='rich"+msgDiv+"'></textarea>"
													+ "<div class='none' id='url-block' style='margin-top: 14px;'>"
													+ "<label class='block' for=''>原文链接<em class='mp_desc'>（选填）</em></label> <input type='text' name='content_source_url' value='' id='surl' class='msg-input' />"
													+ "<br/></div></form> </div>");
							editor.replace("rich" + msgDiv, {
								filebrowserImageUploadUrl : 'uploadImg.action',
								language : 'zh-cn',
							});
						});

		/* 		function ajaxFileUpload(id) {
		 var filename = $("#file" + id).val();
		 var suffix;
		 if (filename != "") {
		 suffix = filename.substr(filename.indexOf(".") + 1,
		 filename.length);
		 }
		 if (filename == "") {
		 alert("请选择要上传的图片");
		 } else if (suffix != "jpg" && suffix != "png") {
		 alert("文件格式有无");
		 } else {
		 $.ajaxFileUpload({
		 url : 'fileUpload', //用于文件上传的服务器端请求地址
		 type : 'post',
		 fileElementId : 'file' + id, //文件上传域的ID
		 dataType : 'json', //返回值类型 一般设置为json
		 success : function(data, status) //服务器成功响应处理函数
		 {
		 alert("成功");
		 },
		 error : function(data, status, e)//服务器响应失败处理函数
		 {
		 alert(e);
		 }
		 })
		 }
		 } */

		function publishTemplate() {
			if ("@ViewBag.Template.Row_ID") {
				var result = window.confirm("确定发布这条图文？");
				if (result) {
					//处理ckeditor的值
					for (instance in CKEDITOR.instances) {
						CKEDITOR.instances[instance].updateElement();
					}
					var divs = $("#main").find('form');
					for (var i = 0; i < divs.length; i++) {
						var data = new FormData(divs[i]);
						if(i==0){
							data.append("head","1");
						}
						if(i==divs.length-1){
							data.append("tail","1")
						}
						$.ajax({
							url : 'addArticle.action',
							method : 'POST',
							async : false,
							data : data,
							contentType : false, // 注意这里应设为false
							processData : false, //
							cache : false,
							success : function(data) {
								
							},
							error : function(jqXHR) {
								console.log(JSON.stringify(jqXHR));
							}
						}).done(function(data) {
							console.log('done');
						}).fail(function(data) {
							console.log('fail');
						}).always(function(data) {
							console.log('always');
						});
					}
				}
			}
		}

		function removeTemplate() {
			if ("@ViewBag.Template.Row_ID") {
				var result = window.confirm("确定删除这条图文？");
				if (result) {
					window.location = "@RemoveUrl";
				}
			}
		}
	</script>
</body>
</html>