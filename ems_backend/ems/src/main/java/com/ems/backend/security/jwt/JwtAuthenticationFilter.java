package com.ems.backend.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ems.backend.security.config.MyUserDetailService;
import com.ems.backend.util.exception.ApiBusinessException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private MyUserDetailService myUserDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String header = request.getHeader("Authorization");
		
		
		if(header == null || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		  
		
		String token = header.substring(7);
		
		try {
			String username = jwtService.extractUsername(token);
			
			System.out.println("User name : " + username);
			if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = myUserDetailService.loadUserByUsername(username);
				
				if(userDetails != null && jwtService.isTokenValid(token)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							username, userDetails.getPassword(), userDetails.getAuthorities());
					
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
		} catch (ApiBusinessException e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.getWriter().write("Token is expired or invalid");
	        return;
		}
		
		
		filterChain.doFilter(request, response);
		
	}

}




