package com.tenneshop.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//import com.tenneshop.order.repository.OrderRepositoryImpl;

@Controller
public class HomepageController {
	
	private static String homepageView = "homepage";
	
//	@Autowired
//	private OrderRepositoryImpl orderRepository;
	
	@RequestMapping("/")
	public String homepage(Model model) {
		
//		model.addAttribute("orders", orderRepository.f);
		
		return homepageView;
	}
}
