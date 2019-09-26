package com.hoyatod.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/map")
public class BaiduMapController {
	
	private static final String prefix = "/baidu/";
	
	@RequestMapping(value = "/index",method =RequestMethod.GET)
	public String mapIndex() {
		return prefix + "index";
	}
	
	@RequestMapping(value = "/index1",method =RequestMethod.GET)
	public String mapIndex1() {
		return prefix + "index1";
	}
	
	@RequestMapping(value = "/list",method =RequestMethod.GET)
	public String mapIndexList() {
		return prefix + "index";
	}
}
