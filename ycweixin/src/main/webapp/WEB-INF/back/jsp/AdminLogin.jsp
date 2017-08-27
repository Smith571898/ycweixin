<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<!DOCTYPE HTML>
<html>
<head>

<title>登录</title>

<script src="js/jquery-1.11.0.min.js"></script>
<script src="js/common.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/login.css" rel="stylesheet" type="text/css" media="all" />
<!-- -->
<script>
	var __links = document.querySelectorAll('a');
	function __linkClick(e) {
		parent.window.postMessage(this.href, '*');
	};
	for (var i = 0, l = __links.length; i < l; i++) {
		if (__links[i].getAttribute('data-t') == '_blank') {
			__links[i].addEventListener('click', __linkClick, false);
		}
	}
</script>
<script>
	$(document).ready(function(c) {
		$('.alert-close').on('click', function(c) {
			$('.message').fadeOut('slow', function(c) {
				$('.message').remove();
			});
		});
	});
</script>

<script type="text/javascript">

function dologin(){
	$.ajax({
		url:'doAdminLogin.action',
		data:$('#loginform').serialize(),
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.code==1){
				alert('登录成功')
				window.location.href='toMain.action'			
				
			}else{
				alert('登录失败'+data.msg)
			}
		}
		
	})
	
	
}
</script>
</head>
<body>
	<!-- contact-form -->
	<div class="message warning">
		<div class="inset">
			<div class="login-head">
				<h1>Login Form</h1>
				<div class="alert-close"></div>
			</div>
			<form id="loginform" >
				<li><input type="text" class="text" name="adminname"><a
					href="#" class=" icon user"></a></li>
				<div class="clear"></div>
				<li><input type="password" name="adminpwd" "> <a
					href="#" class="icon lock"></a></li>
				<div class="clear"></div>
				<div class="submit">
					<input type="button" onclick="dologin()" value="登录">

					<div class="clear"></div>
				</div>

			</form>
		</div>
	</div>
	</div>
	<div class="clear"></div>
	<!--- footer --->
	<div class="footer">
		<p>Copyright &copy; 2014.</p>
	</div>
	<div style="display: none">
		<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script>
	</div>
</body>
</html>