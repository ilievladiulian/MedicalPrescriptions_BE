package com.cfin.prescriptioninventory.filters;

import com.cfin.prescriptioninventory.dtos.AuthResponse;
import com.cfin.prescriptioninventory.services.UserAuthProxyService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthorizationFilter extends BasicAuthenticationFilter {
	private static final String HEADER = "Authorization";
	private  static final String TOKEN_PREFIX = "Bearer ";

	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(HEADER);

		if(RequestMethod.OPTIONS.name().equals(req.getMethod())) {
			chain.doFilter(req, res);
			return;
		}

		if (header == null || !header.startsWith(TOKEN_PREFIX)) {
			res.setStatus(401);
			return;
		}

		try {
			UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
			if (authentication == null) {
				authorizationFailed(req, res);
				return;
			}
			SecurityContextHolder.getContext().setAuthentication(authentication);
			chain.doFilter(req, res);
		} catch (Exception e) {
			authorizationFailed(req, res);
			return;
		}
	}

	private void authorizationFailed(HttpServletRequest req, HttpServletResponse res) {
		res.setStatus(401);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER);
		if (token != null) {
			String parsedAuthHeader = token.replace(TOKEN_PREFIX, "");
			String username = parsedAuthHeader.split(":")[0];
			String parsedToken = parsedAuthHeader.split(":")[1];
			try {
				WebApplicationContext webApplicationContext = WebApplicationContextUtils
						.getWebApplicationContext(request.getServletContext());
				UserAuthProxyService authorizationService = webApplicationContext.getBean(UserAuthProxyService.class);
				AuthResponse response = authorizationService.authorizeUser(username, parsedToken);
				if (response.getAuthorized()) {
					return new UsernamePasswordAuthenticationToken(response.getUsername(), null, new ArrayList<>());
				}
			} catch (Exception e) {
				throw new RuntimeException("Authorization failed");
			}
		}
		return null;
	}

}
