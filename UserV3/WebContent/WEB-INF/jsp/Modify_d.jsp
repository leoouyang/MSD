<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "sql" uri = "http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改部门信息</title>
<script type="text/javascript">
function validate(){
	if(document.myForm.department_name.value == ""){
		alert( "部门名称不能为空！" );
        document.myForm.department_name.focus() ;
        return false;
	}
	return true;
}
</script>
</head>
<body>
	<form action="modifyDepartmentAction" name="myForm" method="POST" onsubmit="return(validate());">
	<input type="hidden" name = "ID" value = "${department.ID}">
	<table align="center" cellpadding=4>
		<tr>
			<th colspan=2 style="align-content:center">部门信息修改</th>
		</tr>
		<tr>
			<td align="left">部门名称</td>
			<td><input type="text" name="department_name" style="width:160px;height:20px" value = "${department.department_name}"></td>
		</tr>
		<tr>
			<td align="left">状态</td>
			<td>
				<select name="cur_status" style="width:163px;height:29px">
					<c:choose>
						<c:when test="${department.cur_status == '启用'}">
							<option value="启用" style="font-size:17px" selected>启用</option>
							<option value="停止" style="font-size:17px">停止</option>
						</c:when>
						<c:otherwise>
							<option value="停止" style="font-size:17px" selected>停止</option>
							<option value="启用" style="font-size:17px">启用</option>
						</c:otherwise>
					</c:choose>
				</select>
			</td>
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