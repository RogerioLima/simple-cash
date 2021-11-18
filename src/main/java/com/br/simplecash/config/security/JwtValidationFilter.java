package com.br.simplecash.config.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtValidationFilter extends BasicAuthenticationFilter {
	
	private static final String HEADER_ATTRIBUTE = "Authorization";
	private static final String PREFIX_ATTRIBUTE = "Bearer ";
	
	private final String tokenKey;

	public JwtValidationFilter(AuthenticationManager authenticationManager, String tokenKey) {
		super(authenticationManager);
		this.tokenKey = tokenKey;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String authorizationContent = request.getHeader(HEADER_ATTRIBUTE);
		
		if (authorizationContent == null || !authorizationContent.startsWith(PREFIX_ATTRIBUTE)) {
			chain.doFilter(request, response);
			return;
		}
		
		String token = authorizationContent.replace(PREFIX_ATTRIBUTE, "");
		UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);
		
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
		String user = JWT.require(Algorithm.HMAC512(tokenKey))
                     .build()
                     .verify(token)
                     .getSubject();
		
		if (user == null) {
			return null;
		}
		
		return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
	}
}
