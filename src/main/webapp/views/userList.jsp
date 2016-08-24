<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<header>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</header>
<body>
	<div>
	<span>用户列表：</span>
	<table border="1" width="98%">
			<tr>
				<th align="center">序号</th>
				<th align="center">用户ID</th>
				<th align="center">用户名</th>
				<th align="center">密码</th>
				<th align="center">邮箱</th>
				<th align="center">手机</th>
			</tr>
		<c:forEach items="${userList}" var="user" varStatus="vs">
			<tr>
				<!-- <td align="center"><s:property value="#vs.index+1" /></td> -->
				<td align="center">${vs.index+1}</td>
				<td align="center">${user.id}</td>
				<td align="left">${user.userName}</td>
				<td align="center">${user.password}</td>
				<td align="left">${user.email}</td>
				<td align="center">${user.mobile}</td>
			</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>
