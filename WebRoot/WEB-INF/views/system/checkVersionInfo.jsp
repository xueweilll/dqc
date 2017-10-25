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
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<form id="ffemp" method="post" style="margin:10px; "  enctype="multipart/form-data">
		<table cellpadding="0" cellspacing="5" border="0">
		<tr>
			<td><label for="version">版本号:</label></td> 
			<td><input id="version" class="easyui-textbox"
				name="version" style="width:220px"
				data-options="required:true,
				validType:['length[1,20]']"
				value="${version}" /></td>
		</tr>
		<tr>
			<td><label for="soft">Android软件升级包:</label></td>
			 <td><input id="soft"
				class="easyui-filebox" name="soft" style="width:220px"
				data-options="required:true,buttonText:'选中文件'"/>
		    </td>
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
				$('#ffemp').form('submit', {    
				    url:'servlet/UploadHandleServlet',    
				    onSubmit: function(){ 
				    },    
				    success:function(data){
				    	$.messager.alert("操作提示","上传成功","info");
				    	$('#dialog').window('close');
				    	reflush();
				    }    
				});
			});

			$("#can").bind("click", function() {
				$("#ffemp").form("clear");
			});
		});

		function reflush() {
			document.getElementById('checkVersion.html').contentWindow.$('#checkVersion_list')
			.datagrid('reload');
			document.getElementById('checkVersion.html').contentWindow.$('#checkVersion_list')
			.datagrid('unselectAll');
		}
	</script>
</body>
</html>
