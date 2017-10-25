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
<title>手机设备信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">

	$(function() {
		$("#sysDevice_list").datagrid({
			width : 'auto',
			height : 'auto',
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'sysDevice/bind.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			rownumbers : true,
			toolbar : '#tb',
			pagination : true,
			pageSize : 20,
			columns:[[
			      {field:'userid',title:'用户',width:160},    
			      {field:'name',title:'设备名称',width:200},    
			      {field:'version',title:'版本',width:200},    
			      {field:'authority',title:'类型',width:100,formatter: function(value,row,index){
			    	  if(value==1){
			    		  return "禁止登陆";
			    	  }else{
			    		  return "允许登陆";
			    	  }
			      }}  
			]]
		});
		
		var p = $("#sysDevice_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
	});
	function distory(obj) {
		var row = $("#sysDevice_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		$.messager.confirm("操作提示", "您确定要执行该操作吗？", function(data) {
			if (data) {
				$.ajax({
					type : 'POST',
					url : "sysDevice/save.html",
					data : {
						id : row.id,
						authority:obj
					},
					success : function() {
						$("#sysDevice_list").datagrid("unselectAll");
						$("#sysDevice_list").datagrid("reload");
					}
				});
			}
		});
	}
	function distorys() {
		var row = $("#sysDevice_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		$.messager.confirm("操作提示", "您确定要执行该操作吗？", function(data) {
			if (data) {
				$.ajax({
					type : 'POST',
					url : "sysDevice/delete.html",
					data : {
						id : row.id
					},
					success : function() {
						$("#sysDevice_list").datagrid("unselectAll");
						$("#sysDevice_list").datagrid("reload");
					}
				});
			}
		});
	}
</script>

</head>

<body class="easyui-layout" id="cc">
	<table id="sysDevice_list" cellspacing="0" cellpadding="0"></table>
		<div id="tb" >
			<div class="cz_div_title">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="distory(1)" data-options="iconCls:'icon-remove'" plain="flase">禁用该登陆</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="distory(0)" data-options="iconCls:'icon-remove'" plain="flase">启用该设备</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="distorys()" data-options="iconCls:'icon-remove'" plain="flase">删除</a>
			</div>
			<!-- <div class="cz_div">
				参数名称:<input class="easyui-textbox" id="name" style="width:100px" name="name"/>
				参数关键字:<input class="easyui-textbox" id="pkey" style="width:100px" name="pkey"/>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="selectUnitParmeter()" iconCls="icon-search">查询</a>
			</div> -->
		</div>
</body>
</html>
