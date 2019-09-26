<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
</head>
<body>
		<c:forEach items="${list }" var="p">
			<table>
				<tr>
					<td>${p.name}</td>
					<td>${p.age}</td>
					<td>${p.address}</td>
				</tr>
			</table>
		</c:forEach>
</body>
</html>