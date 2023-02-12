package com.gl.empmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gl.empmanagement.service.DomainUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final DomainUserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// user authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}

	// user authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login", "/h2-console/**", "/h2-console**", "/h2-console/login**")
				.permitAll().antMatchers(HttpMethod.POST, "/employees/list").hasAnyRole("USER", "ADMIN")
				.antMatchers("/employees/list/**", "/employees/list**", "/employees/search/**", "/employees/403")
				.hasAnyRole("USER", "ADMIN")
				.antMatchers(HttpMethod.POST, "/employees/new", "/employees/update/**", "/user/userRegister/**",
						"/user/adminRegister/**")
				.hasRole("ADMIN").antMatchers(HttpMethod.DELETE, "/employees/**").hasRole("ADMIN")

				.anyRequest().authenticated().and().formLogin().loginProcessingUrl("/login")
				.defaultSuccessUrl("/employees/list", true).permitAll().and()
//	            .logout()
//	            .logoutSuccessUrl("/login").permitAll()
//	            .and()
				.exceptionHandling().accessDeniedPage("/employees/list").and().cors().disable().csrf().disable()
				.headers().frameOptions().disable();
	}
}
