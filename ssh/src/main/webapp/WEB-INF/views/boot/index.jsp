<%@page import="com.hoyatod.config.Configure"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link rel="stylesheet" href="<%=Configure.crm_local_static %>resources/bootstrap-3.3.7/css/bootstrap.min.css">
</head>
<body>
	<h1>Hello World！</h1>
	<div class="alert alert-warning">
		<a href="#" class="close" data-dismiss="alert">
			&times;
		</a>
		<strong>警告！</strong>您的网络连接有问题。
	</div>
	<script type="text/javascript" src="<%=Configure.crm_local_static %>resources/bootstrap-3.3.7/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=Configure.crm_local_static %>resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>
</body>
</html>