package com.hoyatod.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("boot")
@Controller
public class BootstrapController {
	private static final String prefix = "/boot/";
	
	@RequestMapping(value = "/index",method = RequestMethod.GET) 
	public String bootIndex() {
		return prefix + "index";
	}
}
