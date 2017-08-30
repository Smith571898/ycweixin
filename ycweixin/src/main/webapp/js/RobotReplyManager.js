	function showoneknowledge(url,field,col1,title) {
		$('#ReplyTable').edatagrid(
				{
					url :'dofindknowledge.action',
					pagination : true,
					pageSize : 20,
					pageList : [ 3, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 100,
							200, 500 ],

					title : '菜单管理',
					idField : 'id',
					rownumbers : true,
					nowrap : false,
					sortName : 'create_time',
					sortOrder : 'desc',
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
						field : 'question',
						title : '用户信息',
						width : 250,
						height : 150,
						align : 'center'
					}, {
						field : 'answer',
						title : '小辰回复',
						width : 250,
						height : 150,
						align : 'center'
					},
					{
						field : 'category',
						title : '消息类别',
						width : 250,
						height : 150,
						align : 'center',
						formatter : function(category) {
								if(category==0){
									var cate='未知'
								}else if(category==1){
									var cate='普通对话'
								}else if(category==2){
									var cate='笑话'
								}else if(category==3){
									var cate='上下文'
								}
						
							return cate;
						}
					},
					{
						field : '操作',//所属一级菜单
						title : '修改',
						width : 250,
						height : 150,
						align : 'center',
						formatter : function() {

							var btn = '<a href="javascript:void(0)" id="editcl" onclick="updateknow()" >编辑</a>';
							return btn;
						}
					}] ]

				});
	}
	
	function showjoke() {
		$('#ReplyTable').edatagrid(
				{
					url :'dofindAllJoke.action',
					pagination : true,
					pageSize : 20,
					pageList : [ 3, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 100,
							200, 500 ],

					title : '菜单管理',
					idField : 'id',
					rownumbers : true,
					nowrap : false,
					sortName : 'create_time',
					sortOrder : 'desc',
					singleSelect : true,

					onError : function(a, b) {
						$messager.alert('错误', '操作失败');
					},

					columns : [ [ {
						field : 'joke_id',
						title : '笑话编号',
						width : 150,
						height : 250,
						align : 'center',
						hidden : true
					}, {
						field : 'joke_content',
						title : '笑话内容',
						width : 250,
						height : 250,
						align : 'center'
					}
					,
					{
						field : '操作',//所属一级菜单
						title : '修改',
						width : 250,
						height : 150,
						align : 'center',
						formatter : function() {

							var btn = '<a href="javascript:void(0)" id="editcl" onclick="updatejoke()" >编辑</a>';
							return btn;
						}
					}] ]

				});
	}
	
	
	function showtwoknowledge() {
		$('#ReplyTable').edatagrid(
				{
					url :'dofindTwoknowledge.action',
					pagination : true,
					pageSize : 20,
					pageList : [ 3, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 100,
							200, 500 ],

					title : '菜单管理',
					idField : 'id',
					rownumbers : true,
					nowrap : false,
					sortName : 'create_time',
					sortOrder : 'desc',
					singleSelect : true,

					onError : function(a, b) {
						$messager.alert('错误', '操作失败');
					},

					columns : [ [ {
						field : 'id',
						title : '二级表答案编号',
						width : 150,
						height : 250,
						align : 'center',
						hidden : true
					}, {
						field : 'pid',
						title : '所属一级表编号',
						width : 250,
						height : 250,
						align : 'center',
						hidden : true
					}, {
						field : 'subquestion',
						title : '一级表问题',
						width : 250,
						height : 250,
						align : 'center'
					},{
						field : 'answer',
						title : '小辰回答',
						width : 250,
						height : 250,
						align : 'center'
					}
					,
					{
						field : '操作',//所属一级菜单
						title : '修改',
						width : 250,
						height : 150,
						align : 'center',
						formatter : function() {

							var btn = '<a href="javascript:void(0)" id="editcl" onclick="updatetwoknow()" >编辑</a>';
							return btn;
						}
					}] ]

				});
	}
	//弹出修改一级信息窗口
	function updateknow(){
		
		//var menugrade = $("#sel").val()
		var row = $('#ReplyTable').datagrid('getSelected');
		$('#addbtn').css('display', 'none')
		$('#btn').css('display', 'block')
	//	$("#dialog").show();
		$('#knowid').val(row.id)
		$('#question').val(row.question)
		$('#answer').val(row.answer)
		$("#dialog").dialog({
			title : "修改菜单信息",
			width : 400,
			height :300
		});
		
	}
	//修改一级回复信息
	function doupdateKnow(){
		$.ajax({
			url:'doupdateknowledge.action',
			data:$('#knowledgeform').serialize(),
			type:'post',
			dataType:'json',
			success:function(data){
				if(data.code==1){
					alert('修改成功')
					flush()
					clearwindow();
					$('#dialog').window('close');
				}else{
					alert('修改失败'+data.msg)
				}
			}
		})
	}
	//添加一级回复信息
	function doAddKnow(){
		$.ajax({
			url:'doAddknowledge.action',
			data:$('#knowledgeform').serialize(),
			type:'post',
			dataType:'json',
			success:function(data){
				if(data.code==1){
					alert('添加成功')
					flush()
					clearwindow();
					$('#dialog').window('close');
				}else{
					alert('添加失败'+data.msg)
				}
			}
		})
	}
	
	//弹出修改二级信息窗口
	function updatetwoknow(){
		var row = $('#ReplyTable').datagrid('getSelected');
		$('#id').val(row.id)
		$('#pid').val(row.pid)
		$('#subquestion').val(row.subquestion)
		$('#subanswer').val(row.answer)
		$('#twobtn').css('display', 'block')
		$('#twoaddbtn').css('display', 'none')
		$("#twoledgedialog").dialog({
			title : "修改二级表信息",
			width : 400,
			height :300
		});
	}
	//修改二级回复信息
	function doupdatetwoknow(){
		$.ajax({
			url:'doupdatetwoknowledge.action',
			data:$('#twoknowledge').serialize(),
			type:'post',
			dataType:'json',
			success:function(data){
				if(data.code==1){
					alert('修改成功')
					flush()
					clearwindow();
					$('#twoledgedialog').window('close');
				}else{
					alert('修改失败'+data.msg)
				}
			}
		})
	}
	//添加二级回复信息
	function doAddtwoknow(){
		$.ajax({
			url:'doAddTwoknowledge.action',
			data:$('#twoknowledge').serialize(),
			type:'post',
			dataType:'json',
			success:function(data){
				if(data.code==1){
					alert('添加成功')
					flush()
					clearwindow();
					$('#twoledgedialog').window('close');
				}else{
					alert('添加失败'+data.msg)
				}
			}
		})
		
		
	}
	
	//弹出修改二级信息窗口
	function updatejoke(){
		var row = $('#ReplyTable').datagrid('getSelected');
		
		$('#joke_id').val(row.joke_id)
		$('#joke_content').val(row.joke_content)
	
		$('#jokebtn').css('display', 'block')
		$('#jokeaddbtn').css('display', 'none')
		$("#jokedialog").dialog({
			title : "修改笑话信息",
			width : 400,
			height :300
		});
	}
	
	
	
	
	function doupdatejoke(){
		
		$.ajax({
			url:'doupdatejoke.action',
			data:$('#jokefrom').serialize(),
			type:'post',
			dataType:'json',
			success:function(data){
				if(data.code==1){
					alert('修改成功')
					flush()
					clearwindow();
					$('#jokedialog').window('close');
				}else{
					alert('修改失败'+data.msg)
				}
			}
		})
	}
		

		function doAddjoke(){

			$.ajax({
				url:'doAddJoke.action',
				data:$('#jokefrom').serialize(),
				type:'post',
				dataType:'json',
				success:function(data){
					if(data.code==1){
						alert('添加成功')
						flush()
						clearwindow();
						$('#jokedialog').window('close');
					}else{
						alert('添加失败'+data.msg)
					}
				}
			})

		}
		function delknow(id){

			$.ajax({
				url:'dodelknow.action?id='+id,
				type:'post',
				dataType:'json',
				success:function(data){
					if(data.code==1){
						alert('删除成功')
						flush()
						clearwindow();
					}else{
						alert('删除失败'+data.msg)
					}
				}
			});
		}
		function deljoke(joke_id){

			$.ajax({
				url:'dodeljoke.action?joke_id='+joke_id,
				type:'post',
				dataType:'json',
				success:function(data){
					if(data.code==1){
						alert('删除成功')
						flush()
						clearwindow();
					}else{
						alert('删除失败'+data.msg)
					}
				}
			});
		}
		function deltwoknow(id){
			$.ajax({
				url:'dodeltwoknow.action?id='+id,
				type:'post',
				dataType:'json',
				success:function(data){
					if(data.code==1){
						alert('删除成功')
						flush()
						clearwindow();
					}else{
						alert('删除失败'+data.msg)
					}
				}
			});
		}
		




		
		function   clearwindow(){
			$('#id').val('')
			$('#question').val('')
			$('#answer').val('')
		}
		
		//切换类型重新查询的的内容
		function flush(){
			var category = $("#sel").val()
			if(category=="1"){
				showoneknowledge();
			}else if(category=="2"){
				showjoke();//重新加载笑话
			}else if(category=="3"){
				showtwoknowledge()
			
			}
			
		}
		
		
		

		function Add(){
			var category = $("#sel").val()
			if(category=="1"){
				$('#btn').css('display', 'none')
				$('#addbtn').css('display', 'block')
				clearwindow(); //清空窗口的值
			$("#dialog").dialog({
				title : "修改菜单信息",
				width : 400,
				height :300
			});
			}else if(category=="2"){
				$('#jokebtn').css('display', 'none')
				$('#jokeaddbtn').css('display', 'block')
			$('#joke_content').val('')
			$("#jokedialog").dialog({
				title : "修改笑话",
				width : 400,
				height :300
			});
			
			}else if(category=="3"){
				var row = $('#ReplyTable').datagrid('getSelected');
				$('#subquestion').val(row.subquestion)
				$('#subanswer').val('')
				$('#pid').val(row.pid)
				$('#twobtn').css('display', 'none')
				$('#twoaddbtn').css('display', 'block')
				clearwindow(); //清空窗口的值
				$("#twoledgedialog").dialog({
				title : "添加二级表信息",
				width : 400,
				height :300
			});
			}
		}
		
		//
			function Delete(){
			var category = $("#sel").val()
				var row = $('#ReplyTable').datagrid('getSelected');
			if(category=="1"){
			
					var content=row.question.substring(0,20)+'...'
				
				if (confirm('您确定要删除问题为'+content+'的这条记录吗')==true){
					delknow(row.id);
					}else{
					return false;
					}
			}else if(category=="2"){
				
					var content=row.joke_content.substring(0,20)+'...'
				
				if (confirm('您确定要删除内容为'+content+'的这条记录吗')==true){
					deljoke(row.joke_id);
					}else{
					return false;
					}
			
			}else if(category=="3"){
				
					var content=row.answer.substring(0,10)+'...'
				
				if (confirm('您确定要删除小辰回复为'+content+'的这条记录吗')==true){
					deltwoknow(row.id);
					}else{
					return false;
					}
			}
			
		}
