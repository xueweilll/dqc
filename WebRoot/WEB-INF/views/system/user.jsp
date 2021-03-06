<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<%@include file="../header.jsp"%>
<title>My JSP 'user.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">

	$(function() {
		parent.addGrid($("#user_list").datagrid({
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'user/usersBind.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			toolbar : '#tb'
		}));

		var p = $("#user_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
	});
	function selectUser(){	
		$("#user_list").datagrid('load',{		
			name: $("#user").textbox("getText")
		});
		$("#user_list").datagrid("unselectAll");
	}

	function add() {		
		showDialogWH("添加用户信息", "user/userInfo.html?id=0",400,300);
	}
	
	

	function edit() {
		var row = $("#user_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		showDialogWH("编辑用户信息", "user/userInfo.html?id=" + row.id,400,300);
	}

	function distory() {
		var row = $("#user_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {
			if (data) {
				$.ajax({
					type : 'POST',
					url : "user/delete.html",
					data : {
						userid : row.id
					},
					success : function() {
						$("#user_list").datagrid("unselectAll");
						$("#user_list").datagrid("reload");
					}
				});
			}
		});
	}
	function reloadpwd() {
		var row = $("#user_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		$.messager.confirm("密码重置提示", "您确定要执行密码重置吗？", function(data) {
			if (data) {
				$.ajax({
					type : 'POST',
					url : "user/saveReloadpwd.html",
					data : {
						userid : row.id
					},
					success : function() {
						$.messager.alert("操作提示", "密码重置成功！<br/>新密码为：qdapp",
								"info");
					}
				});
			}
		});
	}
	
	
	var buttons = $.extend([], $.fn.combobox.defaults.buttons);
	buttons.splice(0,1,{
		text: '清空',
		handler: function(target){
			$(target).datetimebox("clear");
			$(target).datetimebox("hidePanel");
		}
	});
</script>

</head>

<body class="easyui-layout" id="cc">
	<table id="user_list" cellspacing="0" cellpadding="0">
		<thead>
			<tr>
			    <th data-options="field:'id',hidden:true"></th>
				<th data-options="field:'name',width:120">用户名称</th>
				<th data-options="field:'uname',widht:120">登录名称</th>
				<!-- <th data-options="field:'ename',width:120">员工名称</th> -->
				<th data-options="field:'dname',width:160">部门名称</th>
				<th data-options="field:'cratetime',width:160">创建时间</th>
				<th data-options="field:'edittime',width:160">修改时间</th>
				<th data-options="field:'authority',width:120">权限</th>
				<th data-options="field:'memo',width:160">描述</th>
			</tr>
		</thead>
	</table>
		<div id="tb" >
			<div class="cz_div_title">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="add()" iconCls="icon-add" plain="flase">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="edit()" iconCls="icon-edit" plain="flase">编辑</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="distory()" iconCls="icon-remove" plain="flase">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="reloadpwd()" iconCls="icon-reload" plain="flase">重置用户密码</a>
		</div>
		<div class="cz_div">
			用户名称:
			<input class="easyui-textbox" id="user" style="width:100px" name="user"/>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="selectUser()" iconCls="icon-search">查询</a>
		</div>
	
	</div>
</body>
</html>
