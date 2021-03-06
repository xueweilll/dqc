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
<title>系统参数设置弹窗</title>

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
	<input id="sid" type="hidden" value="${parameter.getId()}" />
	<form id="ffemp" method="post" style="margin:10px; ">
		<table cellpadding="0" cellspacing="5" border="0">
		<tr>
			<td><label for="name">名称:</label></td> 
			<td><input id="name" class="easyui-textbox"
				name="name" style="width:220px"
				data-options="required:true,
			validType:['length[1,20]']"
				value="${parameter.getName()}" /></td>
		</tr>
		<tr>
			<td><label for="pkey">参数关键字:</label></td>
			 <td><input id="pkey"
				class="easyui-textbox" name="pkey" style="width:220px"
				data-options="required:true,validType:['length[1,100]']"
				value="${parameter.getPkey()}" />
		    </td>
		</tr>
		<tr>
			<td><label for="rating">额定功率:</label></td> 
			<td><input id="rating" class="easyui-textbox"
				name="rating" data-options="required:true,validType:['length[1,20]']"
				value="${parameter.getRating()}" style="width:220px;" />
		    </td>
		</tr>
		<tr>
			<td><label for="memo">描述:</label></td> 
			<td><input id="memo" class="easyui-textbox"
				name="memo" data-options="multiline:true,validType:['length[1,100]']"
				value="${parameter.getMemo()}" style="width:220px;height:60px" />
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
			$("#sub").bind("click",
					function() {
						var isValid = $("#ffemp").form('validate');
						if (!isValid) {
							return false;
						}
						var data = {
							"id" : ($("#sid").val() == "" ? 0 : $(
									"#sid").val()),
							"name" : $("#name").textbox("getText"),
							"pkey":$("#pkey").textbox("getText"),
							"memo":$("#memo").textbox("getText"),
							"rating":$("#rating").textbox("getText"),
						};
						$.ajax({
							type : "POST",
							url : "sysParmeter/update.html",
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
			document.getElementById('sysParmeter.html').contentWindow.$('#sysParmeter_list')
			.datagrid('reload');
			document.getElementById('sysParmeter.html').contentWindow.$('#sysParmeter_list')
			.datagrid('unselectAll');
		}
		
		$.extend(
			$.fn.validatebox.defaults.rules.remote.message="用户名已存在,请重新输入！"
		);
	</script>
</body>
</html>
