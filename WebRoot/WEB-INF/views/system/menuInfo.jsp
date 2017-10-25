<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>菜单管理页面弹窗</title>

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
	<input id="menuid" type="hidden" value="${id}" />
	<form id="ffemp" method="post" style="margin:10px;">
		<div align="center">
			<label for="classes">添加类型</label>
			<select id="classes" class="easyui-combobox" name="classes" style="width:220px;" data-options="required:true,value:'${menu.getClasses()}',editable:false">   
			    <option value="0">生产页显示</option>   
			    <option value="1">管理页显示</option>      
			</select> 
		</div>
		<br>
		<div align="center">
			<label for="name">显示名称</label>
			<input id="name"
				name="name" class="easyui-textbox" style="width:220px" data-options="required:true,validType:'length[4,10]'"
				value="${menu.getName()}" />
		</div>
		<br>
		<div align="center">
			<label for="url">外链地址</label>
			<input id="url"
				name="url" class="easyui-textbox" style="width:220px" data-options="required:true,multiline:true,height:60,validType:'length[4,100]'"
				value="${menu.getUrl()}" />
		</div>
		<br>
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
							"id" : ($("#menuid").val() == "" ? 0 : $(
									"#menuid").val()),
							"name" : $("#name").textbox("getValue"),
							"url":$("#url").textbox("getValue"),
							"classes":$("#classes").combobox("getValue")
						};
						$.ajax({
							type : "POST",
							url : "menu/save.html",
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
			document.getElementById('menu.html').contentWindow.$('#menu_list')
			.datagrid('reload');
			document.getElementById('menu.html').contentWindow.$('#menu_list')
			.datagrid('unselectAll');
		}
	</script>
</body>
</html>
