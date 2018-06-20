package com.tenneshop.order.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/").permitAll();
        
        /*
         * Reference:https://springframework.guru/using-the-h2-database-console-in-spring-boot-with-spring-security/
         * To enable access to the H2 database console under Spring Security you need to change three things:
         * 1. Allow all access to the url path /console/*.
		 * 2. Disable CRSF (Cross-Site Request Forgery). By default, Spring Security will protect against CRSF attacks.
		 * 3. Since the H2 database console runs inside a frame, you need to enable this in in Spring Security.
         * */
        httpSecurity.authorizeRequests().antMatchers("/h2-console/**").permitAll();
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
	}
}
