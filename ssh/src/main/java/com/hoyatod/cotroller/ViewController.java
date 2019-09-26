package com.hoyatod.cotroller;

import java.util.Iterator;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ViewController {
	
	@RequestMapping(value = "/validated", method = RequestMethod.GET)
    public String index(Model model,@Validated String test_name ,BindingResult r_name){
		List<ObjectError> allErrors = r_name.getAllErrors();
		for (Iterator<ObjectError> iterator = allErrors.iterator(); iterator.hasNext();) {
			ObjectError objectError = (ObjectError) iterator.next();
			System.out.println("objectError:" + objectError);
		}
        return "success";
    }
}
