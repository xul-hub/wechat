package com.mongo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/app")
public class LoginController {
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String loginInit() {
		return "login";
	}
}
