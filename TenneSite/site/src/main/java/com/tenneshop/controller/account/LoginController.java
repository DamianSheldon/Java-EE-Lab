package com.tenneshop.controller.account;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.broadleafcommerce.core.web.controller.account.BroadleafLoginController;
import org.broadleafcommerce.profile.web.core.form.RegisterCustomerForm;
import org.broadleafcommerce.profile.web.core.service.register.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The controller responsible for all actions involving logging a customer in
 */
@Controller
public class LoginController extends BroadleafLoginController {
    
	@Resource(name = "blRegistrationService")
    RegistrationService registrationService;
	
	@GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        String loginView = super.login(request, response, model);

        RegisterCustomerForm registrationForm = buildRegistrationForm();
        model.addAttribute("registrationForm", registrationForm);

        return loginView;
    }
    
    protected RegisterCustomerForm buildRegistrationForm() {       
    	RegisterCustomerForm registrationForm = registrationService.initCustomerRegistrationForm();
        
        registrationService.addRedirectUrlToForm(registrationForm);

        return registrationForm;
    }
}
