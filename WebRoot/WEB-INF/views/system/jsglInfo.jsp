<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>角色管理页面弹窗</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>
<body>
	<input id="actorid" type="hidden" value="${actorid}" />
	<form id="ffemp" method="post" style="margin:10px;">
		<div align="center">
			<label for="name">名称</label>
			<input id="name"
				name="name" class="easyui-textbox" style="width:320px" data-options="required:true"
				value="${actor.getName()}" />
		</div>
		<div align="center">
			<label for="remark">备注</label>
			<input id="remark"
				name="remark" class="easyui-textbox" style="width:320px" data-options="required:true,multiline:true,height:60"
				value="${actor.getRemark()}" />
		</div>
		<div>
			<div align="center" style="text-align: center;">
				<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp;<a id="can"
					href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-cancel'">取消</a>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		$(function(){
			$("#sub").bind("click",
					function() {
						var isValid = $("#ffemp").form('validate');
						if (!isValid) {
							$.messager.progress('close');
							return false;
						}
						var data = {
							"id" : ($("#actorid").val() == "" ? 0 : $(
									"#actorid").val()),
							"name" : $("#name").textbox("getValue"),
							"remark":$("#remark").textbox("getValue")
						};
						$.ajax({
							type : "POST",
							url : "jsgl/save.html",
							dataType : "text",
							data : {
								"jsonStr" : JSON.stringify(data)
							},
							success : function(msg) {
								data = eval('(' + msg + ')');
								if(data.result){reflush();}
								else{$.messager.alert("操作提示",data.msg,"error");}
								$('#dialog').window('close');
							}
						});
					});
			$("#can").bind("click", function() {
				$("#ffemp").form("clear");
			});
		});

		function reflush() {
			document.getElementById('jsgl.html').contentWindow.$('#actor_list')
			.datagrid('reload');
			document.getElementById('jsgl.html').contentWindow.$('#actor_list')
			.datagrid('unselectAll');
		}
	</script>
</body>
</html>
