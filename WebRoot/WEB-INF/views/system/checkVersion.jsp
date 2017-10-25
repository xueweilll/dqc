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
<title>版本控制</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">
	$(function() {
		$("#checkVersion_list").datagrid({
			width : 'auto',
			height : 'auto',
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'checkVersion/bind.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			rownumbers : true,
			toolbar : '#tb',
			method : 'post',
			columns : [ [ {
				field : 'version',
				title : '版本号',
				width : 200
			} ] ]
		});
	});

	function edit() {
		showDialogWH("上传APP版本", "checkVersion/checkVersionInfo.html", 400, 300);
	}
</script>

</head>

<body class="easyui-layout" id="cc">
	<table id="checkVersion_list" cellspacing="0" cellpadding="0"></table>
	<div id="tb">
		<div class="cz_div_title">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="edit()" data-options="iconCls:'icon-add'" plain="flase">上传新版本</a>
		</div>
	</div>
</body>
</html>
