<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "sql" uri = "http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户</title>
<script type="text/javascript">
	function validate(){
		if(document.myForm.employeeID.value == ""){
			alert( "请输入员工编号！" );
            document.myForm.employeeID.focus() ;
            return false;
		}
		if(document.myForm.realname.value == ""){
			alert( "请输入真实姓名！" );
            document.myForm.realname.focus() ;
            return false;
		}
		if(document.myForm.username.value == ""){
			alert( "请输入用户名！" );
            document.myForm.username.focus() ;
            return false;
		}
		if(document.myForm.password.value == ""){
			alert( "请输入密码！" );
            document.myForm.pass.focus() ;
            return false;
		}
		var reg = new RegExp("^[0-9]*$","");
		if(!reg.test(document.myForm.employeeID.value)){
			alert("员工编号中只应含有数字！");
			document.myForm.employeeID.focus() ;
			return false;
		}
		if(!reg.test(document.myForm.cellphone.value)){
			alert("电话中只应含有数字！");
			document.myForm.cellphone.focus() ;
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form action="addEmployeeAction" name="myForm" method="POST" onsubmit="return(validate());">
	<table align="center" cellpadding=4>
		<tr>
			<th colspan=2 style="align-content:center">用户信息新增</th>
		</tr>
		<tr>
			<td align="left">员工编号</td>
			<td><input type="text" name="employeeID" style="width:160px;height:20px"></td>
		</tr>
		<tr>
			<td align="left">真实姓名</td>
			<td><input type="text" name="realname" style="width:160px;height:20px"></td>
		</tr>
		<tr>
			<td align="left">用户名</td>
			<td><input type="text" name="username" style="width:160px;height:20px"></td>
		</tr>
		<tr>
			<td align="left">密码</td>
			<td><input type="text" name="pass" style="width:160px;height:20px"></td>
		</tr>
		<tr>
			<td align="left">所属部门</td>
			<td>
				<select name="department" style="width:163px;height:29px">
				<c:forEach var="row" items = "${departments}">
					<option value="${row}" style="font-size:17px">${row}</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td align="left">手机</td>
			<td><input type="text" name="cellphone" style="width:160px;height:20px"></td>
		</tr>
		<tr>
			<td align="left">地址</td>
			<td><textarea name="address" style="width:158px;height:40px"></textarea></td>
		</tr>
		<tr>
			<c:set var = "now" value = "<%= new java.util.Date()%>" />
			<td><input type="hidden" name="update_time" style="width:160px;height:20px" 
			value="<fmt:formatDate pattern = "yyyy-MM-dd HH:mm:ss" value = "${now}" />"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="保存" style="height:25px;width:60px"/>
			&nbsp;&nbsp;&nbsp;
			<input type="button" value="关闭" onclick= "javascript:window.close()" style="height:25px;width:60px"/></td>	
		</tr>
	</table>
	</form>
</body>
</html>