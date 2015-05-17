package com.connect.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String indexPage() {
		return "index";
	}
	
	@RequestMapping("/index")
	public String indexPage2() {
		return "index";
	}
}
