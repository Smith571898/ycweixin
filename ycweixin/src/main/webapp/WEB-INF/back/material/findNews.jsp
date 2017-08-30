<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<title>Insert title here</title>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	$(function() {
		showmessage();
	});

	function showmessage() {
		$('#MaterialTable')
				.edatagrid(
						{
							url : 'findNews.action',
							pagination : true,
							pageSize : 5,
							pageList : [ 5, 10, 15, 20, 25, 30, 35, 40, 45, 50,
									100, 200, 500 ],

							title : '永久图文素材',
							idField : 'media_id',
							rownumbers : true,
							nowrap : false,
							sortName : 'media_id',
							sortOrder : 'asc',
							singleSelect : true,

							onError : function(a, b) {
								$messager.alert('错误', '操作失败');
							},

							columns : [ [
									{
										field : 'media_id',
										title : '多媒体文件id',
										width : 100,
										height : 150,
										align : 'center',
										hidden : true
									},
									{
										field : 'type',
										title : '文件类型',
										width : 250,
										height : 150,
										align : 'center'
									},
									{
										field : 'create_at',
										title : '创建时间',
										width : 400,
										height : 150,
										align : 'center'
									},
									{
										field : 'tname1',
										title : '操作',
										width : 140,
										height : 150,
										align : 'center',
										formatter : function() {

											var btn = '<a id="editc1" onclick="updateMaterial()" >编辑</a>';
											return btn;
										}

									},
									{
										field : 'tname2',
										title : '操作',
										width : 140,
										height : 150,
										align : 'center',
										formatter : function() {
											var btn = '<a id="editc2" onclick="delMaterial()" >删除</a>';
											return btn;
										}

									} ] ],
							onLoadSuccess : function(data) {
								$('#editcl').linkbutton({
									text : '编辑',
									plain : true,
									iconCls : 'icon-edit'
								});

							}
						});
	}

	function delMaterial() {
		var row = $('#MaterialTable').datagrid('getSelected');
		$.ajax({
			url : 'dodelMaterial.action?media_id=' + row.media_id,
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if (data.code == 1) {
					alert('删除成功')
					showmessage();
				} else if (data.code == 0) {
					alert('删除失败' + data.msg)
				}
			}
		})
	}
	
	function updateMaterial() {
		var row = $('#MaterialTable').datagrid('getSelected');
		$.ajax({
			url : 'toUpdateNews.action?media_id=' + row.media_id,
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if (data.code == 1) {
					//TODO:
					window.location = "";
				} else if (data.code == 0) {
					alert("未知错误")
				}
			}
		})
	}
</script>
</head>
<div id="win" style="display: none"></div>
<table id="MaterialTable" toolbar="#tb">
	<div id="tb">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="addMenuWindow()">增加</a>
	</div>
</table>
</html>