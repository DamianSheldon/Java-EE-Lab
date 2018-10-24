package com.tenneshop.configuration;

import javax.annotation.Resource;
import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SiteSecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource(name="blSessionFixationProtectionFilter")
    protected Filter sessionFixationProtectionFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.sessionManagement()
				.sessionFixation()
				.none()
				.enableSessionUrlRewriting(false)
				.and()
			.formLogin()
				.and()
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.anyRequest().authenticated()
				.and()
			.requiresChannel()
				.anyRequest()
				.requiresSecure();
	}

	@Bean(name="blAuthenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManager();
    }
}
