package com.tenneshop.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.Filter;

import org.broadleafcommerce.common.security.BroadleafAuthenticationFailureHandler;
import org.broadleafcommerce.common.security.handler.SecurityFilter;
import org.broadleafcommerce.core.web.order.security.BroadleafAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Qualifier;
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
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@ComponentScan({"org.broadleafcommerce.common.web.security","org.broadleafcommerce.profile.web.core.security",
	"org.broadleafcommerce.core.web.order.security"})
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SiteSecurityConfig extends WebSecurityConfigurerAdapter {

    @Configuration
    public static class DependencyConfiguration {

        @Bean
        protected AuthenticationFailureHandler blAuthenticationFailureHandler(@Qualifier("blAuthenticationFailureRedirectStrategy") RedirectStrategy redirectStrategy) {
            BroadleafAuthenticationFailureHandler response = new BroadleafAuthenticationFailureHandler("/login?error=true");
            response.setRedirectStrategy(redirectStrategy);
            return response;
        }

        @Bean
        protected AuthenticationSuccessHandler blAuthenticationSuccessHandler(@Qualifier("blAuthenticationSuccessRedirectStrategy") RedirectStrategy redirectStrategy) {
            BroadleafAuthenticationSuccessHandler handler = new BroadleafAuthenticationSuccessHandler();
            handler.setRedirectStrategy(redirectStrategy);
            handler.setDefaultTargetUrl("/");
            handler.setTargetUrlParameter("successUrl");
            handler.setAlwaysUseDefaultTargetUrl(false);
            return handler;
        }

        @Bean
        protected Filter blCsrfFilter() {
            SecurityFilter securityFilter = new SecurityFilter();
            List<String> excludedRequestPatterns = new ArrayList<>();
            excludedRequestPatterns.add("/sample-checkout/**");
            excludedRequestPatterns.add("/hosted/sample-checkout/**");
            securityFilter.setExcludedRequestPatterns(excludedRequestPatterns);
            return securityFilter;
        }

    }
    
    @Resource(name="blAuthenticationSuccessHandler")
    protected AuthenticationSuccessHandler successHandler;

    @Resource(name="blAuthenticationFailureHandler")
    protected AuthenticationFailureHandler failureHandler;
    
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
			.csrf().disable()
			.formLogin()
				.loginPage("/login")
				.successHandler(successHandler)
                .failureHandler(failureHandler)
                .permitAll()
				.and()
			.authorizeRequests()
				.antMatchers("/", "/register", "/login/forgotPassword", "/login/resetPassword").permitAll()
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
