package com.mybatis.controler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("login")
public class LoginController { 
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	@ResponseBody
	public String loginIndex(HttpServletRequest request,HttpServletResponse response) {
		
		return "login";
	}
}
