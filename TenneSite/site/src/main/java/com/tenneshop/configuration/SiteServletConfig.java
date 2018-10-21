package com.tenneshop.configuration;

import org.broadleafcommerce.cms.web.PageHandlerMapping;
import org.broadleafcommerce.common.web.controller.annotation.EnableAllFrameworkControllers;
import org.broadleafcommerce.core.web.catalog.CategoryHandlerMapping;
import org.broadleafcommerce.core.web.catalog.ProductHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableAllFrameworkControllers
public class SiteServletConfig extends WebMvcConfigurerAdapter {

    @Bean
    public HandlerMapping productHandlerMapping() {
        ProductHandlerMapping mapping = new ProductHandlerMapping();
        mapping.setOrder(3);
        return mapping;
    }
    
    @Bean
    public HandlerMapping pageHandlerMapping() {
        PageHandlerMapping mapping = new PageHandlerMapping();
        mapping.setOrder(4);
        return mapping;
    }
    
    @Bean
    public HandlerMapping categoryHandlerMapping() {
        CategoryHandlerMapping mapping = new CategoryHandlerMapping();
        mapping.setOrder(5);
        return mapping;
    }
}
