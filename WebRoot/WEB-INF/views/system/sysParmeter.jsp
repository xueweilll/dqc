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
<title>系统参数设置</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">
	$(function() {
		$("#sysParmeter_list").datagrid({
			width : 'auto',
			height : 'auto',
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'sysParmeter/bind.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			rownumbers : true,
			toolbar : '#tb',
			method : 'get',
			columns : [ [ {
				field : 'name',
				title : '名称',
				width : 200
			}, {
				field : 'pkey',
				title : '参数关键字',
				width : 200
			}, {
				field : 'rating',
				title : '额定功率',
				width : 200
			}, {
				field : 'memo',
				title : '描述',
				width : 300
			} ] ]
		});
	});

	function edit() {
		var row = $("#sysParmeter_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		showDialogWH("编辑系统参数信息", "sysParmeter/sysParmeterInfo.html?id="
				+ row.id, 400, 300);
	}
	function synchronizationdata(){
		$.post("sysParmeter/synchronizationdata.html",function(msg){
			data = eval('(' + msg + ')');
			if(data.result){$.messager.alert("操作提示","数据同步成功","info");}
			else{$.messager.alert("操作提示",data.msg,"error");}
		});
	}

	var buttons = $.extend([], $.fn.combobox.defaults.buttons);
	buttons.splice(0, 1, {
		text : '清空',
		handler : function(target) {
			$(target).datetimebox("clear");
			$(target).datetimebox("hidePanel");
		}
	});
</script>

</head>

<body class="easyui-layout" id="cc">
	<table id="sysParmeter_list" cellspacing="0" cellpadding="0"></table>
	<div id="tb">
		<div class="cz_div_title">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="edit()" data-options="iconCls:'icon-edit'" plain="flase">编辑</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="synchronizationdata()" data-options="iconCls:'icon-reload'" plain="flase">同步数据</a>
		</div>
	</div>
</body>
</html>
