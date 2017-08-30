<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<script type="text/javascript">
	$(function() {

		var treeData = [ {
			"id" : 1,
			"text" : "微信后台管理",
			"state" : "closed",
			"children" : [ {
				"id" : 11,
				"text" : "用户信息详情",
				"attributes" : {
					"url" : "<iframe style='width:100%;height:100%' src='WeChatUserInfo.action'/>"
				}
			} ]
		} ];

		var treeData_1 = [ {
			"id" : 1,
			"text" : "消息推送",
			"state" : "closed",
			"children" : [
					{
						"id" : 11,
						"text" : "关注推送",
						"attributes" : {
							"url" : "<iframe style='width:100%;height:80%' src='FollowPush.action' />"
						}
					},
					{
						"id" : 12,
						"text" : "每周一推",
						"attributes" : {
							"url" : "<iframe style='width:100%;height:80%' src='toAddNewsMateria.action' />"
						}
					} ]
		} ];

		var treeData_3 = [ {
			"id" : 1,
			"text" : "素材管理",
			"state" : "closed",
			"children" : [
					{
						"id" : 11,
						"text" : "上传临时素材",
						"attributes" : {
							"url" : "<iframe style='width:100%;height:80%' src='touploadTempMaterial.action' />"
						}
					},
					{
						"id" : 12,
						"text" : "上传永久素材",
						"attributes" : {
							"url" : "<iframe style='width:100%;height:80%' src='touploadForeverMaterial.action' />"
						}
					},
					{
						"id" : 13,
						"text" : "上传永久图文素材",
						"attributes" : {
							"url" : "<iframe style='width:100%;height:80%' src='toAddNewsMateria.action' />"
						}
					},
					{
						"id" : 14,
						"text" : "查看永久素材",
						"attributes" : {
							"url" : "<iframe style='width:100%;height:80%' src='toFindMaterial.action' />"
						}
					},
					{
						"id" : 15,
						"text" : "查看永久图文素材",
						"attributes" : {
							"url" : "<iframe style='width:100%;height:80%' src='toFindNews.action' />"
						}
					} ]
		} ];
		var treeData_4 = [ {
			"id" : 1,
			"text" : "菜单管理",
			"state" : "closed",
			"children" : [ {
				"id" : 11,
				"text" : "查看菜单",
				"attributes" : {
					"url" : "<iframe style='width:100%;height:80%' src='findmenu.action' />"
				}

			} ]
		} ];
		var treeData_5 = [ {
			"id" : 1,
			"text" : "机器人管理中心",
			"state" : "closed",
			"children" : [
					{
						"id" : 11,
						"text" : "聊天记录查询",
						"attributes" : {
							"url" : "<iframe style='width:100%;height:80%' src='toChatLog.action' />"
						}
					},
					{
						"id" : 12,
						"text" : "机器人问题答案设置",
						"attributes" : {
							"url" : "<iframe style='width:100%;height:80%' src='toRobotReplyManager.action' />"
						}

					} ]
		} ];

		var treeData_6 = [ {
			"id" : 1,
			"text" : "客服管理",
			"state" : "closed",
			"children" : [ {
				"id" : 11,
				"text" : "客服详情",
				"attributes" : {
					"url" : "<iframe style='width:100%;height:80%' src='toService.action' />"
				}

			} ]
		} ];
		//生成树
		$("#newsTypeTree").tree({
			data : treeData,
			onClick : function(node) {
				//alert(node.text);  // alert node text property when clicked
				if (node.attributes) {
					openTab(node);
				}
			}
		});
		$("#newsTree").tree({
			data : treeData_1,
			onClick : function(node) {
				//alert(node.text);  // alert node text property when clicked
				if (node.attributes) {
					openTab(node);
				}
			}
		});
		$("#mediaTree").tree({
			data : treeData_3,
			onClick : function(node) {
				//alert(node.text);  // alert node text property when clicked
				if (node.attributes) {
					openTab(node);
				}
			}
		});
		$("#menutree").tree({
			data : treeData_4,
			onClick : function(node) {
				//alert(node.text);  // alert node text property when clicked
				if (node.attributes) {
					openTab(node);
				}
			}
		});
		$("#RobotManager").tree({
			data : treeData_5,
			onClick : function(node) {
				//alert(node.text);  // alert node text property when clicked
				if (node.attributes) {
					openTab(node);
				}
			}
		});
		$("#ServicesManager").tree({
			data : treeData_6,
			onClick : function(node) {
				//alert(node.text);  // alert node text property when clicked
				if (node.attributes) {
					openTab(node);
				}
			}
		});

		function openTab(node) {
			if ($("#Maintt").tabs('exists', node.text)) {
				$("#Maintt").tabs('select', node.text);
			} else {
				$("#Maintt").tabs('add', {
					title : node.text,
					selected : true,
					closable : true,
					content : node.attributes.url
				});
			}
		}
	});
</script>
</head>
<body class="easyui-layout layout panel-scroll" data-options="fit:true">
	<div data-options="region:'north'" style="height: 50px">
		<div style="text-align: center;">
			<h1>欢&nbsp;&nbsp; 迎&nbsp;&nbsp;进&nbsp;&nbsp;入&nbsp;&nbsp;源&nbsp;&nbsp; 辰&nbsp;&nbsp;微&nbsp;&nbsp;信&nbsp;&nbsp;后&nbsp;&nbsp;
				台&nbsp;&nbsp;管&nbsp;&nbsp;理&nbsp;&nbsp;系&nbsp;&nbsp;统</h1>
		</div>
	</div>
	<div data-options="region:'south',split:true" style="height: 50px;">南边</div>
	<div data-options="region:'east',split:true" title="East" style="width: 100px;">右边</div>
	<div data-options="region:'west',split:true" title="West" style="width: 150px;">
		<div class="easyui-accordion" style="width: 140px; height: 600px;" data-options="selected:-1">
			<div title="用户管理" style="overflow: auto; padding: 10px;">
				<ul id="newsTypeTree"></ul>
			</div>
			<div title="消息推送" style="overflow: auto; padding: 10px;">
				<ul id="newsTree"></ul>
			</div>
			<div title="素材管理" style="overflow: auto; padding: 10px;">
				<ul id="mediaTree"></ul>
			</div>
			<div title="菜单管理" style="overflow: auto; padding: 10px;">
				<ul id="menutree"></ul>
			</div>
			<div title="机器人管理" style="overflow: auto; padding: 10px;">
				<ul id="RobotManager"></ul>
			</div>
			<div title="客服管理" style="overflow: auto; padding: 10px;">
				<ul id="ServicesManager"></ul>
			</div>
		</div>
	</div>
	<div data-options="region:'center',title:'操作',iconCls:'icon-ok',fit:true">
		<div id="Maintt" class="easyui-tabs" data-options="fit:true,border:false">
			<div title="待处理业务"></div>
		</div>
	</div>
</body>
</html>