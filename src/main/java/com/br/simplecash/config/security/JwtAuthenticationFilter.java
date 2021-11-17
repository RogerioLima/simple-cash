package com.br.simplecash.config.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.br.simplecash.core.domain.User;
import com.br.simplecash.core.exception.UnAuthorizeException;
import com.br.simplecash.provider.security.UserDetail;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;
	private final ObjectMapper mapper;
	private final Integer tokenExpiration;
	private final String tokenKey;
	
	public JwtAuthenticationFilter(
			AuthenticationManager authenticationManager,
			ObjectMapper mapper,
			Integer tokenExpiration,
			String tokenKey
	) {
		this.authenticationManager = authenticationManager;
		this.mapper = mapper;
		this.tokenExpiration = tokenExpiration;
		this.tokenKey = tokenKey;
	}
	
	@Override
	public Authentication attemptAuthentication(
	  HttpServletRequest request,
	  HttpServletResponse response
	) throws AuthenticationException {
		try {
			User user = mapper.readValue(request.getInputStream(), User.class);
			return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), new ArrayList<>())
			);
		} catch (IOException e) {
			throw new UnAuthorizeException("Falha ao autenticar usuário");
		}
	}
	
	@Override
	protected void successfulAuthentication(
		HttpServletRequest request, 
		HttpServletResponse response, 
		FilterChain chain,
		Authentication authResult
	) throws IOException, ServletException {
		UserDetail userDetail = (UserDetail) authResult.getPrincipal();
		
		String token = JWT.create()
                            .withSubject(userDetail.getUsername())
                            .withExpiresAt(new Date(System.currentTimeMillis() + tokenExpiration))
                            .sign(Algorithm.HMAC512(tokenKey));
		
		response.getWriter().write(token);
		response.getWriter().flush();
	}
}
