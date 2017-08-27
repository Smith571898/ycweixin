<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/RobotReplyManager.js"></script>
<script type="text/javascript">
	$(function() {
		// 'findAllMenu.action'
		showoneknowledge();
		
	});


	
	
</script>
</head>
<body>

	<table id="ReplyTable" toolbar="#tb">
	
	<div id="dialog" style="display: none;">
	<form method="post" id="knowledgeform">
	<br/><br/>
	<input type="hidden" id="knowid" name="id"/>
		用户发送的消息<input id="question" type="text" name="question" /><br/><br/>
		小辰回复内容<input id="answer" type="text" name="answer" /><br/><br/>
		菜单类型：&nbsp;&nbsp;<select id="category" name="category"><br/><br/>
				<option value="1" id="button" >普通消息</option>
				<option value="2" id="button" >笑话</option>
				<option value="3" id="button" >上下文</option>
		</select>
		<center>
			<input type="button" id="btn" value="确认修改" onclick="doupdateKnow()"> 
			<input type="button" id="addbtn" value="确认添加" onclick="doAddKnow()">
		</center>
	</form>
</div>

<div id="twoledgedialog" style="display: none;">
	<form method="post" id="twoknowledge">
	<br/><br/>
	<input type="hidden" name="id" id="id">
	<input type="hidden"  name="pid" id="pid"/>
	问题内容<input type="text" id="subquestion" disabled="disabled"><br/><br/>
	请输入小辰回答<input type="text" id="subanswer" name="answer">
	<center>
			<input type="button" id="twobtn" value="确认修改" onclick="doupdatetwoknow()"> 
			<input type="button" id="twoaddbtn" value="确认添加" onclick="doAddtwoknow()">
	</center>
	</form>
	</div>
	
	
	<div id="jokedialog" style="display: none;">
	<form method="post" id="jokefrom">
	<br/><br/>
	<input type="hidden" name="joke_id" id="joke_id">
	笑话内容<br/><textarea style="width: 330px; height: 100px;" id="joke_content" name="joke_content"></textarea><br/><br/>
	<center>
			<input type="button" id="jokebtn" value="确认修改" onclick="doupdatejoke()"> 
			<input type="button" id="jokeaddbtn" value="确认添加" onclick="doAddjoke()">
	</center>
	</form>
	
	
	
	</div>
		<div id="tb">
			
			请选择消息类型：&nbsp;&nbsp; <select id="sel" style="width: 150px;" onchange="flush()">
	
		<option value="1" id="button">一级问答表</option>
		<option value="2" id="button">笑话</option>
		<option value="3" id="button">二级问答子表</option>
	
		
			</select> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="Add()">添加新记录</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="Delete()">删除</a>
		</div>
	</table>

</body>
</html>