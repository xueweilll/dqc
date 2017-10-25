<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>timedata</title>
    <%@include file="../header.jsp"%>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
   <body class="easyui-layout" id="cc">
   
    	<table id="list" cellspacing="0" cellpadding="0"></table>
        <div id="toolbar">   
	        <div id="tt" class="easyui-tabs" >
				<div title="系统实时历史数据">
				</div>
				<div title="机组实时历史数据">
				</div>
			</div>
        	<!-- <div  class="cz_div" id="tool3" >  
	        	参数名称:<input class="easyui-textbox" id="name" style="width:100px" name="name"/>
				参数关键字:<input class="easyui-textbox" id="pkey" style="width:100px" name="pkey"/>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="selectUnitParmeter()" iconCls="icon-search">查询</a>
        	</div> -->
        </div>
  </body>
  <script>
		$(function(){			
			$("#list").datagrid({
				width : 'auto',
				height : 'auto',
				nowrap : false,
				striped : true,
				border : true,
				collapsible : false,
				fit : true,
				url : 'timedata/systimedatebind.html',
				remoteSort : false,
				idField : 'id',
				singleSelect : true,
				rownumbers : true,
				toolbar : '#toolbar',
				method:'get',
				pagination : true,
				pageSize : 20,
				columns:[[
				      {field:'name',title:'名称',width:200},
				      {field:'pkey',title:'键',width:200},
				      {field:'memo',title:'描述',width:200},
				      {field:'rating',title:'额定功率',width:200},
				      {field:'cdate',title:'时间',width:200},
				      {field:'pvalue',title:'实时数据',width:200}
				]]
			});
			$('#tt').tabs({
				tabWidth:'160',
				border : false,
				onSelect : function(title, index) {
					if(index=="0"){
						$("#list").datagrid({
							url:'timedata/systimedatebind.html',
							method:'get',
							columns:[[
							      {field:'name',title:'名称',width:200},
							      {field:'rating',title:'额定功率',width:200},
							      {field:'cdate',title:'时间',width:160},
							      {field:'pvalue',title:'实时数据',width:200}    
							]]
						});
					}else{
						$("#list").datagrid({
							url:'timedata/unittimedatebind.html',
							method:'get',
							columns:[[
							      {field:'name',title:'名称',width:200},    
							      {field:'measurement',title:'计量单位',width:100},    
							      {field:'cdate',title:'时间',width:160},  
							      {field:'pvalue',title:'实时数据',width:100},
							]]
						});
					}
					$("#list").datagrid("unselectAll");			
				}
			});			
			
			/* var p = $("#list").datagrid('getPager');
			$(p).pagination({
				pageList : [ 5, 10, 15, 20 ],
				beforePageText : '第',
				afterPageText : '页        共{pages}页',
				displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
			}); */	
		});
		var buttons = $.extend([], $.fn.combobox.defaults.buttons);
		buttons.splice(0,1,{
			text: '清空',
			handler: function(target){
				$(target).datetimebox("clear");
				$(target).datetimebox("hidePanel");
			}
		});
	</script>
</html>
