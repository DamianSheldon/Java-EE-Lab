package com.tenneshop.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SiteSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//		.requiresChannel()
//			.anyRequest()
//			.requiresSecure()
//			.and()
//		.authorizeRequests()
//			.anyRequest()
//			.authenticated()
//			.and()
//		.formLogin()
//			.and()
//		.httpBasic();
//	}

	
}
