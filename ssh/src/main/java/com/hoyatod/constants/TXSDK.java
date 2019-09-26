package com.hoyatod.constants;

public class TXSDK {
	//计算距离
	public static final String CalDistance = "https://apis.map.qq.com/ws/distance/v1/?mode=driving&from=FROMPOSITION&to=TOPOSITION&key=LERBZ-ASTW3-EDK3H-347EH-634LT-COF6J";
	
	public static final String CalDistance1 = "http://apis.map.qq.com/ws/distance/v1/matrix?mode=driving&from=FROMPOSITION&to=TOPOSITION&key=LERBZ-ASTW3-EDK3H-347EH-634LT-COF6J";
	
	//路线规划组件
	public static final String RouteUrl = "https://apis.map.qq.com/tools/routeplan/eword=EWORD&epointx=EPOINTX&epointy=EPOINTY&spointx=SPOINTX&spointy=SPOINTY?referer=JsKey&key=LERBZ-ASTW3-EDK3H-347EH-634LT-COF6J";
	
	public static void main(String[] args) {
		System.out.println("spointy".toUpperCase());
	}
}
