<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="head.jsp"%>
<script type="text/javascript" src="back/manager/ckeditor/ckeditor.js">

</script>

<script type="text/javascript">
if (typeof FileReader == 'undefined') {
    document.getElementById("showpicDiv").InnerHTML = "<h1>当前浏览器不支持FileReader接口</h1>";
    //使选择控件不可操作
    document.getElementById("xdaTanFileImg").setAttribute("disabled", "disabled");
}

//选择图片，马上预览
function xmTanUploadImg(obj) {
    var file = obj.files[0];
    
    console.log(obj);console.log(file);
    console.log("file.size = " + file.size);  //file.size 单位为byte

    var reader = new FileReader();

    //读取文件过程方法
    reader.onloadstart = function (e) {
        console.log("开始读取....");
    }
    reader.onprogress = function (e) {
        console.log("正在读取中....");
    }
    reader.onabort = function (e) {
        console.log("中断读取....");
    }
    reader.onerror = function (e) {
        console.log("读取异常....");
    }
    reader.onload = function (e) {
        console.log("成功读取....");

        var img = document.getElementById("showpic");
        img.src = e.target.result;
        //或者 img.src = this.result;  //e.target == this
    }

    reader.readAsDataURL(file)
}
 $(function (){
	//页面加载玩，查询到所有的新闻类别显示到select中
	showNewsType();
	
	 $("#addBtn").click(function(){
		//异步请求
		//var data=$("#addNewsForm").serialize();
		/* $.POST("back/newsAdd.action",{},function(data){
			alert(data);
			if(data.code==1){
				$.messager.alert('Warning','添加成功');					
			}else{
				$.messager.alert('Warning','添加失败'+data.errorMsg);	
			}
		},'json') */
		 $.ajax({
			type:"POST",
			url:"back/newsAdd.action",
			//data:$("#addNewsForm").serialize(),
			contentType: "application/json; charset=utf-8",
			success:function(data){
				alert(data);
				if(data.code==1){
					$.messager.alert('Warning','添加成功');					
				}else{
					$.messager.alert('Warning','添加失败'+data.errorMsg);	
				}
			},
			 error: function (XMLHttpRequest, textStatus, errorThrown) {
	                console.info(XMLHttpRequest.status);
	                console.info(XMLHttpRequest.readyState);
	                console.info(textStatus);
	                console.info(errorThrown);
	            }
		});		
	}); 
}); 


function showNewsType(){
	var url="back/newsType.action";
	$.ajax({
		type:"POST",
		url:url,
		data:"op=findAll&status=1",
		dataType:"json",
		success:function(data){
			str="";
			for(var i=0;i<data.rows.length;i++){
				var newstype=data.rows[i];
				str+="<option value='"+newstype.tid+"'>"+newstype.tname+"</option>";
			}
			$("#tid").html(str);
		}
		
		
	})
	
	
	
}

</script>
<title>添加新闻</title>
</head>
<body>
	<center>
		发布新闻
		<hr />
		<form id="addNewsForm" action="back/newsAdd.action" method="post"
			enctype="multipart/form-data">
			<div style="text-align: left;">
				<input type="hidden" name="op" value="add" /> 新闻类别:<select id="tid"
					name="tid">

				</select><br /> 新闻标题:<input type="text" name="title" id="title" /> <br />
				新闻权重:<input type="text" name="weight" id="weight" /><br />
				<p>
					图片上传前预览：<input type="file" name="pic" id="xdaTanFileImg"
						onchange="xmTanUploadImg(this)" accept="image/*" /> <input
						type="button" value="隐藏图片"
						onclick="document.getElementById('showpic').style.display = 'none';" />
					<input type="button" value="显示图片"
						onclick="document.getElementById('showpic').style.display = 'block';" />
				</p>
				<img id="showpic" src="" width="200px" height="100px"
					style="display: none" /><br />
				<div id="showpicDiv" name="pic"></div>
				新闻内容:
				<textarea class="ckeditor" name="content"></textarea>
				<br /> <input type="button" id="addBtn" value="添加" />
			</div>
		</form>
	</center>

</body>
</html>