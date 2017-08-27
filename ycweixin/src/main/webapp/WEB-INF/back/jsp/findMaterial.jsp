<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<title>Insert title here</title>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	$(function() {
		showmessage();
	});

	function showmessage(){
		$('#MaterialTable')
		.edatagrid(
				{
					url : 'findMaterial.action',
					pagination : true,
					pageSize : 5,
					pageList : [ 5, 10, 15, 20, 25, 30, 35, 40, 45, 50,
							100, 200, 500 ],

					title : '新闻类别管理',
					idField : 'fid',
					rownumbers : true,
					nowrap : false,
					sortName : 'fid',
					sortOrder : 'desc',
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
								field : 'url',
								title : '图片',
								width : 300,
								height : 150,
								align : 'center',
								formatter : function(value, row, index) {
									if (value) {
										return "<img src='../source/"+value+"' style='width:300px;height:150px;'/>";/*这儿的value是一个图片的链接*/
									}
								}
							},
							{
								field : 'mpurl',
								title : '微信端文件地址',
								width : 400,
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
								field : 'tname',
								title : '操作',
								width : 140,
								height : 150,
								align : 'center',
								formatter : function() {

									var btn = '<a id="editcl" onclick="delMaterial()" >删除</a>';
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

	function delMaterial(){
		var row = $('#MaterialTable').datagrid('getSelected');
		$.ajax({
			url:'dodelMaterial.action?media_id='+row.media_id,
			type:'post',
			dataType:'json',
			success:function(data){
				if(data.code==1){
					alert('删除成功')
					showmessage();
					}else  if(data.code==0){
						alert('删除失败'+data.msg)
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