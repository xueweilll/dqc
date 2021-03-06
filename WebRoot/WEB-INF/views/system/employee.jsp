<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<%@include file="../header.jsp"%>
<title>My JSP 'employee.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	$(function() {
		var data = {
			"id" : ($("#empId").val() == "" ? 0 : $("#empId").val()),
			"department" : {
				"id" : null
			},
			"name" : null
		};
		parent.addGrid($("#employee_list").datagrid({
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'employee/employeeListBind.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
		}));
		
		/* $("#orgId").combotree({
			url : 'department/departmentsBind.html'
		}); */
		$("#search").bind("click", function() {
			$("#employee_list").datagrid(
				"load", {
					"name" : $("#Name").textbox("getValue")
				}
			);
			$("#employee_list").datagrid("unselectAll");
			$.messager.progress("close");
		});
		var p = $("#employee_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
		$("#btnAdd").bind("click", function() {
			showDialogWH("添加人员信息", "employee/employeeInfo.html?id=0",400,300);
		});
		$("#btnEdit").bind("click", function() {
			var row = $("#employee_list").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
			showDialogWH("编辑人员信息", "employee/employeeInfo.html?id=" + row.id,400,300);
		});
		$("#btnDelete").bind("click", function() {
			var row = $("#employee_list").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
			$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {
				if (data) {
					$.ajax({
						type : 'POST',
						url : "employee/delete.html",
						data : {
							"id" : row.id
						},
						success : function() {
							$("#employee_list").datagrid("unselectAll");
							$("#employee_list").datagrid("reload");
						}
					});
				}
			});
			$("#employee_list").datagrid("unselectAll");
		});
	});
</script>
</head>

<body class="easyui-layout" id="cc">
	<table id="employee_list" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
		<thead>
			<tr>
				<!-- <th data-options="field:'dname',width:120">部门名称</th> -->
				<th data-options="field:'name',width:100">人员名称</th>
				<th data-options="field:'id',hidden:true"></th>
				<!-- <th data-options="field:'moblie',width:120">手机</th> -->
				<th data-options="field:'cornet',width:50">短号</th>
				<!-- <th data-options="field:'phs',width:120">小灵通</th> -->
				<th data-options="field:'cratetime',width:130">添加时间</th>
				<th data-options="field:'edittime',width:130">修改时间</th>
			</tr>
		</thead>
	</table>
	<div id="tb" 
		>
	 <div class="cz_div_title" >	
		<a id="btnAdd" class="easyui-linkbutton"
			data-options="plain:false,iconCls:'icon-add'">新增</a> <a id="btnEdit"
			class="easyui-linkbutton"
			data-options="plain:false,iconCls:'icon-edit'">修改</a> <a
			id="btnDelete" class="easyui-linkbutton"
			data-options="plain:false,iconCls:'icon-remove'">删除</a>
	 </div>	
	 <div class="cz_div" >			
			<form id="department" method="post">
			<%-- 部门名称:<input id="orgId" class="easyui-combobox" style="width: 220px;display: none;"
				value="${employee.getDepartment().getId()}" editable="false"/> --%> &nbsp;&nbsp; 人员名称:<input
				id="Name" class="easyui-textbox" style="width: 110px">
			&nbsp;&nbsp; <a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-search" id="search">查询</a>
		</form>
 
    </div>
    
	

		</div>

</body>
</html>
