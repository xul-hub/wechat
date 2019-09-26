<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />  
<title>百度地圖</title>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=BBuXHPN5GtUVjwOHVUpOTMzbEDDkTGUo"></script> <!-- v2.0版本的引用方式：src="http://api.map.baidu.com/api?v=2.0&ak=您的密钥" -->
<style type="text/css"> 
html{height:100%} 
body{height:100%;margin:0px;padding:0px}
#container{height:100%} 
</style> 
</head>
<body>
<div id="container"></div> 
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("container");
    var point = new BMap.Point(118.778, 32.05);
    map.centerAndZoom(point,12);
    map.enableScrollWheelZoom();                            // 启用滚轮放大缩小
    map.addControl(new BMap.NavigationControl());           // 启用放大缩小 尺
    var geolocation = new BMap.Geolocation();
        
    geolocation.getCurrentPosition(function (r) {//自动定位
        console.log(r.point);
        if(this.getStatus() == BMAP_STATUS_SUCCESS){
            var mk = new BMap.Marker(r.point);
            map.addOverlay(mk);//标出所在地
            map.panTo(r.point);//地图中心移动
            mk.addEventListener("dragend", showInfo);
            mk.enableDragging();    //可拖拽
            var point = new BMap.Point(r.point.lng,r.point.lat);//用所定位的经纬度查找所在地省市街道等信息
            var gc = new BMap.Geocoder();
            gc.getLocation(point, function (rs) {
                console.log(rs);                                          
                var addComp = rs.addressComponents;
                console.log(rs.address);   //地址信息
                alert(rs.address);        //弹出所在地址
                var label = new BMap.Label(rs.address, { offset: new BMap.Size(20, -10) });
                map.removeOverlay(mk.getLabel());//删除之前的label

                mk.setLabel(label);
                
            });
            function showInfo(e) {
                var gc = new BMap.Geocoder();
                gc.getLocation(e.point, function (rs) {
                    var addComp = rs.addressComponents;
                    var address = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;//获取地址
                    document.getElementById("txtposition").value = rs.address;
                    //画图 ---》显示地址信息
                    var label = new BMap.Label(address, { offset: new BMap.Size(20, -10) });
                    map.removeOverlay(mk.getLabel());     //删除之前的label

                    mk.setLabel(label);

                });
            }
        }else {
            alert('failed'+this.getStatus());
        }
    }, { enableHighAccuracy: true })
   
    // 百度地图API功能
    function G(id) {
     
        return document.getElementById(id)
    }

  /*   var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
		{"input" : "suggestId"
		,"location" : map
		});

    ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
        var str = "";
        var _value = e.fromitem.value;
        var value = "";
        if (e.fromitem.index > -1) {
            value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        }    
        str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
		
        value = "";
        if (e.toitem.index > -1) {
            _value = e.toitem.value;
            value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        }    
        str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
        G("searchResultPanel").innerHTML = str;
    });

    var myValue;
    ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
        var _value = e.item.value;
        myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
		
        setPlace();
    });

    function setPlace() {
        map.clearOverlays();    //清除地图上所有覆盖物
        function myFun() {
            var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
            map.centerAndZoom(pp, 18);
            map.addOverlay(new BMap.Marker(pp));    //添加标注
        }
        var local = new BMap.LocalSearch(map, { //智能搜索
            onSearchComplete: myFun
        });
        local.search(myValue);
    } */
  
    </script>


</body>
</html>