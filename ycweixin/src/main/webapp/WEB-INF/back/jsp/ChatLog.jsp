<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		// 'findAllMenu.action'
		showlog('dofindChatLog.action')
	});

	function showlog(url) {
		$('#LogTable').edatagrid(
				{
					url : url,
					pagination : true,
					pageSize : 20,
					pageList : [ 3, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 100,
							200, 500 ],

					title : '菜单管理',
					idField : 'id',
					rownumbers : true,
					nowrap : false,
					sortName : 'create_time',
					sortOrder : 'asc',
					singleSelect : true,

					onError : function(a, b) {
						$messager.alert('错误', '操作失败');
					},

					columns : [ [ {
						field : 'id',
						title : '聊天记录编号',
						width : 100,
						height : 150,
						align : 'center',
						hidden : true
					}, {
						field : 'nickname',
						title : '微信名',
						width : 250,
						height : 150,
						align : 'center'
					}, {
						field : 'create_time',
						title : '最近聊天时间',
						width : 300,
						height : 150,
						align : 'center',
						formatter:function(create_time){
							
							 var tt=new Date(parseInt(create_time) * 1000).toLocaleString().substr(0,17)
								// new Date(subscribe_time).toLocaleString(); 
							   
							    return  tt; 
							}

					}, {
						field : 'req_msg',//所属一级菜单
						title : '最近消息',
						width : 250,
						height : 150,
						align : 'center'
					}, {
						field : 'resp_msg',
						title : '小辰回复',
						width : 300,
						height : 150,
						align : 'center'
					} ] ]

				});
	}

	function findChatLogByDate() {
		var data = $('#dd').val()
		alert(data)
		if(data==''){
			showlog('dofindChatLog.action')
		}else{
		showlog('dofindChatLogByDate.action?createtime=' + data)
		}
	}
</script>
</head>
<body>

	<table id="LogTable" toolbar="#tb">
		<div id="tb">
			选择日期查询日期<input id="dd" type="text" class="easyui-datebox" " name="createtime" data-options="formatter:myformatter,parser:myparser">
			<script type="text/javascript">
				function myformatter(date) {
					var y = date.getFullYear();
					var m = date.getMonth() + 1;
					var d = date.getDate();
					return y + '/' + (m < 10 ? ('0' + m) : m) + '/'
							+ (d < 10 ? ('0' + d) : d);
				}
				function myparser(s) {
					if (!s)
						return new Date();
					var ss = (s.split('/'));
					var y = parseInt(ss[0], 10);
					var m = parseInt(ss[1], 10);
					var d = parseInt(ss[2], 10);
					if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
						return new Date(y, m - 1, d);
					} else {
						return new Date();
					}
				}
			</script>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-findlog" plain="true" onclick="findChatLogByDate()">查询菜单</a>
		</div>
	</table>

</body>
</html>