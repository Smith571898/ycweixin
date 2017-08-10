<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<title>Insert title here</title>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script type="text/javascript">

	$(function() {
		$('#manNewsTable').edatagrid({
							url : 'findFollowPushMessage.action',
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
										field : 'fid',
										title : '文章编号',
										width : 100,
										height : 150,
										align : 'center',
										hidden : true
									},
									{
										field : 'ftitle',
										title : '文章标题',
										width : 250,
										height : 150,
										align : 'center'
									},
									{
										field : 'fpic',
										title : '图片',
										width : 300,
										height : 150,
										align : 'center',
										formatter : function(value, row, index) {
											if (value) {
												return "<img src='../images/"+value+"' style='width:300px;height:150px;'/>";/*这儿的value是一个图片的链接*/
											}
										}
									},
									{
										field : 'fcontent',
										title : '内容',
										width : 400,
										height : 150,
										align : 'center'
									},
									{
										field : 'lastmodify',
										title : '上次修改者',
										width : 180,
										height : 150,
										align : 'center'
									},
									{
										field : 'lastmodifytime',
										title : '上次修改时间',
										width : 180,
										height : 150,
										align : 'center'
									},
									{
										field : 'isfollowpush',
										title : '设为关注时推送',
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
										formatter : function() {

											var btn = '<a id="editcls" onclick="showMessage()" >编辑</a>';
											return btn;
										}

									} ] ],
							onLoadSuccess : function(data) {
								$('#editcls').linkbutton({
									text : '编辑',
									plain : true,
									iconCls : 'icon-edit'									
								});
							

							}
						});
	});

	function showMessage() {
		//document.getElementById("win").append('')
		var row = $('#manNewsTable').datagrid('getSelected');
		
	$('#win').append(' <iframe src="toeditor.action?ftitle='+row.ftitle+
			'&fcontent='+row.fcontent+'&fpic='+row.fpic+
			'&isfollowpush='+row.isfollowpush+'&fid='+row.fid+'" width="1020" height="630"></iframe> ');
		alert(row.ftitle)
		document.getElementById("win").style.display = "";
		
		$('#win').window({
			width : 1020,
			height : 630,
			modal : false,
			openAnimation:'slide',
			title : '关注推送编辑'

		});
		
		
   		
	}
</script>
</head>

	<div id="win" style="display: none">
	
	</div>
	<table id="manNewsTable"></table>


</html>