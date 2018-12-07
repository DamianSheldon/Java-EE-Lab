package com.tenneshop.configuration;

import javax.annotation.Resource;
import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan({"org.broadleafcommerce.common.web.security","org.broadleafcommerce.profile.web.core.security",
	"org.broadleafcommerce.core.web.order.security"})
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SiteSecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource(name="blSessionFixationProtectionFilter")
    protected Filter sessionFixationProtectionFilter;
	
	@Value("${asset.server.url.prefix.internal}")
    protected String assetServerUrlPrefixInternal;
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
                .antMatchers("/css/**")
                .antMatchers("/fonts/**")
                .antMatchers("/img/**")
                .antMatchers("/js/**")
                .antMatchers("/**/"+assetServerUrlPrefixInternal+"/**")
                .antMatchers("/favicon.ico");
    }
	
	@Value("${server.port:9443}")
	private int httpsServerPort;
	
	@Value("${http.server.port:9090}") 
	int httpServerPort;
	
	// @formatter:off
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.formLogin()
				.and()
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.anyRequest().authenticated()
				.and()
			.requiresChannel()
				.anyRequest()
				.requiresSecure()
				.and()
			.portMapper().http(httpServerPort).mapsTo(httpsServerPort);
	}
	// @formatter:on
	
	@Bean(name="blAuthenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManager();
    }
}
