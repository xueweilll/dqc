<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head >
<base href="<%=basePath%>">
<%@include file="../header.jsp"%>
<title>江苏华电戚墅堰发电有限公司</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/basic.js"></script>
<script type="text/javascript">
function test(){
	$.post("unitReport/saveTest.html",{time:"2015-10-16"},function(data){
		console.info(data);
	});
}
function test1(){
	$.post("unitReport/saveTest.html",{time:"2015-05-18"},function(data){
		console.info(data);
	});
}
function test11(){
	$.post("unitReport/monthByTime.html",{dateTime:"2016-04",type:0},function(data){
		console.info(data);
	});
}
function test2(){
	$.post("unitReport/dayBind.html",{dateTime:"2016-05-30"},function(data){
		console.info(data);
	});
}
/* function test1(){
	$.post("qxlb/qxclxxInfo.html",{qxbh:"C16010001"},function(data){
		console.info(data);
	});
}
function test2(){
	$.post("qxlb/qxlzxxInfo.html",{qxbh:"C16010001"},function(data){
		console.info(data);
	});
} */
function test3(){
	$.post("qxlb/qxlbInfo.html",{qxbh:"Q16010010"},function(data){
		console.info(data);
	});
}
function test4(){
	$.get("sysParmeter/sysBind.html",function(data){
		console.info(data);
	});
}
function test5(){
	$.post("unitParmeter/unitBind.html",function(data){
		console.info(data);
	});
}

function test6(){
	$.post("employee/employeeMobileList.html",{page:1,rows:20},function(data){
		console.info(data);
	},"json");
}

function test7(){
	$.post("content/coutentInfo.html",{id:"3284"},function(data){
		console.info(data.content);
		$("#content").html(data.content);
	},"json");
}
function test8(){
	$.post("oaContent/oaCoutentInfo.html",{id:"1VNCbOp98jwDvR3w7ekaJH3hwB9xTab7t0OC"},function(data){
		alert(data.content+"aaaa");
		$("#content").html(data.content);
	},"json");
}

function test9(){
	$.post("oaContent/save.html",function(data){
		console.info(data);
	});
}
function test10(){
	$.post("defect/defectTest.html",function(data){
		console.info(data);
	});
}
</script>
</head>
<body style="height: 100%">
	<input type="button"  value="测试" style="width: 100px;" onclick="test()" /><br/>
	<input type="button"  value="测试1" style="width: 100px;" onclick="test1()" /><br/>
	<input type="button"  value="测试2" style="width: 100px;" onclick="test2()" /><br/>
	<input type="button"  value="测试3" style="width: 100px;" onclick="test3()" /><br/>
	<input type="button"  value="测试4" style="width: 100px;" onclick="test4()" /><br/>
	<input type="button"  value="测试5" style="width: 100px;" onclick="test5()" /><br/>
	<input type="button"  value="值班表" style="width: 100px;" onclick="test6()" /><br/>
	<input type="button"  value="新闻" style="width: 100px;" onclick="test7()" /><br/>
	<input type="button"  value="新闻1" style="width: 100px;" onclick="test8()" /><br/>
	<input type="button"  value="机组电量" style="width: 100px;" onclick="test9()" /><br/>
	<input type="button"  value="消缺" style="width: 100px;" onclick="test10()" /><br/>
	<div id="content">
	<table width="298" height="19" style="border-collapse:collapse;width:223.50pt;">
		<tbody>
			<tr>
				<td width="298" height="19" class="et2" style="color:#000000;font-size:9.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:general;vertical-align:middle;">
					漏氢
				</td>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>
