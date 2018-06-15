package com.tenneshop.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomepageController {
	
	private static String homepageView = "homepage";
	
	@RequestMapping("/")
	public String homepage() {
		
		return homepageView;
	}
}
