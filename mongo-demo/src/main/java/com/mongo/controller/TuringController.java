package com.mongo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongo.turin.entity.Root;
import com.mongo.turin.entity.Values;
import com.mongo.turin.util.TurinApiUtil;

@Controller
@RequestMapping("/turin")
public class TuringController {
	
	
	@RequestMapping(value  = "/index" ,method = RequestMethod.GET)
	public String index() {
		return "turin";
	}
	
	@RequestMapping(value  = "/reply" ,method = RequestMethod.POST)
	@ResponseBody
	public Values autoReply(@RequestParam(value = "text") String text) {
		Root doPsotTurinApi = TurinApiUtil.doPsotTurinApi(text);
		Values values = doPsotTurinApi.getValues();
		return values;
	}
}
