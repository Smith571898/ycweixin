<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Insert title here</title>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	$(function() {
		
		showmenu('findOneMenu.action', 'bid', 'bid', 'bid')
		$('#MenuTable').datagrid('hideColumn','sub_name');
	});
	function showmenu(url, idField, sortName, field) {
		// 'findAllMenu.action'
		$('#MenuTable')
				.edatagrid(
						{
							url : url,
							pagination : true,
							pageSize : 5,
							pageList : [ 3, 5, 10, 15, 20, 25, 30, 35, 40, 45,
									50, 100, 200, 500 ],

							title : '菜单管理',
							idField : idField,
							rownumbers : true,
							nowrap : false,
							sortName : sortName,
							sortOrder : 'desc',
							singleSelect : true,

							onError : function(a, b) {
								$messager.alert('错误', '操作失败');
							},

							columns : [ [
									{
										field : field,
										title : '菜单编号',
										width : 100,
										height : 150,
										align : 'center',
										hidden : true
									},
									{
										field : 'name',
										title : '菜单名称',
										width : 250,
										height : 150,
										align : 'center'
									},
									{
										field : 'menutype',
										title : '菜单类型',
										width : 300,
										height : 150,
										align : 'center',

									},{
										field : 'sub_name',
										title : '所属一级菜单',
										width : 250,
										height : 150,
										align : 'center'},
									{
										field : 'menukey',
										title : '菜单key',
										width : 400,
										height : 150,
										align : 'center'
									},
									{
										field : 'url',
										title : 'url',
										width : 180,
										height : 150,
										align : 'center'
									},

									{
										field : 'tname',
										title : '操作',
										width : 140,
										height : 150,
										align : 'center',
										formatter : function(value, row, index) {

											var btn = '<a href="javascript:void(0)" id="editcls" onclick="showMessage('
													+ index + ')" >编辑</a>';
											return btn;
										}

									} ] ]

						});
	}

	function showMessage(index) {
		$('#MenuTable').datagrid('selectRow', index);

		var row = $('#MenuTable').datagrid('getSelected');

		$('#addbtn').css('display', 'none')
		$('#btn').css('display', 'block')
		var menugrade = $("#sel").val()
		if (menugrade == "二级菜单") {
			$('#seldiv').css('display', 'block')
		} else if (menugrade == "一级菜单") {
			$('#seldiv').css('display', 'none')
		}
		$("#dialog").show();//必须先显示，再弹出    
		$('#bid').val(row.bid)
		$('#sbid').val(row.sbid)
		$('#name').val(row.name)
		$('#menutype').val(row.menutype)
		$('#menukey').val(row.menukey)
		$('#url').val(row.url)
		$("#dialog").dialog({
			title : "修改菜单信息",
			width : 600,
			height : 400
		});

	}
	function clearwindow() {
		$('#sbid').val('')
		$('#name').val('')
		$('#menutype').val('')
		$('#menukey').val('')
		$('#url').val('')

	}

	function updatemenu() {
		var menugrade = $("#sel").val()
		if (menugrade == "二级菜单") {			
			var url="doupdateTwomenu.action"
		} else if (menugrade == "一级菜单") {	
			var url="doupdateOnemenu.action"
		}
		$.ajax({
			type : "post",
			url : url,
			data : $("#menuform").serialize(),
			dataType : "JSON",
			success : function(data) {
				if (data.code == 1) {
					alert('修改成功')
					flush()
					clearwindow();
					$('#dialog').window('close');
				} else {
					alert('修改失败' + data.msg)

				}
			}

		})

	}

	function onchangeMenu(obj) {

		var menugrade = obj;//一级菜单||二级菜单changeMenu.action
		flush()
		
	}

	function  flush(){
		var menugrade = $("#sel").val()
		if (menugrade == "二级菜单") {			
			showmenu('findAllMenu.action', 'sbid', 'sbid', 'sbid')
			$('#MenuTable').datagrid('showColumn','sub_name');
		} else if (menugrade == "一级菜单") {		
			showmenu('findOneMenu.action', 'bid', 'bid', 'bid')
			$('#MenuTable').datagrid('hideColumn','sub_name');
		}
	}
	function AddMenuWindow() {
		clearwindow();//清除残余的缓存信息
		var menugrade = $("#sel").val()
		if (menugrade == "二级菜单") {
			$('#seldiv').css('display', 'block')
		} else if (menugrade == "一级菜单") {
			$('#seldiv').css('display', 'none')
		}
		document.getElementById('btn').style.display = 'none'
		document.getElementById('addbtn').style.display = 'block'

		$("#dialog").show();
		$("#dialog").dialog({
			title : "添加菜单信息",
			width : 600,
			height : 400
		});
	}

	function doAddMenu() {
		var menugrade = $("#sel").val()
		$('#menugrade').val(menugrade)
		var data = $("#menuform").serialize()
		$.ajax({
			url : "doAddMenu.action",
			data : data,
			type : "post",
			dataType : "json",
			success : function(data) {
				if (data.code == 1) {
					alert('添加成功')
					flush();
				} else {
					alert('添加失败' + data.msg)

				}

			}

		})
	}
</script>
</head>

<div id="dialog" style="display: none;">
	<form method="post" id="menuform">
	<input type="hidden" id="bid" name="bid" /> <!-- 修改一级菜单时用 -->
		<input type="hidden" id="sbid" name="sbid" /> <input type="hidden" id="menugrade" name="menugrade" /> 
		菜单名称： <input type="text" style="width: 200px;"
			id="name" name="name" /><br /><br /><br />
		<div id="seldiv">
			所属一级菜单：<select id="onegradeselect" name="onegradeselect">
				<c:forEach items="${OneGradeMenu}" var="menulist">
					<option name="selected" value="${menulist.bid }">${menulist.name }</option>
				</c:forEach>
			</select>
		</div>
		菜单类型： <input type="text" style="width: 200px;" id="menutype" name="menutype" /><br /><br /><br /> 
		菜单Key： <input type="text" style="width: 200px;" id="menukey" name="menukey" /><br /><br /><br /> 
		url：<br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<textarea rows="3" cols="40" name="url" id="url" style="resize: none;"></textarea><br /><br />
		<center>
			<input type="button" id="btn" value="确认修改" onclick="updatemenu()"> 
			<input type="button" id="addbtn" value="确认添加" onclick="doAddMenu()">
		</center>
	</form>
</div>

<table id="MenuTable" toolbar="#tb"></table>
<div id="tb">
	请选择菜单类型：&nbsp;&nbsp; <select id="sel" style="width: 150px;" onchange="onchangeMenu(this.value)">
		<option value="一级菜单" id="button">一级菜单</option>
		<option value="二级菜单" id="sub_button">二级菜单</option>
		
	</select> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="AddMenuWindow()">添加菜单</a>

</div>

</html>