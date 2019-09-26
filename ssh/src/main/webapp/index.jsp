<%@page import="com.hoyatod.config.Configure"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, minimum-scale=1, maximum-scale=1" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
</head>
	<script type="text/javascript" src="https://hoya.oss-cn-shanghai.aliyuncs.com/xiaochengxu/yinhua/20190422/admin/jquery-easyui-1.5/jquery.min.js"></script>
	<script type="text/javascript" src="https://hoya.oss-cn-shanghai.aliyuncs.com/xiaochengxu/yinhua/20190422/admin/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<body>
<h2>Hello World!</h2>
		<a href="https://mp.weixin.qq.com/s/V4CrQUY62gR9Myny3QZg7A">点击这里</a>
		<input type="text" name="source" id= "source">
		<button type="button" value="submit" id ="btn">submit</button>
</body>

<script type="text/javascript">
$("#btn").click(function(){
	var source = $("#source").val();
	alert(source);
	$.ajax({
		url: '<%=Configure.crm_local_static%>emo/test',
		dataType : "json",
		type: 'POST',
		async:true,
	    data : {
	    		source:source
	    		},
			error: function() {
				alert("发生系统异常,请稍后再试!");
			},
			success: function(data) {
				alert(data);
			}
	});	 
})


</script>
</html>
