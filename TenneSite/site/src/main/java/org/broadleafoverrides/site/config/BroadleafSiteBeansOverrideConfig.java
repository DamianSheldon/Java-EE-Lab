package org.broadleafoverrides.site.config;

import org.broadleafcommerce.common.email.service.info.EmailInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:broadleaf-site-beans-override-config.xml")
public class BroadleafSiteBeansOverrideConfig {

    public static EmailInfo generalEmailInfo() {
    	EmailInfo info = new EmailInfo();
        info.setFromAddress("support@mycompany.com");
        info.setSendAsyncPriority("2");
        info.setSendEmailReliableAsync("false");
        return info;
    }
    
    @Bean
    public EmailInfo blForgotPasswordEmailInfo() {
    	EmailInfo info = generalEmailInfo();
        info.setSubject("Reset password request");
        info.setEmailTemplate("resetPassword-email");
        return info;
    }
    
    @Bean
    public EmailInfo blRegistrationEmailInfo() {
    	EmailInfo info = generalEmailInfo();
        info.setSubject("You have successfully registered!");
        info.setEmailTemplate("register-email");
        return info;
    }
}
