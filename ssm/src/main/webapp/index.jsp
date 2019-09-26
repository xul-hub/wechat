<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>首页启动</title>
</head>
<body>
	<div style="font-size: 60px;">启动 Success<a href="http://www.baidu.com">点击</a></div>
	<script type="text/javascript">
		/* window.addEventListener('pageshow', function(event) {//event.persisted属性为true时，表示当前文档是从往返缓存中获取
		    if (event.persisted) {
		        //location.reload();
		        window.location.href= "http://www.baidu.com";
		    }
		}); */
		
		var isPageHide = false;
		 window.addEventListener('pageshow', function(){
		  		if (isPageHide){
		   			window.location.href= "http://www.baidu.com";
		  		}
		 });
		 window.addEventListener('pagehide', function(event){
			 if (event.persisted) {
			        isPageHide = true;
			  }
		   
		 });
	</script>
</body>
</html>