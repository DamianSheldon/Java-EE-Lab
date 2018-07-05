package com.tenneshop.order.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;

import com.tenneshop.order.entity.CustomerOrder;
import com.tenneshop.order.repository.CustomerOrderRepository;

@Controller
public class HomepageController {
	
	private static String homepageView = "homepage";
	
	@Autowired
	private CustomerOrderRepository customerOrderRepository;
	
	@RequestMapping("/")
	public String homepage(Model model) {
		
		model.addAttribute("orders", customerOrderRepository.findAll());
		
		return homepageView;
	}
	
	@ResponseBody
	@PostMapping("/create_order")
	public Map<String, String> createOrder(@RequestParam Map<String, String> formData, HttpServletResponse response) {
		formData.forEach((k, v) -> System.out.println("key: " + k + " value: " + v));
		
		Map<String, String> result = new HashMap<String, String>();
		
		String orderIdString = formData.get("orderId");
		String statusString = formData.get("status");
		String discountString = formData.get("discount");
		String shipmentInfo = formData.get("shipmentInfo");
		
		try {
			Integer orderId = Integer.parseInt(orderIdString);
			
			if (!statusString.isEmpty()) {
				char status = statusString.charAt(0);
				
				try {
					Integer discount = Integer.parseInt(discountString);
					
					CustomerOrder customerOrder = new CustomerOrder (orderId, status, discount, 
		            shipmentInfo);
				
					customerOrderRepository.save(customerOrder);
					
					result.put("statusCode", HttpStatus.OK.toString());
					result.put("message", HttpStatus.OK.getReasonPhrase());
					result.put("data", "successfully create order");
				}
				catch (NumberFormatException e) {
					response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
					
					result.put("statusCode", HttpStatus.EXPECTATION_FAILED.toString());
					result.put("message", "Discount is invalid.");
				}
				
			}
			else {
				response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
				
				result.put("statusCode", HttpStatus.EXPECTATION_FAILED.toString());
				result.put("message", "Status is unknow.");
			}
			
		} catch (NumberFormatException e) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
			
			result.put("statusCode", HttpStatus.EXPECTATION_FAILED.toString());
			result.put("message", "OrderID is invalid.");
		}

		return result;
	}
}
