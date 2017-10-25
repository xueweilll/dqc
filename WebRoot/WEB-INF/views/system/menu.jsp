<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<%@include file="../header.jsp"%>
<title>菜单管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
$(function() {
	$("#menu_list").datagrid({
		width : 'auto',
		height : 'auto',
		nowrap : false,
		striped : true,
		border : true,
		collapsible : false,
		fit : true,
		url : 'menu/bind.html',
		remoteSort : false,
		idField : 'id',
		singleSelect : true,
		rownumbers : true,
		toolbar : '#tb',
		pagination : true,
		pageSize : 20,
		onLoadSuccess:function(){
			$("#menu_list").datagrid("unselectAll");
		},
		columns:[[
		      {field:'name',title:'名称',width:160},
		      {field:'classes',title:'显示类型',width:160,formatter:function(value){
		    	  if(value==0){
		    		  return "生产页显示";
		    	  }else{
		    		  return "管理页显示";
		    	  }
		      }},
		      {field:'createtime',title:'创建时间',width:160},    
		      {field:'edittime',title:'修改时间',width:160} ,
		      {field:'url',title:'外链地址',width:400}
		]]
	});
	
	var p = $("#menu_list").datagrid('getPager');
	$(p).pagination({
		pageList : [ 5, 10, 15, 20 ],
		beforePageText : '第',
		afterPageText : '页        共{pages}页',
		displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
	});
	$("#starttime").datetimebox({
		onChange : function() {
			$('#endtime').datetimebox('enableValidation');
		}
	});
});

function add() {
	showDialogWH("添加页面明细", "menu/menuInfo.html?id=0",'400','300');
}



function edit() {
	var row = $("#menu_list").datagrid("getSelected");
	if (row == null) {
		$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
		return false;
	}
	showDialogWH("编辑页面明细", "menu/menuInfo.html?id=" + row.id,'400','300');
}

function distory() {
	var row = $("#menu_list").datagrid("getSelected");
	if (row == null) {
		$.messager.alert("操作提示", "请选择一条记录再进行操作！", "info");
		return false;
	}
	$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {
		if (data) {
			$.ajax({
				type : 'POST',
				url : "menu/delete.html",
				data : {
					id : row.id
				},
				success : function() {
					$("#menu_list").datagrid("unselectAll");
					$("#menu_list").datagrid("reload");
				}
			});
		}
	});
}
function selectbig(){	
	$("#menu_list").datagrid('load',{		
		starttime: $("#starttime").datetimebox("getValue"),
		endtime: $("#endtime").datetimebox("getValue"),
		"classes":$("#classes").combobox("getValue")==3 ? null:$("#classes").combobox("getValue")
	});
	$("#menu_list").datagrid("unselectAll");
}
var buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
buttons.splice(0,1,{
	text: '清空',
	handler: function(target){
		$(target).datetimebox("clear");
		$(target).datetimebox("hidePanel");
	}
});
</script>
</head>

<body class="easyui-layout" id="cc">
	<table id="menu_list" cellspacing="0" cellpadding="0"></table>
	<div id="tb">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="add()"
			data-options="iconCls:'icon-add'" plain="flase">新增</a> <a
			href="javascript:void(0)" class="easyui-linkbutton" onclick="edit()"
			data-options="iconCls:'icon-edit'" plain="flase">编辑</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="distory()" data-options="iconCls:'icon-remove'"
			plain="flase">删除</a>
			&nbsp;&nbsp;&nbsp;&nbsp;
			显示类型：
			<select id="classes" class="easyui-combobox" name="classes" style="width:100px;" data-options="required:true,editable:false">   
			    <option value="3">全部</option>
			    <option value="0">生产页显示</option>   
			    <option value="1">管理页显示</option>      
			</select> 
			创建时间： <input id="starttime" type="text"
			class="easyui-datetimebox"
			data-options="width:160,editable:false,buttons: buttons"> ~ <input
			id="endtime" type="text" class="easyui-datetimebox"
			data-options="width:160,editable:false,buttons: buttons,validType:['compareDate[starttime]']">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="selectbig()" iconCls="icon-search">查询</a>
	</div>
</body>

</html>