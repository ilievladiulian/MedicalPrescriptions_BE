package com.cfin.prescriptioninventory.config;

import com.cfin.prescriptioninventory.filters.AuthorizationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().addFilterBefore(new AuthorizationFilter(authenticationManager()), BasicAuthenticationFilter.class);
	}
}