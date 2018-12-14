package com.tenneshop.controller.account;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.broadleafcommerce.common.exception.ServiceException;
import org.broadleafcommerce.core.web.controller.account.BroadleafLoginController;
import org.broadleafcommerce.core.web.controller.account.ResetPasswordForm;
import org.broadleafcommerce.profile.web.core.form.RegisterCustomerForm;
import org.broadleafcommerce.profile.web.core.service.register.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    @GetMapping(value="/login/forgotPassword")
    public String forgotPassword(HttpServletRequest request, HttpServletResponse response, Model model) {
        return super.forgotPassword(request, response, model);
    }
    
    @PostMapping(value="/login/forgotPassword")
    public String processForgotPassword(@RequestParam("emailAddress") String emailAddress, HttpServletRequest request, Model model) {
        return super.processForgotPassword(emailAddress, request, model);
    }   

    @GetMapping(value="/login/resetPassword")
    public String resetPassword(HttpServletRequest request, HttpServletResponse response, Model model) {
        return super.resetPassword(request, response, model);
    }   
    
    @PostMapping(value="/login/resetPassword")
    public String processResetPassword(@ModelAttribute("resetPasswordForm") ResetPasswordForm resetPasswordForm, HttpServletRequest request, HttpServletResponse response, Model model, BindingResult errors) throws ServiceException {
        return super.processResetPassword(resetPasswordForm, request, response, model, errors);
    }   
    
    @Override
    public String getResetPasswordUrl(HttpServletRequest request) {     
        String url = request.getScheme() + "://" + request.getServerName() + getResetPasswordPort(request, request.getScheme());
        
        if (request.getContextPath() != null && ! "".equals(request.getContextPath())) {
            url = url + request.getContextPath() + "/login/resetPassword";
        } else {
            url = url + "/login/resetPassword";
        }
        return url;
    }
}
