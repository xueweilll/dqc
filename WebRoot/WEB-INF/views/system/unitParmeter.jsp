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
<title>My JSP 'user.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">

	$(function() {
		$("#unitParmeter_list").datagrid({
			width : 'auto',
			height : 'auto',
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'unitParmeter/bind.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			rownumbers : true,
			toolbar : '#tb',
			pagination : true,
			pageSize : 20,
			onLoadSuccess:function(){
				$("#unitParmeter_list").datagrid("unselectAll");
			},
			columns:[[
			      {field:'name',title:'名称',width:200},    
			      {field:'pkey',title:'参数关键字',width:200},    
			      {field:'measurement',title:'显示单位',width:100},    
			      {field:'sysid',title:'所属机组',width:160},    
			      {field:'type',title:'类型',width:100,formatter: function(value,row,index){
			    	  if(value==1){
			    		  return "重要";
			    	  }else{
			    		  return "普通";
			    	  }
			      }},    
			      {field:'sort',title:'排序号',width:100},    
			      {field:'memo',title:'描述',width:300}    
			]]
		});
		
		var p = $("#unitParmeter_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
	});
	
	function add() {		
		showDialogWH("添加机组参数信息", "unitParmeter/unitParmeterInfo.html?id=0",400,300);
	}
	
	

	function edit() {
		var row = $("#unitParmeter_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		showDialogWH("编辑机组参数信息", "unitParmeter/unitParmeterInfo.html?id=" + row.id,400,300);
	}

	function distory() {
		var row = $("#unitParmeter_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {
			if (data) {
				$.ajax({
					type : 'POST',
					url : "unitParmeter/delete.html",
					data : {
						id : row.id
					},
					success : function() {
						$("#unitParmeter_list").datagrid("unselectAll");
						$("#unitParmeter_list").datagrid("reload");
					}
				});
			}
		});
	}
	function synchronizationdata(){
		$.post("unitParmeter/synchronizationdata.html",function(msg){
			data = eval('(' + msg + ')');
			if(data.result){$.messager.alert("操作提示","数据同步成功","info");}
			else{$.messager.alert("操作提示",data.msg,"error");}
		});
	}
	function selectUnitParmeter(){	
		$("#unitParmeter_list").datagrid('load',{		
			name: $("#name").textbox("getText"),
			pkey: $("#pkey").textbox("getText"),
			sysid:$("#sysid").combobox("getValue")
		});
		$("#unitParmeter_list").datagrid("unselectAll");
	}
	function downloads(){
		$("#dowload").submit();
	}
</script>

</head>

<body class="easyui-layout" id="cc">
	<form id="dowload" action="unitParmeter/download.html" method="post" action="" style="display: none;">
	</form>
	<table id="unitParmeter_list" cellspacing="0" cellpadding="0"></table>
		<div id="tb" >
			<div class="cz_div_title">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="add()" data-options="iconCls:'icon-add'" plain="flase">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="edit()" data-options="iconCls:'icon-edit'" plain="flase">编辑</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="distory()" data-options="iconCls:'icon-remove'" plain="flase">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="synchronizationdata()" data-options="iconCls:'icon-reload'" plain="flase">同步数据</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="downloads()" data-options="iconCls:'icon-reload'" plain="flase">下载Config文件</a>
			</div>
			<div class="cz_div">
				机组：<select id="sysid" class="easyui-combobox" name="sysid"  style="width:100px;">   
					    <option value="">全部</option>   
					    <option value="46664689-a550-4ee0-ab63-05587f0e538e">#1号机</option>   
					    <option value="e3476aa7-e0a2-4aa5-a13a-e7c9ea6ca747">#2号机</option>   
					    <option value="ea960703-821e-4ff1-b704-64787ddf80ec">#3号机</option>   
					    <option value="fcf32bb0-20b9-4d43-8fa0-bcbd7fc16875">#4号机</option>   
					    <option value="f28a17e7-82a2-48aa-8b5e-7cb04b24f9b4">#5/6号机组</option>   
					    <option value="07cc63f8-50f7-4489-8403-a2c69b63fa7a">#7/8号机组</option>   
					</select>
				参数名称:<input class="easyui-textbox" id="name" style="width:100px" name="name"/>
				参数关键字:<input class="easyui-textbox" id="pkey" style="width:100px" name="pkey"/>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="selectUnitParmeter()" iconCls="icon-search">查询</a>
			</div>
		</div>
</body>
</html>
