<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		$('#manNewsTable').edatagrid({
			url : 'findAllUserInfo.action',//查询时加载的URL
			
			pageSize : 3,//默认分页的条数
			pageList : [3,5,10,15,20,25,30,35,40,45,50,100,200,500],//可选分页条数
			
			title : '用户管理',
			idField : 'uid',//标识  会记录我们选中的一行的ID  不一定是id  通常都是逐渐
			rownumbers : true,//不换行显示
			nowrap : true,
			sortName : 'subscribe_time',//排序的列 这个参数会传到后台的servlet上  所以要有后台对应的接收
			sortOrder : 'desc',//排序方式 降序
			singleSelect : true,
			
			onError : function(a,b){
				$messager.alert('错误','操作失败');
			},
			
			columns : [[{
				field : 'headimgurl',
				title : '微信头像',
				width : 150,
				height:50,
				align : 'center',
				formatter:function(value,row,index){
					if(value){
					return "<img src='../head/"+value+"' style='width:100px;height:100px;'/>";/*这儿的value是一个图片的链接*/
					}
				}
				
			},{
				field : 'nickname',
				title : '微信名',
				width : 150,
				height:50,
				align : 'center'
			},{
				field : 'sex',
				title : '性别',
				width : 150,
				height:50,
				align : 'center'
			},{
				field : 'address',
				title : '地址信息',
				width : 150,
				height:50,
				align : 'center'
			},{
				field : 'subscribe_time',
				title : '上次关注时间',
				width : 150,
				height:50,
				align : 'center',
				formatter:function(subscribe_time){
					
					 var tt=new Date(parseInt(subscribe_time) * 1000).toLocaleString().substr(0,17)
						// new Date(subscribe_time).toLocaleString(); 
					   
					    return  tt; 
					}
			},{
				field : 'subscribe',
				title : '当前是否关注',
				width : 150,
				height:50,
				align : 'center'
			},{
				field : 'name',
				title : '真实姓名',
				width : 150,
				height:50,
				align : 'center'
			},{
				field : 'telephone',
				title : '联系方式',
				width : 150,
				height:50,
				align : 'center'
			},{
				field : 'schoolname',
				title : '所在学校',
				width : 150,
				height:50,
				align : 'center'
			},{
				field : 'nowclass',
				title : '所在班级',
				width : 150,
				height:50,
				align : 'center'
			},{
				field : 'ycclass',
				title : '源辰班级',
				width : 150,
				height:50,
				align : 'center'
			}]],
			pagination : true,//显示分页
		});
	});
</script>
</head>
<body>
	<table id="manNewsTable"></table>
</body>
</html>