package com.mongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mongo.dao.impl.PersonMongoDaoImpl;
import com.mongo.entity.Person;

@Controller
public class IndexController {
	
	@Autowired
	private PersonMongoDaoImpl personMongoDaoImpl;
	
	@RequestMapping(value  = "/list" ,method = RequestMethod.GET)
	public String indexTestModel(Model model) {
		List<Person> findAll = personMongoDaoImpl.findAll();
		model.addAttribute("list", findAll);
		return "login";
	}
}
