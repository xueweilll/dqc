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
<title>人员信息弹窗</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<input id="empId" type="hidden" value="${employee.getId()}" />
	<input id="sidid" type="hidden" value="${snameid}" />
	<form id="ffemp" method="post" style="margin:10px; ">
		<table cellpadding="0" cellspacing="5" border="0">
			<%-- <tr>
				<td><label for="orgId"
					style="display: inline-block; width: 80px">部门名称:</label></td>
				<td><input id="orgId" name="orgId" data-options="required:true"
					value="${employee.getDepartmentid()}" style="width:220px" /></td>
			</tr> --%>
			<tr>
				<td><label for="empName">人员名称:</label></td>
				<td><input id="name" class="easyui-textbox" type="text"
					name="empName"
					data-options="required:true,validType:['name','length[0,20]']"
					value="${employee.getName()}" style="width:220px" /></td>
			</tr>
			<%-- <tr>
				<td><label for="phs">小灵通:</label></td>
				<td><input id="phs" class="easyui-textbox" type="text"
					name="phs" data-options="validType:['integer','length[1,8]']"
					value="${employee.getPhs()}" style="width:220px" /></td>
			</tr> --%>

			<tr>
				<td><label for="cornet">短号:</label></td>
				<td><input id="cornet" class="easyui-textbox" type="text"
					name="cornet" data-options="validType:['integer','length[1,8]']"
					value="${employee.getCornet()}" style="width:220px" /></td>
			</tr>
			<%-- <tr>
				<td><label for="moblie">手机:</label></td>
				<td><input id="moblie" class="easyui-textbox" type="text"
					name="moblie" data-options="validType:['mobile','length[1,11]']"
					value="${employee.getMoblie()}" style="width:220px" /></td>
			</tr> --%>
			<tr>
				<td><label for="memo">描述:</label></td>
				<td><input id="memo" class="easyui-textbox" type="text"
					name="memo" data-options="multiline:true,validType:'length[1,100]'"
					value="${employee.getMemo()}" style="width:220px;height:60px" /></td>
			</tr>
		</table>
		<div style="text-align: center">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp; <a id="can"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</form>
	<script type="text/javascript">
		$(function() {
			var myDate = new Date();
			var y;
			/* $("#pl").panel({
				onClose:function(){
					href:'employee/employeeListBind.html'
				}
			}); */
			/* $("#orgId").combotree({
				url : 'department/departmentsBind.html',
				required : true
			}); */
			$("#sub").bind(
					"click",
					function() {

						/* if ($("#orgId").combotree('getValue') == 0) {
							$.messager.alert("操作提示", "部门树不能被选择！", "info");
							$.messager.progress('close');
							return false;
						}  */
						var isValid = $("#ffemp").form('validate');
						if (!isValid) {
							$.messager.progress('close');
							return false;
						}
						
						var data = {
							"id" : ($("#empId").val() == "" ? 0 : $("#empId")
									.val()),
							/* "departmentid" : 
								$("#orgId").textbox("getValue")
							, */
							"name" : $("#name").textbox("getText"),
							"cornet" : $("#cornet").textbox("getText"),
							/* "moblie" : $("#moblie").textbox("getText"), */
							/* "phs" : $("#phs").textbox("getText"), */
							"memo":$("#memo").textbox("getText")
							
						};
						$.ajax({
							type : "POST",
							url : "employee/save.html",
							dataType : "text",
							data : {
								"jsonStr" : JSON.stringify(data)
							},
							success : function() {
								reflush();
								$.messager.alert("操作提示", "保存成功");
								$('#dialog').window('close');
							}
						});
					});

			$("#can").bind("click", function() {
				$("#ffemp").form("clear");
			});
		});

		function reflush() {
			document.getElementById('employee.html').contentWindow.$('#employee_list')
			.datagrid('reload');
			document.getElementById('employee.html').contentWindow.$('#employee_list')
			.datagrid('unselectAll');
		}

		function myformatter(date) {
			var y = date.getFullYear();
			var m = date.getMonth() + 1;
			var d = date.getDate();
			return y + '-' + (m < 10 ? ('0' + m) : m) + '-'
					+ (d < 10 ? ('0' + d) : d);
		}

		function myparser(s) {
			if (!s)
				return new Date();
			var ss = (s.split('-'));
			var y = parseInt(ss[0], 10);
			var m = parseInt(ss[1], 10);
			var d = parseInt(ss[2], 10);
			if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
				return new Date(y, m - 1, d);
			} else {
				return new Date();
			}
		}
	</script>
</body>
</html>
