package com.mybatis.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mybatis.annotation.MyFirstAnnotation;
import com.mybatis.dao.TestMapper;
import com.mybatis.entity.Test;

import lombok.extern.log4j.Log4j;


@Controller
@RequestMapping("/test")
@Log4j
@MyFirstAnnotation(value = "Test is not get this!")
public class TestController {
	
	@Autowired
	private TestMapper testMapper;
	
	@RequestMapping(value = "/test/{id}")
	@ResponseBody
	public Test testMapper(@PathVariable("id") Long id) {
		log.info("Test");
		Test t = testMapper.selectTestById(id);
		return 	t;
	}
}
