package com.tenneshop.controller.catalog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.broadleafcommerce.core.web.controller.catalog.BroadleafCategoryController;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class works in combination with the CategoryHandlerMapping which finds a category based upon
 * the passed in URL.
 */
@Controller("blCategoryController")
public class CategoryController extends BroadleafCategoryController {

	@Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = super.handleRequest(request, response);
        if (isAjaxRequest(request)) {
            modelAndView.setViewName(modelAndView.getViewName() + " :: ajax");
        }

        return modelAndView;
    }
}
