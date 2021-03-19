package com.garcia.springboot.app.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.garcia.springboot.app.constant.JwtConstant;
import com.garcia.springboot.app.service.UserService;
import com.garcia.springboot.app.util.JwtTokenUtil;
import com.garcia.springboot.app.util.StringUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtFilter extends OncePerRequestFilter {

	private static final Logger log = LogManager.getLogger(JwtFilter.class);

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authorizationHeader = request.getHeader(JwtConstant.HEADER_AUTHORIZATION);
		String token = null;
		String username = null;
		try {
			if (StringUtil.isNotEmpty(authorizationHeader)
					&& authorizationHeader.startsWith(JwtConstant.HEADER_AUTHORIZATION_BEARER)) {
				log.info("Extracting token from header...");
				token = authorizationHeader.substring(7);
				username = jwtTokenUtil.extractUsername(token);
			}

			if (StringUtil.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userService.loadUserByUsername(username);
				if (jwtTokenUtil.validateToken(token, userDetails)) {
					log.info("User & token validated...");
					UsernamePasswordAuthenticationToken credentialAuthToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					credentialAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(credentialAuthToken);
				}
			}
		} catch (ExpiredJwtException ex) {
			log.info("Token expired, check refresh token...");
			String isRefreshToken = request.getHeader("isRefreshToken");
			String requestURL = request.getRequestURL().toString();
			// allow for Refresh Token creation if following conditions are true.
			if (StringUtil.isNotEmpty(isRefreshToken) && isRefreshToken.equals("true")
					&& requestURL.contains("refreshtoken")) {
				log.info("Is valid refresh token request");
				allowForRefreshToken(ex, request);
			} else {
				log.info("Invalid refresh token request");
				request.setAttribute("exception", ex);
			}
		} catch (BadCredentialsException ex) {
			log.info("Bad credential detected");
			request.setAttribute("exception", ex);
		} catch (Exception ex) {
			log.info("Unhandled exception found");
			log.error(ex);
		}

		filterChain.doFilter(request, response);
	}

	private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {

		// create a UsernamePasswordAuthenticationToken with null values.
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				null, null, null);
		// After setting the Authentication in the context, we specify
		// that the current user is authenticated. So it passes the
		// Spring Security Configurations successfully.
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		// Set the claims so that in controller we will be using it to create
		// new JWT
		request.setAttribute("claims", ex.getClaims());

	}

}
