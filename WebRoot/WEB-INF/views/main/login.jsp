<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="../header.jsp"%>
<!-- <link rel="shortcut icon" href="images/logo.ico" /> -->
<link rel="stylesheet" type="text/css" href="css/default.css" />
<link rel="stylesheet" type="text/css"
	href="js/easyui/themes/mythem/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css" />
<title>江苏华电戚墅堰发电有限公司后台管理系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
		$(function(){
			$("#login").click(function(){
				if($.trim($("#username").val())==""){
					$.messager.alert("操作提示", "请输入登录用户名！","info");
					return false;
				}
				if($.trim($("#password").val())==""){
					$.messager.alert("操作提示", "请输入登录密码","info");
					return false;
				}
				$.post("login/verify.html",
				{username:$("#username").val(),
				password:$("#password").val()},
				function(data){
				var data = eval('(' + data + ')');
					if(data.result){
						/* alert("main.html"); */
						window.open("main.html","_self");
					}else{
						$.messager.alert("操作提示", data.msg,"error");
					}
					
				});
			});
			$("#reset").click(function(){
				$("#username").val("");
				$("#password").val("");
			});
		});
	</script>

<body class="easyui-layout" style="overflow-y:auto;">
	<div data-options="region:'center',iconCls:'icon-home'" style="overflow-y:auto;background-color:#fff!important"
		>
		<div
			style=" height:89px; text-align:center; margin-top:30px; margin-bottom:30px;">
			<img src="images/login-logo.png" />
		</div>
		<div class="login_img">
		<center>
			<div class="dx_1000" style="height: 348px;">
				<div class="div_login" style="float: right;">
					<div class="zhxx">
						<div class="zhm">用户名:</div>
						<div class="div_input">
							<input value=""  id="username" type="text"
								class="ps_input" value=""
								placeholder="请输入用户名"
								name="username" maxlength="12" />
						</div>
					</div>

					<div class="zhxx" style=" margin-top:20px;">
						<div class="zhm">密 &nbsp;码:</div>
						<div class="div_input">
							<input value=""  id="password" type="password" value=""
								class="ps_input" placeholder="请输入密码"
								name="username" maxlength="12" />
						</div>
					</div>


					<div class="zhxx" style=" margin-top:20px; ">
						<a id="login" href="javascript:;" class="register">登录</a> <a
							id="reset" href="javascript:;" class="register">重置</a>
					</div>
				</div>


			</div>
				</center>
		</div>
		<div align="center">
			<img alt="" src="images/downloda.png" style="margin-top:20px;" height="294" width="471">
	
		</div>
				<center>
		<div class="main_div"
			style="height:50px; line-height:50px; text-align: center; margin-top:30px; color:#000">
			 Copyright © 2016 All Rights Reserved.  江苏华电戚墅堰发电有限公司 版权所有  江苏首创高科信息工程技术有限公司 技术支持
		</div>
				</center>
	</div>
</body>
</html>
