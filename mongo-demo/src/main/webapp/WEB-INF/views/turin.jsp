<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<title>turining123</title>
	<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
		<p><input type="text" id = "say"></p>
		<button id = "btn">提交</button>
		<hr>
		<p id = "req"></p>
	
<script type="text/javascript">
	$(document).ready(function(){
		var voicename;
		$("#btn").click(function(){
			$.ajax({
		    	url: 'http://192.168.1.5:80/mongo-demo/turin/reply',
		    	data:{"text":$("#say").val()},
			    type: 'POST',
			    dataType:"json",
			    traditional:true,
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
			    },
			    success:function(data){
			    	$("#req").append(data.text + "<br>");
			    }
			});
		})
	});
	
	
</script>

</body>
</html>