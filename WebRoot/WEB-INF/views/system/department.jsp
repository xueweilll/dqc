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
    <title>部门管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	var id = 0;
	var pareId = 0;
	var url = "";
	$(function() {
		$("#btn").hide();
		$("#tt").tree({
			/* url : 'organization/bind.html', */
			url : 'department/departmentsBind.html',
			method:'POST',
			onBeforeExpand:function(node){
				if(node.children==undefined){
					return false;
				}
			},
			onClick : function(node) {
				$("#status").textbox("setValue", "显示");
				$("#orgphone").textbox({disabled:true});
				$("#orgname").textbox({disabled:true});
				$("#remark").textbox({disabled:true});
				$("#cornet").textbox({disabled:true});
				$("#btn").hide();
				id = node.id;
				pareId = node.pareId;
				$("#ff").form('load', {
					name : node.text,
					telephone : node.obj.phone,
					remark : node.obj.memo,
					cornet: node.obj.cornet
				});
			}
		});

		$("#sub").bind('click', function() {
			submit();
		});

		$("#can").bind('click', function() {
			$("#orgphone").textbox("clear");
			$("#orgname").textbox("clear");
			$("#remark").textbox("clear");
			$("#cornet").textbox("clear");
		});
	});

	function treeBind() {
		$("#tt").tree('reload');
	}

	function submit() {
		if (isDepartment()) {
			return false;
		}
		var isValid = $("#ff").form('validate');
		if (!isValid) {
			return false;
		}
		var data = {
				"id":id,
				"phone" : $("#orgphone").textbox("getValue"),
				"cornet" : $("#cornet").textbox("getValue"),
				"memo" : $("#remark").textbox("getValue").replace(/\s+/g,""),
				"name" : $("#orgname").textbox("getValue").replace(/\s+/g,""),
				"parent":pareId
		};
		$.ajax({
			type:"POST",
			url:url,
			data:{
				"jsonStr" : JSON.stringify(data)
			},
			success:function(msg){
				data = eval('(' + msg + ')');
				if(data.result){
					id=0;
					treeBind();
					$.messager.alert('操作提示','操作成功！','info');
					$("#status").textbox("setValue", "显示");
					$("#orgphone").textbox({disabled:true});
					$("#orgname").textbox({disabled:true});
					$("#remark").textbox({disabled:true});
					$("#cornet").textbox({disabled:true});
					$("#btn").hide();
				}else{
					$.messager.alert('操作提示','操作失败！','info');
				}
			}
		});
	}

	function add() {
		var target=$("#tt").tree("getSelected");
		var parenttarget=$("#tt").tree("getParent",target.target);
		if(target.id!="0"){
			if(parenttarget.id!="0"){
				$.messager.alert('操作提示','无法添加第三级！','info');
				return false;
			}
		}
		$("#status").textbox("setValue", "添加");
		$("#orgphone").textbox({disabled:false});
		$("#orgname").textbox({disabled:false});
		$("#remark").textbox({disabled:false});
		$("#cornet").textbox({disabled:false});
		$("#btn").show();
		url = "department/save.html";
		pareId = id;
		$("#departmentid").val(0);
		$("#ff").form('load', {
			name : "",
			telephone : "",
			remark : ""
		});
	}

	function edit() {
		url = "department/save.html";
		if (isDepartment()) {
			return false;
		}
		$("#departmentid").val(id);
		$("#status").textbox("setValue", "编辑");
		$("#orgphone").textbox({disabled:false});
		$("#orgname").textbox({disabled:false});
		$("#remark").textbox({disabled:false});
		$("#cornet").textbox({disabled:false});
		$("#btn").show();
	}

	function isDepartment() {
		if (id == 0 && url != "department/save.html"||id == 0 && url != "department/delete.html") {
			$.messager.alert("操作提示", "部门根节点无法被增删改！", "error");
			return true;
		} else {
			return false;
		}
	}

	function distroy() {
		url = "department/delete.html";
		if (isDepartment()) {
			return false;
		}
		$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {
			if (data) {
				$.ajax({
					type : 'POST',
					url : url,
					data : {
						"id":id
					},
					success : function() {
						id=0;
						treeBind();
						$.messager.alert('操作提示','操作成功','info');
					}
				});
			}
		});
	}
</script>
  </head>
  
  <body class="easyui-layout" id="cc">
  <input type="hidden" id="departmentid">
	<div
		data-options="region:'west',title:'部门列表',split:true,iconCls:'icon icon-icon4',tools:'#tl'"
		style="padding: 5px; width: 250px">
		<ul id="tt"></ul>
	</div>
	<div id="tl">
		<a href="javascript:void(0)" class="icon-add" onclick="add()" ></a> <a
			href="javascript:void(0)" class="icon-edit" onclick="edit()" ></a>
		<a href="javascript:void(0)" class="icon-remove" onclick="distroy()" ></a>
	</div>
	<div
		data-options="region:'center',title:'部门编辑',iconCls:'icon icon-icon4'"
		style="padding: 25px; background: #eee">
		<form id="ff" method="post">
			<table cellpadding="0" cellspacing="5" border="0" align="center">
				<tr>
					<td><label for="status">显示操作:</label></td>
					<td><input class="easyui-textbox" disabled="disabled" type="text" name="status"
						id="status" /></td>
				</tr>
				<tr>
					<td><label for="orgname">部门名称:</label></td>
					<td><input class="easyui-textbox"  type="text" name="name"
						id="orgname" data-options="disabled:true,required:true,validType:['length[1,20]','unnormal']" /></td>
				</tr>
				<tr>
					<td><label for="orgphone">部门电话:</label></td>
					<td><input class="easyui-textbox"  id="orgphone" type="text"
						name="telephone" data-options="disabled:true,validType:['length[8,12]']" /></td>
				</tr>
				<tr>
					<td><label for="cornet">部门短号:</label></td>
					<td><input class="easyui-textbox"  id="cornet" type="text"
						name="cornet" data-options="disabled:true,validType:['length[4,4]']" /></td>
				</tr>
				<tr>
					<td><label for="orgremark">部门备注:</label></td>
					<td><input class="easyui-textbox" type="text" id="remark"
						name="remark" style="height: 60px"
						data-options="disabled:true,multiline:true,validType:'length[0,2000]'" /></td>
				</tr>
			</table>
		</form>
		<div id="btn" style="text-align: center">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp;<a id="can"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</div>
</body>
</html>
