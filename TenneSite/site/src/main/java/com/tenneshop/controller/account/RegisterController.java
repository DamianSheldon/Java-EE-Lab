package com.tenneshop.controller.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.broadleafcommerce.common.exception.ServiceException;
import org.broadleafcommerce.core.pricing.service.exception.PricingException;
import org.broadleafcommerce.core.web.controller.account.BroadleafRegisterController;
import org.broadleafcommerce.profile.web.core.form.RegisterCustomerForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/register")
public class RegisterController extends BroadleafRegisterController {

	@RequestMapping(method = RequestMethod.GET)
	public String register(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("registrationForm") RegisterCustomerForm registerCustomerForm) {
		return super.register(registerCustomerForm, request, response, model);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processRegister(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("registrationForm") RegisterCustomerForm registerCustomerForm, BindingResult errors)
			throws ServiceException, PricingException {
		String url = super.processRegister(registerCustomerForm, errors, request, response, model);
		return url;
	}

	@Override
	public String getRegisterView() {
		return "authentication/login";
	}
	
	@ModelAttribute("registrationForm")
	public RegisterCustomerForm initCustomerRegistrationForm() {
		return super.initCustomerRegistrationForm();

	}
}
