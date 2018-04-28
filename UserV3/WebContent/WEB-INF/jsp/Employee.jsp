<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "sql" uri = "http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
 <script type = "text/javascript"  src = "${pageContext.request.contextPath}/jquery/jquery-3.2.1.min.js"></script>
<script>
function toadd(){
 var url = "${pageContext.request.contextPath}/addEmployee";
 window.open(url, "_blank", 'height=460, width=400');
}
function verify(){
	var form = document.forms['myForm'];
	var i,counter = 0;
	for (i=0; i<form.length; i++){
		var row=form[i];
		if(row.checked && row.type=='checkbox'){
			counter++;
		}
	}
	if(counter > 1){
		alert("最多只能选择一个用户!!")
		return false;
	}else if(counter == 0){
		alert("请选择一个用户!!")
		return false;
	}
	return true;
}
$(document).ready(function(){
	$("#modify").click(function(){
		if (verify()){
			var checked = $("input:checkbox:checked").attr("name");
			var url = "${pageContext.request.contextPath}/modifyEmployee?ID="+checked;
			window.open(url, "_blank", "height=460, width=400");
		}
	})
})
$(document).ready(function(){
	$("#delete").click(function(){
		var checked = [];
		$("input:checkbox:checked").each(function(){
			checked.push($(this).attr("name"));
		})
		$.ajax({
			type:"POST",
			url:"deleteEmployee",
			traditional:true,
			data:{"IDs":checked},
			success:function(){
				window.location.reload();
			}
		})
	})
})
</script>
</head>
<body>
	<!--navigator-->
	<div style="height:30px; width:100%;position:absolute;top:20px;left:40px">
		<a href="employee">用户管理</a> &nbsp;&nbsp;<a href="department">部门管理</a>
	</div>
	<!--main section-->
	<div style="position:absolute;top:100px;left:15%;width:85%">
	<form action = "employee" method = "GET">
		<font size="4">员工编号: </font><input type = "text" name = "employeeID" style="width:15%;height:23px;font-size:20px">
		&nbsp;&nbsp;&nbsp;
		<font size="4">真实姓名: </font><input type = "text" name = "realname" style="width:15%;height:23px;font-size:20px">
		&nbsp;&nbsp;&nbsp;
		<font size="4">所属部门: </font>
		<select name="department" style="width:10%;height:25px">
			<option value="default_d" selected style="font-size:17px">请选择</option>
			<c:forEach var="row" items = "${departments}">
				<option value="${row}" style="font-size:17px">${row}</option>
			</c:forEach>
		</select>
		&nbsp;&nbsp;&nbsp;
		<input type="submit" name="search" value="查询" style="height:27px;width:60px"/>
	</form>
 	</div>
 	<div style="position:absolute;width:100%;top:150px;">	
		<form name="myForm" method = "POST">
 		<div style="position:absolute;width:92%;left:4%;">
 		<hr style="width:100%"/><br/>
		 	<div style="position:relative;left:5%">
		 		<input type="button" name="add" value="新增" onclick="toadd()" style="height:27px;width:80px;font-size:16px;">
		 		&nbsp;&nbsp;
		 		<input type="button" name="modify" value="修改" id="modify" style="height:27px;width:80px;font-size:16px;">
		 		&nbsp;&nbsp;
		 		<input type="button" name="delete" value="删除" id="delete" style="height:28px;width:80px;font-size:16px;">
		 	</div>
 		<br /><hr style="width:100%"/>
 		</div>
 		<div style="position:absolute;width:100%;top:120px;">
			<table style="border-collapse:collapse" border="1" align="center" width="90%" cellpadding=2>
				<tr>
					<th>CB</th>
					<th>ID</th>
					<th>员工编号</th>
					<th>真实姓名</th>				
					<th>用户名</th>
					<th>密码</th>
					<th>所属部门</th>
					<th>手机</th>
					<th>地址</th>
					<th>最后更新日期</th>
				</tr>
         
				<c:forEach var = "row" items = "${table_info}">
					<tr>
						<td><input type="checkbox" name="${row.ID}" value="on"></td>
						<td><c:out value = "${row.ID}"/></td>
						<td><c:out value = "${row.employeeID}"/></td>
						<td><c:out value = "${row.realname}"/></td>
						<td><c:out value = "${row.username}"/></td>
						<td><c:out value = "${row.pass}"/></td>
						<td><c:out value = "${row.department}"/></td>
						<td><c:out value = "${row.cellphone}"/></td>
						<td><c:out value = "${row.address}"/></td>
						<td><c:out value = "${row.update_time}"/></td>
           			 </tr>
				</c:forEach>
			</table>
		</div>
		</form>
	</div>
</body>
</html>