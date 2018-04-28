<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<script type = "text/javascript"  src = "${pageContext.request.contextPath}/jquery/jquery-3.2.1.min.js"></script>
<script type = "text/javascript">
$(document).ready(function(){
	$("#login").click(function(){
		var params = {};
		params.username = $("#username").val();
		params.pass = $("#pass").val();
		$.ajax({
			type:"POST",
			url:"loginValidation",
			data:params,
			success:function(result){
				if(result == "success"){
					location.href="employee";
					//alert("success");
				}else{
					$("#errormessage").text(result);
				}
			}
		})
	})
})
</script>
</head>
<body>
	<div style="position:absolute;width:100%;top:30%">
		<form class="login_info">
			<table border="0" align="center" cellpadding="7">
				<tr>
		       	<th colspan=2 style="align-content:center;font-size:24px">用户管理 </th>
		       	</tr>
		       	<tr>
					<td align="right"> 用户名: </td>
					<td><input type="text" name="username" id="username" style="height:20px;width:140px;font-size:18px"/></td>
				</tr>
				<tr>
					<td align="right">密码: </td>
					<td><input type="password" name="pass" id="pass" style="height:20px;width:140px;font-size:18px"/> </td>          
				</tr>
				<tr>
					<td colspan="2" align="center">
					<input type="button" id="login" value="登录" style="height:25px;width:60px"/>
					&nbsp;&nbsp;&nbsp;
					<input type="reset" value="清除" style="height:25px;width:60px"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
    				    <p style="color:red" id="errormessage"><p>
				</tr>
			</table>
		</form>
    </div>
</body>
</html>