<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<%@include file="../header.jsp"%>
<title>江苏华电戚墅堰发电有限公司</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/basic.js"></script>
<style type="text/css">
body {
	font-family: 微软雅黑 !important;
}

.accordion-header,.accordion-body {
	border-left: 0px !important;
	border-right: 0px !important;
}

/* a:link {
	color: #fff;
	text-decoration: none;
	font-size: 12px;
} */

a:visited {
	color: #fff;
	text-decoration: none;
}

a:hover {
	color: #fff;
	text-decoration: none;
	cursor: pointer;
	font-weight: bold;
}

a:active {
	color: #CF0000;
}

.xx a {
	font-size: 12px;
	height:40px;
	line-height: 40px;
	background-color: #fff;
	display: block;
	padding-left: 0px;
	border-bottom: 0px solid #99BBE8;
	text-align: center;
	border: 1px solid #8DB2E3;
}

.xx a:hover {
	background-color: #092469 !important;
	border: 1px solid #092469;
}
</style>
<script type="text/javascript">
$(function () {
    $('a[title]').click(function () {
          var src = $(this).attr('title');
          var title = $(this).html();
          if ($('#tt').tabs('exists', title)) {
              $('#tt').tabs('select', title);
              	var currTab = $('#tt').tabs('getSelected');
	      		$('#tt').tabs('update', {
	      			tab : currTab,
	      			options : {
	      				content : '<iframe id='+src+' frameborder=0 style=width:100%;height:100% src=' + src + ' ></iframe>'
	      			}
	      		});
          } else {
               $('#tt').tabs('add', {
               title: title,
               content: '<iframe id='+src+' frameborder=0 style=width:100%;height:100% src=' + src + ' ></iframe>',
               closable: true
           	 });
       	  }
	});
    $('#tt').tabs('add', {
        title: "用户管理",
        content: '<iframe id="user.html" frameborder=0 style=width:100%;height:100% src="user.html" ></iframe>',
        closable: true
    });
});
function logout(){
	$.post("login/logout.html",function(json){
		var data = eval('(' + json + ')');
		if(data.result){
			window.location.href="login.html";
		}
	});
}
function edits(){
	showDialogWH("修改密码", "login/editpwdInfo.html", 400, 300);
}
</script>
</head>
<body style="height: 100%">
	<div id="cc" class="easyui-layout" fit="true"
		style="width: 100%; height: 100%">
		<div region="north" split="true" style="height:74px; background-color:#77cce4;overflow:hidden; background-image:url(./images/hbg.jpg);background-position: top right;background-repeat: no-repeat;">
		<img src="./images/logo.png" style="height:74px;float:left;"/>
		<div style="float: right;">
		<a id="edits" onclick="edits()"><img src="./images/edit.png" style="height:74px;line-height:74px;display:inline-block;float:left;margin-right:20px"/></a>
		<a id="logout" onclick="logout()"><img src="./images/66.png" style="height:74px;line-height:74px;display:inline-block;float:right;margin-right:20px"/></a>
		</div>
		</div>
		<div region="west" iconcls="icon-tip" split="true"
			style="width: 150px;">
			<div id="aa" class="easyui-accordion" fit="true"  
				style=" background-color:#36adcf; background-image:url(./images/end_bg.gif)">
				<div title="后台管理"  class="xx"  data-options="collapsible:false"
					style="overflow: auto; padding: 10px;height:100%;background-color:#77cce4;">
					<a title="user.html">用户管理</a> <br /> 
					<a title="sysDevice.html">用户设备管理</a> <br /> 
					<a title="checkVersion.html">APP版本升级</a> <br /> 
					<a title="employee.html">员工电话录入</a> <br />
					<a title="department.html">部门电话录入</a> <br /> 
					<a title="sysParmeter.html">系统实时参数设置</a><br/> 
					<a title="unitParmeter.html ">机组实时参数设置</a><br/>
					<a title="menu.html ">外部链接添加</a><br/>
				</div>
			</div>
		</div>
		<div region="center" iconcls="icon-save"
			style="padding: 5px; background: #fff;">
			<div id="tt" class="easyui-tabs" fit="true"></div>
		</div>
		<div style="height:34px;"
		data-options="region:'south',split:true,collapsible:false"
		class="bqxx">
		<span class="bqxx_span span_left02">当前用户：${loginUser.username}</span>
		<span class="bqxx_span span_left01">登录时间：<%=new java.util.Date()%></span></span>
		<span class="bqxx_span span_right">江苏华电戚墅堰发电有限公司信息化管理系统 v1.0</span>
	</div>
	</div>
	<div id="dialog"></div>
	<div id="window"></div>
</body>
</html>
