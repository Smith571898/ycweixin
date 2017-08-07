<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="js/jquery-1.11.0.min.js"></script>
<script src="js/common.js"></script>
<div class="login">
			<h2>用户登录</h2>
			<form method="post" id="myform" action="AdminLogin.action">
				<dl id="loginBox">
					<dt>用户名：</dt>
					<dd><input type="text" class="input-text" name="uname"  value=""/></dd>
					<dt>密　码：</dt>
					<dd><input type="password" class="input-text" name="pwd"  value=""/></dd>
					<dt>验证码 ：</dt>
					<dd>
					<input type="text" name="zccode" id="zccode"  style="font-size:14px;width:100px;"  />
					<img  src="image.jsp" onclick="changeVilidateCode(this)" border="0" title="点击图片获取验证码" size="10"/></dd>
					<dt></dt>
					<dd><input type="submit" id="inputbutton" class="input-button"  value="登录" /></dd>
				</dl>
			</form>
			<div class="error"></div>
		</div>