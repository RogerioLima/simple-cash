package com.br.simplecash.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.br.simplecash.core.gateway.user.UserLoginGateway;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebSecurity
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {
	private final UserLoginGateway userLoginGateway;
	private final PasswordEncoder passwordEncoder;
	
	@Value("${token.expiration}")
	private int tokenEpiration;
	
	@Value("${token.key}")
	private String tokenKey;
	
	@Autowired
	private ObjectMapper mapper;
	
	public JwtSecurityConfig(UserLoginGateway userLoginGateway, PasswordEncoder passwordEncoder) {
		this.userLoginGateway = userLoginGateway;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userLoginGateway).passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		JwtAuthenticationFilter authenticationFilter = new JwtAuthenticationFilter(authenticationManager(), mapper, tokenEpiration, tokenKey);
		JwtValidationFilter validationFilter = new JwtValidationFilter(authenticationManager(), tokenKey);
		
		http.csrf().disable().authorizeRequests()
        .antMatchers(HttpMethod.POST, "login").permitAll()
        .antMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
        .anyRequest().authenticated()
      .and()
        .addFilter(authenticationFilter)
        .addFilter(validationFilter)
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
		source.registerCorsConfiguration("/**", corsConfiguration);
		
		return source;
	}
}
