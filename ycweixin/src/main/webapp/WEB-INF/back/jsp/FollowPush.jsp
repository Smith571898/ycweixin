<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<title>Insert title here</title>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	$(function() {
		$('#manNewsTable')
				.edatagrid(
						{
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
										align : 'center',
											formatter:function(lastmodifytime){
												
												 var tt=new Date(parseInt(lastmodifytime)).toLocaleString().substr(0,19)
													// new Date(subscribe_time).toLocaleString(); 
												   
												    return  tt; 
												}
									},
									{
										field : 'isfollowpush',
										title : '是否推送',
										width : 60,
										align : 'center',
										/* //调用formater函数对列进行格式化，使其显示单选按钮（所有单选按钮name属性设为统一，这样就只能有一个处于选中状态）  
										formatter : function(value, row, index) {
											if(value=='是'){
												var status='true';
											
											}else{
												var status='false'
											}
											return '是<input name="isfollowpush"  type="radio" checked='+status+' /> ';
											
										},
										onClickRow : function(rowIndex, rowData) {
											//加载完毕后获取所有的checkbox遍历
											var radio = $("input[type='radio']")[rowIndex].disabled;
											//如果当前的单选框不可选，则不让其选中
											if (radio != true) {
												//让点击的行单选按钮选中
												$("input[type='radio']")[rowIndex].checked = true;
											
											} else {
												$("input[type='radio']")[rowIndex].checked = false;
											}
										} */
									},
									{
										field : 'tname',
										title : '操作',
										width : 140,
										height : 150,
										align : 'center',
										formatter : function() {

											var btn = '<a id="editcl" onclick="showMessage()" >编辑</a>';
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
	});

	 
	function showMessage() {
		//document.getElementById("win").append('')
		var row = $('#manNewsTable').datagrid('getSelected');
		document.getElementById('win').innerHTML = "";
     var fcontent=row.fcontent.replace(/\"/g,"'")
     alert(fcontent)
		$('#win')
				.append(
						' <iframe src="toeditor.action?ftitle='+ row.ftitle+ '&fcontent='+ fcontent+ '&fpic='+ row.fpic+ '&isfollowpush='+ row.isfollowpush+ '&fid='+ row.fid+ '" style="overflow:hidden;height:630px;width:1200px" ></iframe> ');
	
		document.getElementById("win").style.display = "";

		$('#win').window({
			width : 1080,
			height : 630,
			modal : false,
			openAnimation : 'slide',
			title : '关注推送编辑'

		});

	}

	function addMenuWindow() {
		$('#win')
				.append(
						' <iframe src="toAddFollowPush.action" style="overflow:hidden;height:630px;width:1200px" ></iframe> ');
		document.getElementById("win").style.display = "";

		$('#win').window({
			width : 1080,
			height : 630,
			modal : false,
			openAnimation : 'slide',
			title : '关注推送编辑'

		});
	}
</script>
</head>
<div id="win" style="display: none"></div>
<table id="manNewsTable" toolbar="#tb">
	<div id="tb">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="addMenuWindow()">增加</a>
	</div>
</table>
</html>