package com.mybatis.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("echarts")
public class EchartsController {
	
	@RequestMapping(value = "/index", method = {RequestMethod.GET,RequestMethod.POST})
	public String index() {
		return "echarts"; 
	}
}
