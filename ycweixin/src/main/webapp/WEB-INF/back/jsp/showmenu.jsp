<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/menu.css" media="all">
</head>
<body>
<div>
<div style="width:800px;float:left;height:100%;">aaa</div>
<div style="width:500px;float:right;height:100%;">
	

	<div data-role="widget" data-widget="nav4" class="nav4" >
		<nav>
		<div id="nav4_ul" class="nav_4" >
			<ul class="box">
				<li><a href="javascript:;" class=""><span>关于我们</span></a>


					<dl>
						<dd>
							<a href="#"><span>电话</span></a>
						</dd>
						<dd>
							<a href="#"><span>地址</span></a>
						</dd>
						<dd>
							<a href="#"><span>在线客服</span></a>
						</dd>
						<dd>
							<a href="#"><span>在线QQ</span></a>
						</dd>
					</dl></li>
				<li><a href="javascript:;" class=""><span>电商</span></a>


					<dl>
						<dd>
							<a href="#"><span>微信会员卡</span></a>
						</dd>
						<dd>
							<a href="#"><span>微社区</span></a>
						</dd>
						<dd>
							<a href="#"><span>微投票</span></a>
						</dd>
						<dd>
							<a href="#"><span>微调研</span></a>
						</dd>
					</dl></li>
				<li><a href="javascript:;" class="on"><span>会员专区</span></a>


					<dl>
						<dd>
							<a href="#"><span>微商城</span></a>
						</dd>
						<dd>
							<a href="#"><span>微餐饮</span></a>
						</dd>
						<dd>
							<a href="#"><span>微团购</span></a>
						</dd>
						<dd>
							<a href="#"><span>微汽车</span></a>
						</dd>
					</dl></li>

			</ul>
		</div>
		</nav>
	
		<script src="js/nav4.js"></script>
		<script type="text/javascript">
			nav4.bindClick(document.getElementById("nav4_ul").querySelectorAll(
					"li>a"), document.getElementById("nav4_masklayer"));
		</script>
	</div>
	</div>
</div>
</body>
</html>