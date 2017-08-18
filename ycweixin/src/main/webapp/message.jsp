<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
	function sendxml() {
		var xmlData = "<xml><ToUserName><![CDATA[gh_94450eaf9cc0]]></ToUserName><FromUserName><![CDATA[ofsPstzX58O31f6ChX7VS82UvZfk]]></FromUserName><CreateTime>1501818562</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[笑话]]></Content><MsgId>6450261608761361890</MsgId></xml>";
		var xmlDataSubscribe = "<xml><ToUserName><![CDATA[gh_94450eaf9cc0]]></ToUserName><FromUserName><![CDATA[ofsPstzX58O31f6ChX7VS82UvZfk]]></FromUserName><CreateTime>1501818562</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event></xml>";
		$.ajax("${pageContext.request.contextPath}/hello",// 发送请求的URL字符串。  
		{
			type : "POST", //  请求方式 POST或GET  
			contentType : "application/xml", //  发送信息至服务器时的内容编码类型  
			// 发送到服务器的数据。  
			data : xmlDataSubscribe,
			async : true, // 默认设置下，所有请求均为异步请求。如果设置为false，则发送同步请求  
		});
	}
</script>
<body>
	发送消息:
	<input type="button" onClick="javascript:sendxml()" value="发送" />
</body>
</html>