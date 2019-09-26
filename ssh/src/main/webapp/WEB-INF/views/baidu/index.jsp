<%@page import="com.hoyatod.config.Configure"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<title>baidu 定位</title>
<style type="text/css">  
	html{height:100%}  
	body{height:100%;margin:0px;padding:0px}  
	#container{height:100%}  
</style>
</head>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=您的密钥"></script>
<!-- v3.0版本的引用方式<script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=您的密钥"></script> -->
<body>

	<div id="container"></div> 
	
	
	<script type="text/javascript"> 
		//浏览器定位		
		/* var geolocation = new BMap.Geolocation();
		geolocation.getCurrentPosition(function(r){
			if(this.getStatus() == BMAP_STATUS_SUCCESS){
				alert('您的位置：'+r.point.lng+','+r.point.lat);
			}
			else {
				alert('failed'+this.getStatus());
			}        
		}); */
		
		//兼容写法
		/* if(navigator.geolocation) {
		    navigator.geolocation.getCurrentPosition(successCallback,errorCallback);
		    } else {
		    alert("对不起，您的浏览器不支持地理定位！");
		}
		
		function successCallback (position) {
		    var longitudeValue = position.coords.longitude;//获得当前位置的经度
		    var latitudeValue = position.coords.latitude;//获得当前位置的纬度
		    console.log("经度："+longitudeValue+"---纬度："+latitudeValue);
		}
		
		function errorCallback (error) {
		    console.log(error);
		    console.log("获取用户位置失败！")
		} */
	</script>  
	
	<script type="text/javascript"> 
		//IP定位		
		/* var map = new BMap.Map("container");
		var point = new BMap.Point(116.331398,39.897445);
		map.centerAndZoom(point,12);
		var marker = new BMap.Marker(point);       
		map.addOverlay(marker);                    
		map.enableScrollWheelZoom(true);
		
		function myFun(result){
			var cityName = result.name;
			alert("当前定位城市:"+cityName);
			var center = result.center;
			 map.setCenter(cityName); 
			map.panTo(center);
			alert('您的位置：'+center.lng+','+center.lat);
			
		}
		var myCity = new BMap.LocalCity();
		myCity.get(myFun);  */
	</script>
	
	<script type="text/javascript">
	var map = new BMap.Map("container");
	var point = new BMap.Point(116.331398,39.897445);
	map.centerAndZoom(point,12);
	
		var geolocation = new BMap.Geolocation();
		// 开启SDK辅助定位
		geolocation.enableSDKLocation();
		geolocation.getCurrentPosition(function(r){
			if(this.getStatus() == BMAP_STATUS_SUCCESS){
				var mk = new BMap.Marker(r.point);
				map.addOverlay(mk);
				map.panTo(r.point);
				alert('您的位置：'+r.point.lng+','+r.point.lat);
			}
			else {
				alert('failed'+this.getStatus());
			}        
		});
	</script>
	
	<script>
	(function(window,document,undefined){
			var hearts = [];
			window.requestAnimationFrame = (function(){
				return window.requestAnimationFrame ||
				window.webkitRequestAnimationFrame ||
				window.mozRequestAnimationFrame ||
				window.oRequestAnimationFrame ||
				window.msRequestAnimationFrame ||
				function (callback){
					setTimeout(callback,1000/60);
				}
			})();
			init();
			function init(){
				css(".heart{width: 10px;height: 10px;position: fixed;background: #f00;transform: rotate(45deg);-webkit-transform: rotate(45deg);-moz-transform: rotate(45deg);}.heart:after,.heart:before{content: '';width: inherit;height: inherit;background: inherit;border-radius: 50%;-webkit-border-radius: 50%;-moz-border-radius: 50%;position: absolute;}.heart:after{top: -5px;}.heart:before{left: -5px;}");
				attachEvent();
				gameloop();
			}
			function gameloop(){
				for(var i=0;i<hearts.length;i++){
					if(hearts[i].alpha <=0){
						document.body.removeChild(hearts[i].el);
						hearts.splice(i,1);
						continue;
					}
					hearts[i].y--;
					hearts[i].scale += 0.004;
					hearts[i].alpha -= 0.013;
					hearts[i].el.style.cssText = "left:"+hearts[i].x+"px;top:"+hearts[i].y+"px;opacity:"+hearts[i].alpha+";transform:scale("+hearts[i].scale+","+hearts[i].scale+") rotate(45deg);background:"+hearts[i].color;
				}
				requestAnimationFrame(gameloop);
			}
			function attachEvent(){
				var old = typeof window.οnclick==="function" && window.onclick;
				window.onclick = function(event){
					old && old();
					createHeart(event);
				}
			}
			function createHeart(event){
				var d = document.createElement("div");
				d.className = "heart";
				hearts.push({
					el : d,
					x : event.clientX - 5,
					y : event.clientY - 5,
					scale : 1,
					alpha : 1,
					color : randomColor()
				});
				document.body.appendChild(d);
			}
			function css(css){
				var style = document.createElement("style");
				style.type="text/css";
				try{
					style.appendChild(document.createTextNode(css));
				}catch(ex){
					style.styleSheet.cssText = css;
				}
				document.getElementsByTagName('head')[0].appendChild(style);
			}
			function randomColor(){
				return "rgb("+(~~(Math.random()*255))+","+(~~(Math.random()*255))+","+(~~(Math.random()*255))+")";
			}
		})(window,document);
	
	</script>
</body>
</html>