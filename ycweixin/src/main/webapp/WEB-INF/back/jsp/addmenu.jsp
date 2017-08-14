<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<title>Insert title here</title>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script type="text/javascript">

	$(function() {
		$('#MenuTable').edatagrid({
							url : 'findFollowPushMessage.action',
							pagination : true,
							pageSize : 5,
							pageList : [ 5, 10, 15, 20, 25, 30, 35, 40, 45, 50,
									100, 200, 500 ],

							title : '菜单管理',
							idField : 'bid',
							rownumbers : true,
							nowrap : false,
							sortName : 'bid',
							sortOrder : 'desc',
							singleSelect : true,

							onError : function(a, b) {
								$messager.alert('错误', '操作失败');
							},

							columns : [ [
									{
										field : 'bid',
										title : '一级菜单编号',
										width : 100,
										height : 150,
										align : 'center',
										hidden : true
									},
									{
										field : 'name',
										title : '一级菜单名称',
										width : 250,
										height : 150,
										align : 'center'
									},
									{
										field : 'firstname',
										title : '子菜单1',
										width : 300,
										height : 150,
										align : 'center',
									
									},
									{
										field : 'secondname',
										title : '子菜单2',
										width : 400,
										height : 150,
										align : 'center'
									},
									{
										field : 'thirdname',
										title : '子菜单3',
										width : 180,
										height : 150,
										align : 'center'
									},
									{
										field : 'fourthname',
										title : '子菜单4',
										width : 180,
										height : 150,
										align : 'center'
									},
									{
										field : 'fifthname',
										title : '子菜单5',
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
										formatter : function(value,row,index) {

											var btn = '<a id="editcls" onclick="showMessage('+index+')" >编辑</a>';
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

	function showMessage(index) {
		$('#manNewsTable').datagrid('selectRow',index);
		
		var row = $('#manNewsTable').datagrid('getSelected');
		alert(row)
			$.ajax({
				type:"POST",
				url:"updatemenu.action",
				data:"?",
				dataType:"JSON",
				success:function(data){
					if(data.code==1){
						alert('修改成功')
					}else{
						alert('修改失败')
					}
				}
				
			})

		
   		
	}
</script>
</head>

	<div id="win" style="display: none">
	
	</div>
	<table id="MenuTable"></table>


</html>