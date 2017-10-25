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
<title>My JSP 'userInfo.jsp' starting page</title>

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
	<input id="sid" type="hidden" value="${id}" />
	<input id="sysunits" type="hidden"
		value="${parameter.getSysid()}" />
	<form id="ffemp" method="post" style="margin:10px; ">
		<table cellpadding="0" cellspacing="5" border="0">
		<tr>
			<td><label for="sysunit">所属机组:</label></td> 
			<td><select id="sysunit" class="easyui-combobox" name="sysunit"  style="width:220px;">   
					    <option value="46664689-a550-4ee0-ab63-05587f0e538e">#1号机</option>   
					    <option value="e3476aa7-e0a2-4aa5-a13a-e7c9ea6ca747">#2号机</option>   
					    <option value="ea960703-821e-4ff1-b704-64787ddf80ec">#3号机</option>   
					    <option value="fcf32bb0-20b9-4d43-8fa0-bcbd7fc16875">#4号机</option>   
					    <option value="f28a17e7-82a2-48aa-8b5e-7cb04b24f9b4">#5/6号机组</option>   
					    <option value="07cc63f8-50f7-4489-8403-a2c69b63fa7a">#7/8号机组</option>   
				</select></td>
		</tr>
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
			<td><label for="measurement">显示单位:</label></td>
			 <td><input id="measurement"
				class="easyui-textbox" name="measurement" style="width:220px"
				data-options="required:true,validType:['length[1,10]']"
				value="${parameter.getMeasurement()}" />
		    </td>
		</tr>
		<tr>
			<td><label for="type">排序类型:</label></td>
			 <td>
			 <select id="type" class="easyui-combobox" name="type" style="width:220px;"  data-options="editable:false,required:true,value:'${parameter.getType()}'">   
				  <option value="0">普通</option>   
				  <option value="1">重要</option>  
			</select>
		    </td>
		</tr>
		<tr>
			<td><label for="sort">排序号:</label></td>
			 <td><input id="sort"
				class="easyui-textbox" name="sort" style="width:220px"
				data-options="required:true,validType:['integer']"
				value="${parameter.getSort()}" />
		    </td>
		</tr>
		<tr>
			<td><label for="memo">描述:</label></td> 
			<td><input id="memo" class="easyui-textbox"
				name="memo" data-options="multiline:true,validType:['length[1,200]']"
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
			$('#sysunit').combobox({
			    editable:false,
			    required:true,
			    value:$("#sysunits").val()
			});
			$("#sub").bind("click",
					function() {
						console.info($("#sysunit").combobox("getValue"));
						var isValid = $("#ffemp").form('validate');
						if (!isValid) {
							return false;
						}
						var data = {
							"id" : ($("#sid").val() == "" ? 0 : $(
									"#sid").val()),
							"name" : $("#name").textbox("getText"),
							"pkey":$("#pkey").textbox("getText"),
							"measurement":$("#measurement").textbox("getText"),
							"memo":$("#memo").textbox("getText"),
							"type":$("#type").combobox("getValue"),
							"sort":$("#sort").textbox("getText"),
							"sysid":$("#sysunit").combobox("getValue")
						};
						$.ajax({
							type : "POST",
							url : "unitParmeter/save.html",
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
			document.getElementById('unitParmeter.html').contentWindow.$('#unitParmeter_list')
			.datagrid('reload');
			document.getElementById('unitParmeter.html').contentWindow.$('#unitParmeter_list')
			.datagrid('unselectAll');
		}
	</script>
</body>
</html>
