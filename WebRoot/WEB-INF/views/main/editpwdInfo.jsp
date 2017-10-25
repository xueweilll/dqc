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
<title>版本弹窗</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<form id="ffemp" method="post" style="margin:10px; "  enctype="multipart/form-data">
		<table cellpadding="0" cellspacing="5" border="0">
		<tr>
			<td><label for="oldpwd">旧密码:</label></td> 
			<td><input id="oldpwd" class="easyui-textbox"
				name="oldpwd" style="width:220px"
				data-options="required:true,type:'password',
				validType:['length[5,12]','unnormal']"
				value="" /></td>
		</tr>
		<tr>
			<td><label for="newpwd">新密码:</label></td> 
			<td><input id="newpwd" class="easyui-textbox"
				name="newpwd" style="width:220px"
				data-options="required:true,type:'password',
				validType:['length[5,12]','unnormal']"
				value="" /></td>
		</tr>
		<tr>
			<td><label for="newpwd1">确认密码:</label></td> 
			<td><input id="newpwd1" class="easyui-textbox"
				name="newpwd1" style="width:220px"
				data-options="required:true,type:'password',
				validType:['length[5,12]','same[\'newpwd\']','unnormal']"
				value="" /></td>
		</tr>
		</table>
		<div >
			<div align="center" style="text-align: center;">
				<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-save'" plain="flase">保存</a>&nbsp;&nbsp;<a id="can"
					href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-cancel'" plain="flase">取消</a>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		$(function(){
			$("#sub").bind("click",function() {
				var isValid = $("#ffemp").form('validate');
				if (!isValid) {
					return false;
				}
				$.post("login/editpwd.html",{oldpwd:$("#oldpwd").val(),newpwd:$("#newpwd").val()},function(json){
					data = eval('(' + json + ')');
					if(data.result){
						$('#dialog').window('close');
						$.messager.alert("操作提示",data.msg,"info");
					}else{
						$.messager.alert("操作提示",data.msg,"info");
					}
				});
			});

			$("#can").bind("click", function() {
				$("#ffemp").form("clear");
			});
		});
	</script>
</body>
</html>
